package com.monkey.wx.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.monkey.common.annotation.Log;
import com.monkey.common.controller.BaseController;
import com.monkey.common.domain.QueryRequest;
import com.monkey.common.domain.ResponseBo;
import com.monkey.common.util.FileUtils;
import com.monkey.wx.domain.WxOrder;
import com.monkey.wx.service.WxOrderService;					

@Controller
public class WxOrderController extends BaseController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WxOrderService orderService;
	
	@Log("查询订单信息")
	@RequestMapping("order")	
    @RequiresPermissions("order:list")
    public String index() {
        return "wx/order/order";
    }
	
	@Log("获取订单信息")
	@RequestMapping("order/list")
	@ResponseBody
	public Map<String,Object> orderList(QueryRequest request, WxOrder order,HttpServletRequest req){
		PageHelper.startPage(request.getPageNum(), request.getPageSize());
		String createTime = req.getParameter("createTime");		//开始时间
		String endTime = req.getParameter("endTime");			//结束时间
		//根据条件查询
		List<WxOrder> list = orderService.selectAllOrder(order, createTime, endTime);
		PageInfo<WxOrder> pageInfo = new PageInfo<>(list);
        return getDataTable(pageInfo);
	}
	
    @RequestMapping("order/excel")
    @ResponseBody
    public ResponseBo orderExcel(WxOrder wxOrder,HttpServletRequest req) {
        try {
        	String createTime = req.getParameter("createTime");		//开始时间
    		String endTime = req.getParameter("endTime");			//结束时间
        	List<WxOrder> list = orderService.selectAllOrder(wxOrder,createTime,endTime);
            return FileUtils.createExcelByPOIKit("订单表",list, WxOrder.class);
        } catch (Exception e) {
            log.error("导出用户信息Excel失败", e);
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }
    
    @RequestMapping("order/csv")
    @ResponseBody
    public ResponseBo orderCsv(WxOrder wxOrder,HttpServletRequest req) {
        try {
        	String createTime = req.getParameter("createTime");		//开始时间
    		String endTime = req.getParameter("endTime");			//结束时间
            List<WxOrder> list = orderService.selectAllOrder(wxOrder,createTime,endTime);	
            return FileUtils.createCsv("用户表", list, WxOrder.class);
        } catch (Exception e) {
            log.error("导出用户信息Csv失败", e);
            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
        }
    }
    
    /**
     * 查询客户订单
     * @param req
     * @return
     */
    @RequestMapping("order/myOrderList")
    @ResponseBody
    public JSONObject myOrderList(HttpServletRequest req) {
    	return orderService.selectMyOrderList(req);
    }

}
