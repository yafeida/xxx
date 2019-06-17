package com.monkey.wx.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.monkey.wx.service.BosspayBillService;

@Controller
public class BosspayBillController {

	@Autowired
	BosspayBillService billService;
	
	//查询手机号信息
	@RequestMapping("/getPhoneInfo")
	@ResponseBody
	public JSONObject getPhoneInfo(HttpServletRequest req) {
		return billService.getPhoneInfo(req);
	}
	
	/**
	 * 接收充值平台回调
	 * @param request
	 * @return
	 */
	@RequestMapping("bosspaybill/callback")
	@ResponseBody
	public String callback(HttpServletRequest request){
		return billService.callback(request);
	}
	
}
