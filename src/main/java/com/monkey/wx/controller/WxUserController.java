package com.monkey.wx.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.monkey.common.annotation.Log;
import com.monkey.common.controller.BaseController;
import com.monkey.common.domain.QueryRequest;
import com.monkey.common.domain.ResponseBo;
import com.monkey.system.domain.User;
import com.monkey.wx.domain.WxUser;
import com.monkey.wx.service.WxUserService;

@Controller
public class WxUserController extends BaseController{
	
	@Autowired
	WxUserService wxUserService;

	@PostMapping("wxUser")
	public String toPage() {
		return "wx/wxUser";
	}

	@Log("获取微信客户列表")
	@GetMapping("wxUser/list")
	@ResponseBody
	public Map<String, Object> list(QueryRequest request, WxUser wxUser) {
		PageHelper.startPage(request.getPageNum(), request.getPageSize());
		List<WxUser> list = this.wxUserService.list(wxUser);
		PageInfo<WxUser> pageInfo = new PageInfo<>(list);
		return getDataTable(pageInfo);
	}

	@Log("删除微信客户")
	@PostMapping("wxUser/delete")
	@ResponseBody
	public ResponseBo delete(String ids){
		this.wxUserService.delete(ids);
		return ResponseBo.ok("删除成功");
	}
	
}
