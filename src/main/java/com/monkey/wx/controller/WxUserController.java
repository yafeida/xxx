package com.monkey.wx.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.monkey.common.annotation.Log;
import com.monkey.common.controller.BaseController;
import com.monkey.common.domain.QueryRequest;
import com.monkey.common.domain.ResponseBo;
import com.monkey.wx.domain.WxConfig;
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

	@Log("添加微信客户")
	@PostMapping("wxUser/add")
	@ResponseBody
	public ResponseBo add(WxUser wxUser){
		this.wxUserService.add(wxUser);
		return ResponseBo.ok("添加成功");
	}

	@RequestMapping("wxUser/getWxUser")
	@ResponseBody
	public ResponseBo getWxUser(String Id) {
		Session session = getSession();
		session.setAttribute("wxUserId",Id);
		WxUser wxUser = this.wxUserService.findById(Id);
		return ResponseBo.ok(wxUser);
	}

	@Log("修改微信客户")
	@PostMapping("wxUser/update")
	@ResponseBody
	public ResponseBo update(WxUser wxUser){
		Session session = getSubject().getSession();
		wxUser.setId(session.getAttribute("wxUserId")+"");
		return wxUserService.update(wxUser);
	}
	
	@Log("配置微信客户")
	@RequiresPermissions("wxUser:config")
	@PostMapping("wxUser/config")
	@ResponseBody
	public ResponseBo config(WxConfig wxConfig){
		try {
			String	str = wxUserService.addConfig(wxConfig);
			return ResponseBo.ok(str);
		} catch (Exception e) {
			return ResponseBo.ok("配置失败");
		}
	}

}
