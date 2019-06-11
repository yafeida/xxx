package com.monkey.wx.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.monkey.common.util.HttpUtils;
import com.monkey.common.util.MD5Utils;

@Service
public class GetPhoneInfoService extends BaseService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 查询号段信息
	 * @param req
	 * @return 返回json格式的号段信息
	 */
	public JSONObject getPhoneInfo(HttpServletRequest req){
		Map<String,Object> param = getMaps(req);
		String phone = param.get("phone")+"";
		String parameter = "phoneNo="+phone+"&terminalName=newweixin";
		String sign = MD5Utils.ToMD5(parameter);
		String url = "http://118.31.229.23:8080/bosspaybill/order/getOrderPriceByPhone";
		JSONObject json = null;
		try {
			json = JSONObject.parseObject(HttpUtils.sendPost(url,parameter+"&signature="+sign));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("查询号段信息失败", e);
		}
		return json;
	}
	
}
