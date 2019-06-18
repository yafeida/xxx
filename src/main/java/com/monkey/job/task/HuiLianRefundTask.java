package com.monkey.job.task;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.monkey.common.annotation.CronTag;
import com.monkey.wx.dao.WxConfigMapper;
import com.monkey.wx.dao.WxOrderMapper;
import com.monkey.wx.dao.WxUserMapper;
import com.monkey.wx.domain.WxConfig;
import com.monkey.wx.domain.WxOrder;
import com.monkey.wx.domain.WxOrderExample;
import com.monkey.wx.domain.WxUser;
import com.monkey.wx.pay.HuiLianUtils.HttpsMain;
import com.monkey.wx.pay.HuiLianUtils.RSA;
import com.monkey.wx.pay.HuiLianUtils.XmlSignUtil;
import com.monkey.wx.pay.HuiLianUtils.XmlUtil;

@CronTag("huiLianRefundTask")
public class HuiLianRefundTask {

	private static Logger log = LoggerFactory.getLogger(HuiLianRefundTask.class);
	
	@Autowired
	WxOrderMapper wxOrderMapper;
	@Autowired
	WxUserMapper wxUserMapper;
	@Autowired
	WxConfigMapper wxConfigMapper;
	
	public void queryOrder(){
		WxOrderExample woEx = new WxOrderExample();
		woEx.createCriteria().andZfStatusEqualTo(4);
		List<WxOrder> list = wxOrderMapper.selectByExample(woEx);
		for (WxOrder wxOrder : list) {
			WxUser wxUser = wxUserMapper.selectByPrimaryKey(wxOrder.getUserId());
			WxConfig wxConfig = wxConfigMapper.selectByPrimaryKey(wxUser.getConfigId());
			String zfInfo = wxConfig.getZfInfo();
			JSONObject zfInfoJson = JSONObject.parseObject(zfInfo);
			String function = "ant.mybank.bkmerchanttrade.refundQuery";
			XmlUtil xmlUtil = new XmlUtil();
			Map<String, String> form = new HashMap<>();
			form.put("IsvOrgId", zfInfoJson.getString("IsvOrgId"));// 合作方机构号
			form.put("HlMerchantId", zfInfoJson.getString("HlMerchantId"));// 商户号
			form.put("OutRefundNo", wxOrder.getOrderId());// 原支付交易外部交易号

			form.put("Function", function);
			form.put("ReqTime", new Timestamp(System.currentTimeMillis()).toString());
			// reqMsgId每次报文必须都不一样
			form.put("ReqMsgId", UUID.randomUUID().toString());

			try {
				String param = xmlUtil.format(form, function);
				param = XmlSignUtil.sign(param, zfInfoJson.getString("myRsaPrivateKey"));
				log.info("汇联退款查询报文:" + param);
				String response = HttpsMain.httpsReq(HttpsMain.payUrl, param);

				log.info("response{}" + response);
				if (!XmlSignUtil.verify(response, RSA.huiLianRsaPublicKey)) {
					continue;
				}
				// 解析报文
				Map<String, Object> resMap = xmlUtil.parse(response, function);
				// 结果消息
				log.info("汇联退款查询返回报文:" + resMap);
				String tradeStatus = resMap.get("TradeStatus") + "";
				if (tradeStatus.equals("succ")) {
					wxOrder.setZfStatus(5);
				} else if (tradeStatus.equals("fail")) {
					wxOrder.setZfStatus(6);
				} else {
					continue;
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
