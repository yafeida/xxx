package com.monkey.job.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.monkey.common.annotation.CronTag;
import com.monkey.common.util.HttpUtils;
import com.monkey.wx.dao.WxConfigMapper;
import com.monkey.wx.dao.WxOrderMapper;
import com.monkey.wx.dao.WxUserMapper;
import com.monkey.wx.domain.WxOrder;
import com.monkey.wx.domain.WxOrderExample;
import com.monkey.wx.domain.WxUser;

@CronTag("bosspayBillTask")
public class BosspayBillTask {

	private static Logger log = LoggerFactory.getLogger(BosspayBillTask.class);
	
	private static String query_url = "http://118.31.229.23:8080/bosspaybill/order/queryOrder";
	
	@Autowired
	WxOrderMapper wxOrderMapper;
	@Autowired
	WxUserMapper wxUserMapper;
	@Autowired
	WxConfigMapper wxConfigMapper;
	
	public void queryOrder(){
		//查询出状态为提交成功的订单
		WxOrderExample woEx = new WxOrderExample();
		woEx.createCriteria().andCzStatusEqualTo(1);
		List<WxOrder> list = wxOrderMapper.selectByExample(woEx);
		log.info("查询出"+list.size()+"条提交成功订单，开始执行查询");
		for(WxOrder wxOrder : list){
			String orderId = wxOrder.getOrderId();//订单号
			log.info("订单号为"+orderId+"的订单开始执行查询");
			WxUser wxUser = wxUserMapper.selectByPrimaryKey(wxOrder.getUserId());
			String billName = wxUser.getBillName();
			String paramStr = "terminalName="+billName+"&customerOrderId="+orderId;
			String result = HttpUtils.get(query_url+"?"+paramStr);
			log.info("订单号为"+orderId+"的订单执行查询返回"+result);
			JSONObject resultJson = JSONObject.parseObject(result);
			String code = resultJson.getString("code");
			if("0".equals(code)){
				JSONObject data = resultJson.getJSONObject("data");
				String status = data.getString("status");
				String message = data.getString("message");
				wxOrder.setCzMsg(wxOrder.getCzMsg()+"|"+message);
				if("0".equals(status)){//成功
					wxOrder.setCzStatus(3);
				}else if("1".equals(status)){
					continue;
				}else{
					wxOrder.setCzStatus(4);
				}
				int x = wxOrderMapper.updateByPrimaryKeySelective(wxOrder);
				if(x > 0){
					log.info("订单号为"+orderId+"的订单执行更新成功");
				}
			}
		}
	}
	
}
