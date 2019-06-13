package com.monkey.wx.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monkey.wx.service.PayService;

@Controller
public class PayController {

	@Autowired
	PayService payService;
	
	@RequestMapping("pay")
	@ResponseBody
	public String pay(HttpServletRequest request){
		return payService.pay(request);
	}
	
	@RequestMapping("callback/huilian")
	public String HuiLianCallback(HttpServletRequest request){
		return "";
	}
	
}
