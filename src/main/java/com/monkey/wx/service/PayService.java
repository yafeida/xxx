package com.monkey.wx.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monkey.common.util.IPUtils;
import com.monkey.wx.dao.WxConfigMapper;
import com.monkey.wx.dao.WxOrderMapper;
import com.monkey.wx.dao.WxUserMapper;
import com.monkey.wx.domain.WxConfig;
import com.monkey.wx.domain.WxUser;

@Service
public class PayService {

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
		
		WxUser wxUser = wxUserMapper.selectByPrimaryKey(userId);//微信对象实体类
		if(wxUser == null){
			return "客户不存在";
		}
		WxConfig wxConfig = wxConfigMapper.selectByPrimaryKey(wxUser.getConfigId());
		if(wxConfig == null){
			return "客户未配置支付相关参数";
		}
		String zfType = wxConfig.getZfType();
		if(zfType.equals("HL")){//汇联支付
			
		}
		return "";
	}
	
	public boolean createOrder(){
		boolean flag = false;
		
		return flag;
	}
	
}
