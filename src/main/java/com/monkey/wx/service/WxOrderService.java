package com.monkey.wx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.monkey.common.service.IService;
import com.monkey.wx.dao.WxOrderMapper;
import com.monkey.wx.domain.WxOrder;
import com.monkey.wx.domain.WxOrderExample;
import com.monkey.wx.domain.WxOrderExample.Criteria;


@Service
public class WxOrderService{
	
	@Autowired
	WxOrderMapper wxOrderMapper;
		
	/**
	 * 查询订单信息
	 * @param wxOrder		页面传入的订单信息
	 * @param createTime	开始时间
	 * @param endTime		结束时间
	 * @return	将符合条件的数据以list集合的方式返回
	 */
	public List<WxOrder> selectAllOrder(WxOrder wxOrder,String createTime,String endTime) {
		// TODO Auto-generated method stub
		WxOrderExample wxOrderExample = new WxOrderExample();
		//对传入的数据进行判断为空则不添加查询，反之则添加查询
		Criteria c = wxOrderExample.createCriteria();
		if(wxOrder.getPhone()!= null && !wxOrder.getPhone().equals("")) {
			c.andPhoneEqualTo(wxOrder.getPhone());
		}
		if(wxOrder.getAgent()!= null && !wxOrder.getAgent().equals("")) {
			c.andAgentEqualTo(wxOrder.getAgent());
		}
		if(wxOrder.getOrderId()!= null && !wxOrder.getOrderId().equals("")) {
			c.andOrderIdEqualTo(wxOrder.getOrderId());
		}
		if(wxOrder.getCzStatus()!= null && !wxOrder.getCzStatus().equals("")) {
			c.andCzStatusEqualTo(wxOrder.getCzStatus());
		}
		if(createTime!= null && !createTime.equals("") && endTime!= null && !endTime.equals("")) {
			c.andCreateTimeBetween(createTime,endTime);
		}
		List<WxOrder> list = wxOrderMapper.selectByExampleTwo(wxOrderExample);
		return list;
	}
	
	
	
}
