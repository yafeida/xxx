package com.monkey.wx.service;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monkey.common.util.DateUtil;
import com.monkey.common.util.IDUtils;
import com.monkey.system.domain.User;
import com.monkey.wx.dao.WxUserMapper;
import com.monkey.wx.domain.WxUser;
import com.monkey.wx.domain.WxUserExample;
import com.monkey.wx.domain.WxUserExample.Criteria;

@Service
public class WxUserService {
	
	@Autowired
	WxUserMapper wxUserMapper;

	public List<WxUser> list(WxUser wxUser){
		WxUserExample wuEx = new WxUserExample();
		Criteria c = wuEx.createCriteria().andDelFlagEqualTo(1);
		if(wxUser.getWxName() != null && !"".equals(wxUser.getWxName())){
			c.andWxNameEqualTo(wxUser.getWxName());
		}
		if(wxUser.getChannelPerson() != null && !"".equals(wxUser.getChannelPerson())){
			c.andChannelPersonEqualTo(wxUser.getChannelPerson());
		}
		if(wxUser.getTitle() != null && !"".equals(wxUser.getTitle())){
			c.andTitleEqualTo(wxUser.getTitle());
		}
		return wxUserMapper.selectByExample(wuEx);
	}
	
	public void delete(String ids){
		String[] idArr = ids.split(",");
		for(String id : idArr){
			WxUserExample wuEx = new WxUserExample();
			wuEx.createCriteria().andIdEqualTo(id);
			WxUser wxUser = new WxUser();
			wxUser.setDelFlag(0);
			wxUserMapper.updateByExampleSelective(wxUser, wuEx);
		}
	}
	
	/**
	 * 新增微信客户
	 * @param wxUser
	 */
	public void add(WxUser wxUser){
		wxUser.setId(IDUtils.getRandomId(9));
		wxUser.setCreateTime(DateUtil.getDefaultDateFormat());
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		wxUser.setCreateUser(user.getUsername());
		wxUser.setDelFlag(1);
		wxUserMapper.insertSelective(wxUser);
	}
	
	/**
	 * 根据主键ID查询客户信息
	 * @param id
	 * @return
	 */
	public WxUser findById(String id) {
		WxUser wxUser =  wxUserMapper.selectByPrimaryKey(id);
		return wxUser;
	}
	
	/**
	 * 修改客户信息
	 * @param wxUser
	 * @param id
	 */
	public void update(WxUser wxUser) {
		wxUserMapper.updateByPrimaryKeySelective(wxUser);
	}
	
}
