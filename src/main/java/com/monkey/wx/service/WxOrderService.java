package com.monkey.wx.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
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
		List<WxOrder> list = wxOrderMapper.selectPageList(wxOrderExample);
		return list;
	}
	
	/**
	 * 根据openid查询指定时间段内的订单
	 * @param req
	 * @return
	 */
	public JSONObject selectMyOrderList(HttpServletRequest req){
		int pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
		int pageSize = Integer.parseInt(req.getParameter("pageSize"));
		String startDate = req.getParameter("startDate")+" 00:00:00";
		String endDate = req.getParameter("endDate")+" 23:59:59";
		String openId = req.getParameter("openId");
		WxOrderExample wxOrderExample = new WxOrderExample();
		wxOrderExample.setOrderByClause("create_time DESC");
		Criteria c = wxOrderExample.createCriteria();
		if(startDate!= null && !startDate.equals("") && endDate!= null && !endDate.equals("") ) {
			c.andCreateTimeBetween(startDate, endDate);
		}
		if(openId!= null && !openId.equals("") ) {
			c.andOpenIdEqualTo(openId);
		}
		int count = wxOrderMapper.countByExample(wxOrderExample);
		int totalPages = count / pageSize;
		if (count % pageSize != 0){
			totalPages ++;
		}
		PageHelper.startPage(pageNumber,pageSize);
		List<WxOrder> list = wxOrderMapper.selectByExample(wxOrderExample);
		JSONArray json = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new HashMap<>();
			map.put("phone",list.get(i).getPhone());
			map.put("fee",list.get(i).getFee());
			map.put("discount",list.get(i).getDisprice());
			if(list.get(i).getCzStatus() == 3) {
				map.put("status","成功");
			}else if(list.get(i).getCzStatus() == 4){
				map.put("status","已退款");
			}else {
				map.put("status","处理中");
			}
			map.put("createTime",list.get(i).getCreateTime());
			json.add(map);
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("myOrder", json);
		jsonObj.put("pageNumber",pageNumber );
		jsonObj.put("pageSize",pageSize );
		jsonObj.put("totalPage",totalPages);
		jsonObj.put("totalRow",count);
		return jsonObj;
	}
	

	
}
