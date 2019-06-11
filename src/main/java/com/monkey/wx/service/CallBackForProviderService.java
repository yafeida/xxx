package com.monkey.wx.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monkey.common.util.MD5Utils;
import com.monkey.common.util.RSAEncrypt;
import com.monkey.wx.dao.WxOrderMapper;
import com.monkey.wx.domain.WxOrder;
import com.monkey.wx.domain.WxOrderExample;

@Service
public class CallBackForProviderService extends BaseService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	WxOrderMapper wxOrderMapper;
	
	/**
	 * 微信话费充值回调
	 * @param request
	 * @param response
	 * @return
	 */
	public String chargeBillCallback(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> param = getMaps(request);
		if(param == null) {
			logger.error("话费充值回调数据为空" +param);
			return "fail";
		}
		System.out.println("_____________"+param);
		String orderId = param.get("orderId")+"";						//订单平台ID
		String customerOrderId = param.get("customerOrderId")+"";		//客户32位订单号
		String phoneNo = param.get("phoneNo")+"";						//客户手机号
		String status = param.get("status")+"";							//充值结果状态
		String spec = param.get("spec")+"";								//充值金额
		String scope = param.get("scope")+"";							//范围(全国：nation，省内：province)
		String signature = param.get("signature")+"";					//数据私钥签名
		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDEnge3ccqHT/5RfzCo1DDq/gc48oBFA5mGLC+GcRQa30kTToKGgXsS0jRDmtRfKrw0ckr89p4AQTkhBXlqYgflQ3B+f5ulVjrwXP4n5LSLIjTYqB/mN57rwIWQyT2+t6Z7EKGnwFUIrBYoBx9vUvUYFDAZp4rAjp36gHmgCwyVyQIDAQAB";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", customerOrderId);
		map.put("providerOrderId", orderId);
		map.put("phoneNo", phoneNo);
		map.put("spec", spec);
		if (status.equals("success"))
		{
			map.put("status", 1);
		}
		else if (status.equals("fail"))
		{
			map.put("status", 0);
		}
		WxOrderExample wxOrderExample = new WxOrderExample();
		wxOrderExample.createCriteria().andOrderIdEqualTo(orderId);
		//查询回调的订单号信息
		List<WxOrder> list = wxOrderMapper.selectByExample(wxOrderExample);
		if(list == null) {
			logger.error("话费充值回调数据查询为空" + map);
			return "fail";
		}
		//对返回的参数进行排序组合，用于签名验证
		String paramStr = "customerOrderId="+customerOrderId+"&orderId="+orderId+"&phoneNo="+phoneNo
						 +"&scope="+scope+"&spec="+spec+"&status="+status;
		byte[] data =MD5Utils.md5(paramStr).getBytes();
		try {
			if(!RSAEncrypt.verify(data,publicKey,signature)) {		//验签
				logger.error("话费充值回调签名验证失败");
				return "fail";
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
}
