package com.monkey.wx.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.monkey.common.annotation.Log;
import com.monkey.wx.service.ChargeService;
import com.monkey.wx.service.GetPhoneInfoService;

@Controller
public class ChargeController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ChargeService chargeService;
	
	@Autowired
	private GetPhoneInfoService getPhoneInfoService;
	
	@RequestMapping("/charge")
	public String charge() {
		return "mp/charge";
	}
	//查询手机号信息
	@RequestMapping("/getPhoneInfo")
	@ResponseBody
	public JSONObject getPhoneInfo(HttpServletRequest req) {
		return getPhoneInfoService.getPhoneInfo(req);
	}
	
	//提交充值
	@RequestMapping("submitRecharge")
	@ResponseBody
	public JSONObject submitRecharge(HttpServletRequest req) {
		return chargeService.submitRecharge(req);
	}
} 
