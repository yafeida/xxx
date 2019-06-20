package com.monkey.wx.service;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.monkey.wx.dao.WxConfigMapper;
import com.monkey.wx.domain.WxConfig;
import com.monkey.wx.domain.WxUser;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;


public class TemplateService {
private static Logger log = LoggerFactory.getLogger(TemplateService.class);
	
	protected  WxMpService wxService;
	
	@Autowired
	public WxConfigMapper wxConfigMapper;
	
	@Autowired
    public TemplateService(WxMpService wxService) {
        this.wxService = wxService;
    }
	
	/**
	 * 充值成功模板消息 
	 * @param wxUser	
	 * @param openId
	 * @param phone
	 * @param miane	面值
	 * @param money	折扣价
	 * @throws WxErrorException
	 */
	public void sendBillSuccessMsg(WxUser wxUser,String openId,String phone,String miane,String money) throws WxErrorException {
		//查询用户的配置信息
		WxConfig wxConfig = wxConfigMapper.selectByPrimaryKey(wxUser.getConfigId());
		
		WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
				.toUser(openId)							//接收的用户的openId
				.templateId(wxConfig.getTemplateBillSuccess())		//模板编号
				.url("")									//点击详情跳转地址
				.build();
		templateMessage.addData(new WxMpTemplateData("keyword1","话费", "#FF00FF"))
		.addData(new WxMpTemplateData("keyword2","17700000000", "#FF00FF"))
		.addData(new WxMpTemplateData("keyword3","100", "#FF00FF"));
		
/*		.addData(new WxMpTemplateData("","尊敬的客户您好，感谢您使用" + wxUser.getWxName() + "进行充值，您的话费已经充值成功。", "#173177"))
		.addData(new WxMpTemplateData("","充值号码", "#000000"))
		.addData(new WxMpTemplateData("","phone", "#000000"))
		.addData(new WxMpTemplateData("","[面额:" + miane + "],支付:" + money + "元", "#000000"))
		.addData(new WxMpTemplateData("","充值成功", "#000000"))
		.addData(new WxMpTemplateData("","欢迎再次充值！如有疑问可直接拨打客服热线" + wxUser.getKfPhone() + ",我们将竭诚为你服务。", "#000000"));*/
		
		String msgId = this.wxService.getTemplateMsgService().sendTemplateMsg(templateMessage);
		Assert.assertNotNull(msgId);
		System.out.println(msgId);
	}
	
	/**
	 * 充值失败模板消息 
	 * @param wxUser	
	 * @param openId
	 * @param phone
	 * @param miane	面值
	 * @param money	折扣价
	 * @throws WxErrorException
	 */
	public void sendBillFailMsg(WxUser wxUser,String openId,String phone,String miane,String money) throws WxErrorException {
		
		//查询用户的配置信息
		WxConfig wxConfig = wxConfigMapper.selectByPrimaryKey(wxUser.getConfigId());
		
		WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
				.toUser("ojGTc5sHG7mYp7qOkqHkr19rYNyA")							//接收的用户的openId
				.templateId(wxConfig.getTemplateBillFail())						//模板编号
				.url("https://www.baidu.com")									//点击详情跳转地址
				.build();
		templateMessage.addData(new WxMpTemplateData("keyword1","话费", "#FF00FF"))
		.addData(new WxMpTemplateData("keyword2","17700000000", "#FF00FF"))
		.addData(new WxMpTemplateData("keyword3","100", "#FF00FF"));
		
/*		.addData(new WxMpTemplateData("","尊敬的客户您好，感谢您使用" + wxInfo.getPlatformName() + "进行充值，非常抱歉运营商网络繁忙，您的话费没能充值成功，我们已为您办理退款。", "#173177"))
		.addData(new WxMpTemplateData("","phone", "#000000"))
		.addData(new WxMpTemplateData("","[面额:" + miane + "],退款:" + money + "元", "#000000"))
		.addData(new WxMpTemplateData("","退还金额一般会在3个工作日内原路退回，如有疑问可直接拨打客服热线" + wxInfo.getCompanyPhone() + ",我们将竭诚为你服务。", "#173177"));*/
		
		String msgId = this.wxService.getTemplateMsgService().sendTemplateMsg(templateMessage);
		Assert.assertNotNull(msgId);
		System.out.println(msgId);
	}
	
}
