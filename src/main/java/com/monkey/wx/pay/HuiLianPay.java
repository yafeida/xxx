package com.monkey.wx.pay;

import java.util.HashMap;
import java.util.Map;

import com.sun.xml.internal.ws.util.xml.XmlUtil;

public class HuiLianPay {

	//汇联支付方法
	public static String pay(Map<String,Object> map){
		String function = "ant.mybank.bkmerchanttrade.prePay";
        XmlUtil xmlUtil = new XmlUtil();

        Map<String, String> form = new HashMap<>();
//        form.put("OutTradeNo", oid);
//        form.put("Body", "浩百-话费充值"+phone+"[面额:"+mianer+"元，支付:"+jiner+"元]");
//        form.put("TotalAmount", fee+"");
//        form.put("Currency", "CNY");
//        form.put("HlMerchantId", wxInfo.getZxPaternerKey());
//        form.put("IsvOrgId", wxInfo.getZxPartner());
//        form.put("ChannelType", "WX");
//        form.put("OpenId", openId);
//        form.put("DeviceCreateIp", ip);
//        form.put("SettleType", "T1");
//        form.put("SubAppId", appId);
		return "";
	}
	
}
