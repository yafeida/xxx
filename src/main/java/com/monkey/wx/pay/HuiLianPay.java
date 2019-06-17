package com.monkey.wx.pay;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.monkey.wx.domain.WxConfig;
import com.monkey.wx.domain.WxOrder;
import com.monkey.wx.pay.HuiLianUtils.HttpsMain;
import com.monkey.wx.pay.HuiLianUtils.RSA;
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

	/**
	 * 汇联支付退款方法
	 * @param wxOrder
	 * @return
	 */
	public static boolean refound(WxOrder wxOrder,WxConfig wxConfig){
		log.info("汇联退款方法，订单号为" + wxOrder.getOrderId()+"，手机号为" + wxOrder.getPhone());
		String function = "ant.mybank.bkmerchanttrade.refund";
		XmlUtil xmlUtil = new XmlUtil();
		
		String zfInfo = wxConfig.getZfInfo();
		JSONObject zfInfoJson = JSONObject.parseObject(zfInfo);
		Map<String, String> form = new HashMap<>();
		form.put("OutTradeNo", wxOrder.getOrderId());// 原支付交易外部交易号、
		form.put("HlMerchantId", zfInfoJson.getString("HlMerchantId"));// 商户号
		form.put("IsvOrgId", zfInfoJson.getString("IsvOrgId"));// 合作方机构号
		String outRefundNo = UUID.randomUUID().toString().replaceAll("-", "");
		form.put("OutRefundNo", outRefundNo);// 退款外部交易号
		int fee = (int) (wxOrder.getDisprice() * 100);
		form.put("RefundAmount", fee + "");// 退款金额
		form.put("DeviceCreateIp", "127.0.0.1");// 创建订单终端的IP

		form.put("Function", function);
		form.put("ReqTime", new Timestamp(System.currentTimeMillis()).toString());
		// reqMsgId每次报文必须都不一样
		form.put("ReqMsgId", UUID.randomUUID().toString());
		try {
			String param = xmlUtil.format(form, function);
			param = XmlSignUtil.sign(param, zfInfoJson.getString("myRsaPrivateKey"));
			// 发送请求
			// https
			log.info("汇联退款请求报文{}" + param);

			String response = HttpsMain.httpsReq(HttpsMain.payUrl, param);

			log.info("response{}" + response);
			if (!XmlSignUtil.verify(response, RSA.huiLianRsaPublicKey)) {
				return false;
			}
			// 解析报文
			Map<String, Object> resMap = xmlUtil.parse(response, function);
			log.info("汇联退款返回报文:" + resMap);
			// 结果消息
			@SuppressWarnings("unchecked")
			Map<String, Object> respInfo = (Map<String, Object>) resMap.get("RespInfo");
			if (respInfo.get("ResultCode").equals("0000")) {
				log.info("汇联退款提交成功");
				return true;
			}
		} catch (Exception e) {
			log.error("汇联退款出现异常",e);
			e.printStackTrace();
		}
		return false;
	}
	
}
