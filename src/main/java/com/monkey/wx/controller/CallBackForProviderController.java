package com.monkey.wx.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monkey.wx.service.CallBackForProviderService;

@Controller
public class CallBackForProviderController {
	
	@Autowired
	private CallBackForProviderService callBackForProviderService;
	
	/**
	 * 话费充值回调
	 * @author xxz 2019年6月10日上午16:08:20
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/chargeBillCallback")
    @ResponseBody
    public String chargeBillCallback(HttpServletRequest request, HttpServletResponse response)
    {
        return callBackForProviderService.chargeBillCallback(request, response);
    }
}
