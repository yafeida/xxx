package com.monkey.wx.service;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monkey.common.domain.ResponseBo;
import com.monkey.common.util.DateUtil;
import com.monkey.common.util.IDUtils;
import com.monkey.system.domain.User;
import com.monkey.wx.dao.WxConfigMapper;
import com.monkey.wx.dao.WxUserMapper;
import com.monkey.wx.domain.WxConfig;
import com.monkey.wx.domain.WxUser;
import com.monkey.wx.domain.WxUserExample;
import com.monkey.wx.domain.WxUserExample.Criteria;

@Service
@Transactional
public class WxUserService {
	
	@Autowired
	WxUserMapper wxUserMapper;
	
	@Autowired
	WxConfigMapper wxConfigMapper;

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
	public boolean update(WxUser wxUser) {
		int i= wxUserMapper.updateByPrimaryKeySelective(wxUser);
		if(i>0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 添加配置客户的信息
	 * @param wxConfig
	 */
	@Transactional
	public String addConfig(WxConfig wxConfig) {
		String userID = wxConfig.getId();		 //获取的是用户的ID
		if(userID.equals("") || userID == null) {//判断用户ID是否为空	
			return "配置失败";
		}
		WxUser user =  wxUserMapper.selectByPrimaryKey(userID);
		if(user.getConfigId().equals("") || user.getConfigId() == null ) {//判断ConfigID是否为空，如已配置信息，则不能在进行配置
			String configId = IDUtils.getUUID();	 //生成wx_config的id
			wxConfig.setId(configId);
			int i = wxConfigMapper.insert(wxConfig);
			//配置信息保存成功后，将id添加到user表中的config_id
			if(i>0) {
				WxUser wxUser = new WxUser();//根据userId去添加configId
				wxUser.setId(userID);
				wxUser.setConfigId(configId);
				int j = wxUserMapper.updateByPrimaryKeySelective(wxUser);
				if(j>0) {
					return "配置成功";
				}else {
					 throw new RuntimeException();
				}
			}
			return "配置失败";
		}else {
			return "已配置的客户，如需修改请联系管理员";
		}
	}
}
