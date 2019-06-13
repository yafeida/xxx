package com.monkey.wx.pay;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.monkey.wx.domain.WxConfig;
import com.monkey.wx.pay.HuiLianUtils.HttpsMain;
import com.monkey.wx.pay.HuiLianUtils.XmlSignUtil;
import com.monkey.wx.pay.HuiLianUtils.XmlUtil;
import com.mysql.jdbc.StringUtils;

public class HuiLianPay {
	private static Logger log = LoggerFactory.getLogger(HuiLianPay.class);

	// 汇联支付方法
	public static String pay(String openId, String ip, String orderId, String phone, String fee, String disprice,
			String agent, WxConfig wxConfig) {
		String function = "ant.mybank.bkmerchanttrade.prePay";

		String zfInfo = wxConfig.getZfInfo();
		if (!StringUtils.isNullOrEmpty(zfInfo)) {
			JSONObject zfInfoJson = JSONObject.parseObject(zfInfo);
			Map<String, String> form = new HashMap<>();
			form.put("OutTradeNo", orderId);
			form.put("Body", "浩百-话费充值" + phone + "[面额:" + fee + "元，支付:" + disprice + "元]");
			form.put("TotalAmount", fee + "");
			form.put("Currency", "CNY");
			form.put("HlMerchantId", zfInfoJson.getString("HlMerchantId"));
			form.put("IsvOrgId", zfInfoJson.getString("IsvOrgId"));
			form.put("ChannelType", "WX");
			form.put("OpenId", openId);
			form.put("DeviceCreateIp", ip);
			form.put("SettleType", "T1");
			form.put("SubAppId", wxConfig.getAppId());
			form.put("NotifyUrl", "http://dijiejiaofei.com/wxpay/huilian/huilian_notify");
			form.put("Function", function);
			form.put("ReqTime", new Timestamp(System.currentTimeMillis()).toString());
			form.put("ReqMsgId", UUID.randomUUID().toString());

			XmlUtil xmlUtil = new XmlUtil();
			try {
				// 封装报文
				String param = xmlUtil.format(form, function);
				param = XmlSignUtil.sign(param, zfInfoJson.getString("myRsaPrivateKey"));
				// 发送请求
				log.info("订单号[" + orderId + "],手机号[" + phone + "]充值请求报文" + param);
				String response = HttpsMain.httpsReq(HttpsMain.payUrl, param);
				log.info("订单号[" + orderId + "],手机号[" + phone + "]充值返回报文" + response);
				if (!XmlSignUtil.verify(response, zfInfoJson.getString("huiLianRsaPublicKey"))) {
					log.error("订单号[" + orderId + "],手机号[" + phone + "]的签名验证失败");
					return null;
				} else {
					// 解析报文
					Map<String, Object> resMap = xmlUtil.parse(response, function);
					// 结果消息
					@SuppressWarnings("unchecked")
					Map<String, Object> RespInfo = (Map<String, Object>) resMap.get("RespInfo");
					log.info("订单号[" + orderId + "],手机号[" + phone + "]充值返回报文payInfo:" + resMap.get("PayInfo"));
					String resultCode = RespInfo.get("ResultCode") + "";
					if (resultCode.equals("0000")) {
						String str = (String) resMap.get("PayInfo");
						JSONObject json = JSONObject.parseObject(str);
						return json.toJSONString();
					} else {
						log.error("订单号[" + orderId + "],手机号[" + phone + "]的支付请求失败");
						return null;
					}
				}
			} catch (Exception e) {
				log.error("订单号[" + orderId + "],手机号[" + phone + "]的充值提交出现错误", e);
				return null;
			}
		} else {
			log.error("订单号[" + orderId + "],手机号[" + phone + "]的支付参数zfInfo为空");
			return null;
		}
	}

}
