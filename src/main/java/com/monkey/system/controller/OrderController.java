package com.monkey.system.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.monkey.common.annotation.Log;
import com.monkey.common.controller.BaseController;
import com.monkey.system.domain.User;

@Controller
public class OrderController extends BaseController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Log("获取订单信息")
	@RequestMapping("order")
    @RequiresPermissions("order:list")
    public String index() {
        return "system/order/order";
    }
	
	
}
