package com.monkey.wx.controller;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.monkey.wx.dao.WxConfigMapper;
import com.monkey.wx.dao.WxUserMapper;
import com.monkey.wx.domain.WxConfig;
import com.monkey.wx.domain.WxUser;
import com.monkey.wx.service.WxUserService;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Controller
public class ChargeController {
	
	@Autowired
	WxMpService wxService;
	@Autowired
	WxUserMapper wxUserMapper;
	@Autowired
	WxConfigMapper wxConfigMapper;
	
	@RequestMapping("/charge/{userid}/{agent}")
	public String charge(@PathVariable String userid, @PathVariable String agent) {
		WxUser wxUser = wxUserMapper.selectByPrimaryKey(userid);
		WxConfig wxConfig = wxConfigMapper.selectByPrimaryKey(wxUser.getConfigId());
		String url = "";
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if (servletRequestAttributes != null) {
			HttpServletRequest request = servletRequestAttributes.getRequest();
			URL requestURL = null;
			try {
				requestURL = new URL(request.getRequestURL().toString());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			url = this.wxService.switchoverTo(wxConfig.getAppId()).oauth2buildAuthorizationUrl(String.format("%s://%s/toCharge/%s/%s",
					requestURL.getProtocol(), requestURL.getHost(), userid, agent), WxConsts.OAuth2Scope.SNSAPI_BASE,
					null);
		}
		return "redirect:"+url;
	}
	
	//跳转到我的订单页面
	@RequestMapping("/myOrder")
	public String myOrder() {
		return "wx/myOrder";
	}
	
	@RequestMapping("toCharge")
	public String toCharge(){
		return "wx/charge";
	}
	
	@RequestMapping("/toCharge/{userid}/{agent}")
	public String toCharge(@PathVariable String userid,@PathVariable String agent, @RequestParam String code, ModelMap map){
		try {
            WxMpOAuth2AccessToken accessToken = wxService.oauth2getAccessToken(code);
            WxMpUser user = wxService.oauth2getUserInfo(accessToken, null);
            map.put("openId", user.getOpenId());
            map.put("userid", userid);
            map.put("agent", agent);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
		return "wx/charge";
	}
	
} 
