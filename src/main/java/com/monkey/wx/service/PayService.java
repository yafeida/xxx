package com.monkey.wx.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.util.StringUtil;
import com.monkey.common.util.DateUtil;
import com.monkey.common.util.IDUtils;
import com.monkey.common.util.IPUtils;
import com.monkey.common.util.MapUtils;
import com.monkey.wx.dao.WxConfigMapper;
import com.monkey.wx.dao.WxOrderMapper;
import com.monkey.wx.dao.WxUserMapper;
import com.monkey.wx.domain.WxConfig;
import com.monkey.wx.domain.WxOrder;
import com.monkey.wx.domain.WxOrderExample;
import com.monkey.wx.domain.WxUser;
import com.monkey.wx.pay.HuiLianPay;
import com.monkey.wx.pay.HuiLianUtils.RSA;
import com.monkey.wx.pay.HuiLianUtils.XmlSignUtil;
import com.monkey.wx.pay.HuiLianUtils.XmlUtil;

@Service
public class PayService {

	private static Logger log = LoggerFactory.getLogger(PayService.class);
	
	@Autowired
	WxUserMapper wxUserMapper;
	@Autowired
	WxConfigMapper wxConfigMapper;
	@Autowired
	WxOrderMapper wxOrderMapper;
	@Autowired
	BosspayBillService billService;
	
	/**
	 * 支付方法
	 * @param request
	 * @return
	 */
	public String pay(HttpServletRequest request){
		String ip = IPUtils.getIpAddr(request);//获取IP地址
		String userId = request.getParameter("userId");//获取客户ID
		String agent = request.getParameter("agent");//代理
		String phone = request.getParameter("phone");//手机号
		String fee = request.getParameter("fee");//原价
		String disprice	= request.getParameter("disprice");//折扣价
		
		String openId = request.getParameter("openId");
		
		String orderId = "wx"+DateUtil.getDateFormat(new Date(), "yyyyMMddHHmmssSSS")+phone+IDUtils.getRandomId(2);//订单号
		WxUser wxUser = wxUserMapper.selectByPrimaryKey(userId);//微信对象实体类
		if(wxUser == null){
			return "客户不存在";
		}
		WxConfig wxConfig = wxConfigMapper.selectByPrimaryKey(wxUser.getConfigId());
		if(wxConfig == null){
			return "客户未配置支付相关参数";
		}
		if(createOrder(ip,orderId,phone,fee,disprice,agent)){
			String zfType = wxConfig.getZfType();
			if(zfType.equals("HL")){//汇联支付
				String result = HuiLianPay.pay(openId, ip, orderId, phone, fee, disprice, agent, wxConfig);
				if(result != null){
					return result;
				}
			}
		}else{
			return "生成订单失败";
		}
		return "发起支付失败";
	}
	
	public boolean createOrder(String ip,String orderId,String phone,String fee,String disprice,String agent){
		boolean flag = false;
		WxOrder wxOrder = new WxOrder();
		wxOrder.setId(IDUtils.getUUID());
		wxOrder.setOrderId(orderId);
		wxOrder.setIp(ip);
		wxOrder.setCreateTime(DateUtil.getDefaultDateFormat());
		wxOrder.setPhone(phone);
		wxOrder.setFee(Double.parseDouble(fee));
		wxOrder.setDisprice(Double.parseDouble(disprice));
		wxOrder.setAgent(agent);
		wxOrder.setZfStatus(0);
		wxOrder.setCzStatus(0);
		int i = wxOrderMapper.insertSelective(wxOrder);
		if(i > 0){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 汇联支付回调方法
	 * @param request
	 * @return
	 */
	public String HuiLianCallback(HttpServletRequest request){
		String xml = readXml(request);
		log.info("汇联支付回调报文：" + xml);
		try{
			if (!XmlSignUtil.verify(xml, RSA.huiLianRsaPublicKey)) {
				throw new Exception("验签失败");
			}else{
				XmlUtil xmlUtil = new XmlUtil();
				Map<String, Object> params = xmlUtil.parseReceive(xml.replaceAll("\r\n", ""), "ant.mybank.bkmerchanttrade.prePayNotice");
				if(HuiLianCallbackDeal(params)){//回调处理成功
					String orderId = (String) params.get("OutTradeNo");
					WxOrderExample woEx = new WxOrderExample();
					woEx.createCriteria().andOrderIdEqualTo(orderId);
					List<WxOrder> woList = wxOrderMapper.selectByExample(woEx);
					if(woList.isEmpty()){
						log.error("汇联支付回调未查询到订单号为"+orderId+"的订单");
						return "FAIL";
					}
					WxOrder wxOrder = woList.get(0);
					Map<String,Object> map = this.billService.deal(wxOrder);
					boolean flag = (boolean) map.get("flag");
					String czMsg = (String) map.get("msg");
					wxOrder.setCzMsg(czMsg);
					wxOrder.setCzTime(DateUtil.getDefaultDateFormat());
					if(flag){//判断是否提交成功
						wxOrder.setCzStatus(1);
					}else{
						wxOrder.setCzStatus(2);
					}
					
				}else{
					
				}
			}
		}catch(Exception e){
			log.error("汇联支付回调验签出现错误",e);
		}
		return "SUCCESS";
	}
	
	/**
	 * 汇联支付回调处理方法
	 * @param map
	 * @return
	 */
	public boolean HuiLianCallbackDeal(Map<String,Object> map){
		String OutTradeNo = (String) map.get("OutTradeNo");//订单号
		if(StringUtil.isNotEmpty(OutTradeNo)){
			WxOrderExample wxOrderEx = new WxOrderExample();
			wxOrderEx.createCriteria().andOrderIdEqualTo(OutTradeNo);
			List<WxOrder> woList = wxOrderMapper.selectByExample(wxOrderEx);
			if(woList.isEmpty()){
				log.error("汇联支付回调处理方法未查询到订单号为"+OutTradeNo+"的订单");
				return false;
			}
			WxOrder wxOrder = woList.get(0);
			if(wxOrder != null){
				if(wxOrder.getZfStatus() == 1){
					String totalAmount = (String) map.get("TotalAmount");
					double zfFee = Integer.parseInt(totalAmount)/100;
					if(zfFee == wxOrder.getDisprice()){//支付金额与应付金额相同
						String zfTime = (String) map.get("GmtPayment");//支付时间
						String tranId = (String) map.get("PayChannelOrderNo");//微信支付订单号
						String OrderNo = (String) map.get("OrderNo");//第三方支付的订单号
						wxOrder.setZfCallbackTime(DateUtil.getDefaultDateFormat());
						wxOrder.setZfStatus(3);
						wxOrder.setZfMsg(MapUtils.toString(map));
						wxOrder.setZfTime(zfTime);
						wxOrder.setZfOrderId(OrderNo);
						wxOrder.setTransactionId(tranId);
						int x = wxOrderMapper.updateByPrimaryKeySelective(wxOrder);
						if(x > 0){
							log.info("汇联支付回调【"+MapUtils.toString(map)+"】更新订单成功");
							return true;
						}else{
							log.error("汇联支付回调OutTradeNo"+OutTradeNo+"更新订单失败");
						}
					}else{
						log.error("汇联支付回调OutTradeNo"+OutTradeNo+",支付金额与应付金额不符");
					}
				}else{
					log.error("汇联支付回调OutTradeNo"+OutTradeNo+",状态不为1");
				}
			}else{
				log.error("汇联支付回调OutTradeNo"+OutTradeNo+",获取订单为null");
			}
		}else{
			log.error("汇联支付回调获取OutTradeNo数据为空");
		}
		return false;
	}
	
	public static String readXml(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		try{
			InputStream inputStream;
			inputStream = request.getInputStream();
			String s;
			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			while ((s = in.readLine()) != null) {
				sb.append(s);
			}
			in.close();
			inputStream.close();
		}catch(Exception e){
			log.error("获取request请求中的xml数据出错",e);
		}
		return sb.toString();
	}
	
}
