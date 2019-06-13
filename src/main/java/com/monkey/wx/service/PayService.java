package com.monkey.wx.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monkey.common.util.DateUtil;
import com.monkey.common.util.IDUtils;
import com.monkey.common.util.IPUtils;
import com.monkey.wx.dao.WxConfigMapper;
import com.monkey.wx.dao.WxOrderMapper;
import com.monkey.wx.dao.WxUserMapper;
import com.monkey.wx.domain.WxConfig;
import com.monkey.wx.domain.WxOrder;
import com.monkey.wx.domain.WxUser;
import com.monkey.wx.pay.HuiLianPay;

@Service
public class PayService {

	private static Logger log = LoggerFactory.getLogger(PayService.class);
	
	@Autowired
	WxUserMapper wxUserMapper;
	@Autowired
	WxConfigMapper wxConfigMapper;
	@Autowired
	WxOrderMapper wxOrderMapper;
	
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
		
		return "";
	}
	
}
