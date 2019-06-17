package com.monkey.wx.service;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.monkey.common.util.DateUtil;
import com.monkey.common.util.HttpUtils;
import com.monkey.common.util.MD5Utils;
import com.monkey.common.util.RSAEncrypt;
import com.monkey.wx.dao.WxOrderMapper;
import com.monkey.wx.dao.WxUserMapper;
import com.monkey.wx.domain.WxOrder;
import com.monkey.wx.domain.WxOrderExample;
import com.monkey.wx.domain.WxUser;

@Service
public class BosspayBillService {
	
	private static Logger log = LoggerFactory.getLogger(BosspayBillService.class);

	private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDEnge3ccqHT/5RfzCo1DDq/gc48oBFA5mGLC+GcRQa30kTToKGgXsS0jRDmtRfKrw0ckr89p4AQTkhBXlqYgflQ3B+f5ulVjrwXP4n5LSLIjTYqB/mN57rwIWQyT2+t6Z7EKGnwFUIrBYoBx9vUvUYFDAZp4rAjp36gHmgCwyVyQIDAQAB";
	
	@Autowired
	WxOrderMapper wxOrderMapper;
	@Autowired
	WxUserMapper wxUserMapper;

	private static String charge_url = "http://118.31.229.23:8080/bosspaybill/order/quotaOrder";
	private static String callback_url = "";

	/**
	 * 提交充值
	 * 
	 * @param phone
	 * @param fee
	 * @param orderId
	 * @return
	 */
	public Map<String, Object> deal(WxOrder wxOrder) {
		Map<String, Object> map = new HashMap<String, Object>();
		String orderId = wxOrder.getOrderId();
		String phone = wxOrder.getPhone();
		int fee = (int) (wxOrder.getFee()*100);
		WxUser wxUser = wxUserMapper.selectByPrimaryKey(wxOrder.getUserId());
		String billName = wxUser.getBillName();
		String billKey = wxUser.getBillKey();
		String timeStamp = DateUtil.getDateFormat(new Date(), "yyyyMMddHHmmssSSS");
		String sign = sign(callback_url,orderId,"1",phone,"nation",fee+"",billName,timeStamp,billKey);
		String param = "terminalName=" + billName + "&customerOrderId=" + orderId + "&phoneNo=" + phone
				+ "&orderType=1&scope=nation&spec=" + fee + "&callbackUrl=" + callback_url
				+ "&timeStamp=" + timeStamp + "&signature=" + sign;
		String result = HttpUtils.get(charge_url+"?"+param);
		log.info("充值提交返回："+result);
		if(StringUtil.isNotEmpty(result)){
			JSONObject resultJson = JSONObject.parseObject(result);
			String code = resultJson.getString("code");
			String message = resultJson.getString("message");
			map.put("msg", code+":"+message);
			if(code.equals("0")){
				map.put("flag", true);
			}else{
				map.put("flag", false);
			}
		}else{
			map.put("msg", "充值提交获取返回值为空");
			map.put("flag", false);
		}
		return map;
	}

	/**
	 * 生成签名
	 * @param paramMap
	 * @param privateKeyStr
	 * @return
	 * @throws Exception
	 */
	public static String sign(String callbackUrl, String customerOrderId, String orderType, String phoneNo,
			String scope, String spec, String terminalName, String timeStamp, String privateKeyStr){
		try{
			byte[] keyByteArray = Base64.decodeBase64(privateKeyStr);
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyByteArray);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
			String md5 = md5Param(callbackUrl, customerOrderId, orderType, phoneNo, scope, spec, terminalName, timeStamp);
			log.info("订单提交中生成的MD5值为:" + md5);
			byte[] rsa = encodeBytePrivate(md5.getBytes(), privateKey);
			return Hex.encodeHexString(rsa);
		}catch(Exception e){
			log.error("订单提交中出现异常",e);
			return null;
		}
	}

	/**
	 * RSA 加密返回byte[]
	 * 
	 * @param content
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static byte[] encodeBytePrivate(byte[] content, PrivateKey privateKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(content);
	}

	public static String md5Param(String callbackUrl, String customerOrderId, String orderType, String phoneNo,
			String scope, String spec, String terminalName, String timeStamp) {
		String content = getCustSignature(callbackUrl, customerOrderId, orderType, phoneNo, scope, spec, terminalName,
				timeStamp);
		log.info("订单提交中生成签名的参数:" + content);
		return MD5Utils.md5(content);
	}

	public static String getCustSignature(String callbackUrl, String customerOrderId, String orderType, String phoneNo,
			String scope, String spec, String terminalName, String timeStamp) {
		StringBuilder sb = new StringBuilder();
		sb.append("callbackUrl=" + callbackUrl);
		sb.append("&customerOrderId=" + customerOrderId);
		sb.append("&orderType=" + orderType);
		sb.append("&phoneNo=" + phoneNo);
		sb.append("&scope=" + scope);
		sb.append("&spec=" + spec);
		sb.append("&terminalName=" + terminalName);
		sb.append("&timeStamp=" + timeStamp);
		return sb.toString();
	}
	
	/**
	 * bosspaybill平台接收回调方法
	 * @param request
	 * @return
	 */
	public String callback(HttpServletRequest request) {
		String orderId = request.getParameter("orderId"); // 订单平台ID
		String customerOrderId = request.getParameter("customerOrderId"); // 客户32位订单号
		String phoneNo = request.getParameter("phoneNo"); // 客户手机号
		String status = request.getParameter("status"); // 充值结果状态
		String spec = request.getParameter("spec"); // 充值金额
		String scope = request.getParameter("scope"); // 范围(全国：nation，省内：province)
		String signature = request.getParameter("signature"); // 数据私钥签名
		if (StringUtil.isEmpty(orderId) || StringUtil.isNotEmpty(customerOrderId) || StringUtil.isNotEmpty(phoneNo)
				|| StringUtil.isNotEmpty(status) || StringUtil.isNotEmpty(spec) || StringUtil.isNotEmpty(scope)
				|| StringUtil.isNotEmpty(signature)) {
			log.error("接收充值平台回调有参数为空");
			return "fail";
		}
		String paramStr = "customerOrderId=" + customerOrderId + "&orderId=" + orderId + "&phoneNo=" + phoneNo
				+ "&scope=" + scope + "&spec=" + spec + "&status=" + status;
		byte[] data = MD5Utils.md5(paramStr).getBytes();
		try {
			if (!RSAEncrypt.verify(data, publicKey, signature)) { // 验签
				log.error("话费充值回调签名验证失败");
				return "fail";
			}
		} catch (Exception e) {
			log.error("话费充值回调签名验证出现异常",e);
			e.printStackTrace();
		}
		WxOrderExample woEx = new WxOrderExample();
		woEx.createCriteria().andOrderIdEqualTo(customerOrderId).andCzStatusEqualTo(1);
		List<WxOrder> woList = wxOrderMapper.selectByExample(woEx);
		if(woList.isEmpty()){
			log.error("接收充值平台回调未查询到订单号为"+customerOrderId+"的订单记录");
			return "fail";
		}
		WxOrder wxOrder = woList.get(0);
		wxOrder.setCzCallbackTime(DateUtil.getDefaultDateFormat());
		wxOrder.setCzMsg(wxOrder.getCzMsg()+"|接收到回调为："+status);
		if(status.equals("success")){
			wxOrder.setCzStatus(3);
		}else{
			wxOrder.setCzStatus(4);
		}
		int i = wxOrderMapper.updateByPrimaryKeySelective(wxOrder);
		if(i > 0){
			log.info("回调处理完成，更新订单成功");
		}
		return "success";
	}

	/**
	 * 查询号段信息
	 * @param req
	 * @return 返回json格式的号段信息
	 */
	public JSONObject getPhoneInfo(HttpServletRequest req){
		String phone = req.getParameter("phone");
		String parameter = "phoneNo="+phone+"&terminalName=newweixin";
		String sign = MD5Utils.ToMD5(parameter);
		String url = "http://118.31.229.23:8080/bosspaybill/order/getOrderPriceByPhone";
		JSONObject json = null;
		try {
			json = JSONObject.parseObject(HttpUtils.sendPost(url,parameter+"&signature="+sign));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("查询号段信息失败", e);
		}
		return json;
	}
	
}
