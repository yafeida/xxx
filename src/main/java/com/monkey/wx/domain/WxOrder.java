package com.monkey.wx.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.monkey.common.annotation.ExportConfig;

public class WxOrder {
	
	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name = "ID")
    private String id;
	
	@Column(name = "USERID")
    private String userId;
	
	@Column(name = "ORDERID")
	@ExportConfig(value = "平台订单号")
    private String orderId;
	
	@Column(name = "UPORDERID")
	@ExportConfig(value = "微信流水号")
    private String upOrderId;

	@Column(name = "OPENID")
    private String openId;
	
	@Column(name = "IP")
    private String ip;

	@Column(name = "PHONE")
	@ExportConfig(value = "手机号")
    private String phone;

	@Column(name = "FEE")
	@ExportConfig(value = "充值金额")
    private Double fee;

	@Column(name = "DISPRICE")
	@ExportConfig(value = "折扣")
    private Double disprice;

	@Column(name = "ZFSTATUS")
	@ExportConfig(value = "支付状态码")
    private Integer zfStatus;

	@Column(name = "ZFMSG")
	@ExportConfig(value = "支付状态")
    private String zfMsg;

	@Column(name = "ZFTIME")
	@ExportConfig(value = "支付时间")
    private String zfTime;

	@Column(name = "ZFCALLBACKTIME")
	@ExportConfig(value = "支付回调时间")
    private String zfCallbackTime;

	@Column(name = "TRANSACTIONID")
	@ExportConfig(value = "支付流水号")
    private String transactionId;

	@Column(name = "CZSTATUS")
	@ExportConfig(value = "充值状态码")
    private Integer czStatus;

	@Column(name = "CZMSG")
	@ExportConfig(value = "充值状态")
    private String czMsg;

	@Column(name = "CZTIME")
	@ExportConfig(value = "充值时间")
    private String czTime;

	@Column(name = "CZCALLBACKTIME")
	@ExportConfig(value = "充值回调时间")
    private String czCallbackTime;

	@Column(name = "AGENT")
	@ExportConfig(value = "代理商标识")
    private String agent;

	@Column(name = "CREATETIME")
	@ExportConfig(value = "创建时间")
    private String createTime;
    
	@Column(name = "WXNAME")
	@ExportConfig(value = "用户名称")
    private String wxName;
    
    public String getWxName() {
        return wxName;
    }

    public void setWxName(String wxName) {
        this.wxName = wxName == null ? null : wxName.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getUpOrderId() {
        return upOrderId;
    }

    public void setUpOrderId(String upOrderId) {
        this.upOrderId = upOrderId == null ? null : upOrderId.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Double getDisprice() {
        return disprice;
    }

    public void setDisprice(Double disprice) {
        this.disprice = disprice;
    }

    public Integer getZfStatus() {
        return zfStatus;
    }

    public void setZfStatus(Integer zfStatus) {
        this.zfStatus = zfStatus;
    }

    public String getZfMsg() {
        return zfMsg;
    }

    public void setZfMsg(String zfMsg) {
        this.zfMsg = zfMsg == null ? null : zfMsg.trim();
    }

    public String getZfTime() {
        return zfTime;
    }

    public void setZfTime(String zfTime) {
        this.zfTime = zfTime == null ? null : zfTime.trim();
    }

    public String getZfCallbackTime() {
        return zfCallbackTime;
    }

    public void setZfCallbackTime(String zfCallbackTime) {
        this.zfCallbackTime = zfCallbackTime == null ? null : zfCallbackTime.trim();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId == null ? null : transactionId.trim();
    }

    public Integer getCzStatus() {
        return czStatus;
    }

    public void setCzStatus(Integer czStatus) {
        this.czStatus = czStatus;
    }

    public String getCzMsg() {
        return czMsg;
    }

    public void setCzMsg(String czMsg) {
        this.czMsg = czMsg == null ? null : czMsg.trim();
    }

    public String getCzTime() {
        return czTime;
    }

    public void setCzTime(String czTime) {
        this.czTime = czTime == null ? null : czTime.trim();
    }

    public String getCzCallbackTime() {
        return czCallbackTime;
    }

    public void setCzCallbackTime(String czCallbackTime) {
        this.czCallbackTime = czCallbackTime == null ? null : czCallbackTime.trim();
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent == null ? null : agent.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }
}