package com.monkey.wx.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChargeController {
	
	@RequestMapping("/charge")
	public String charge() {
		return "wx/charge";
	}
	//跳转到我的订单页面
	@RequestMapping("/myOrder")
	public String myOrder() {
		return "wx/myOrder";
	}
} 
