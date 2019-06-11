package com.monkey.wx.domain;

public class WxOrder {
    private String id;

    private String userId;

    private String orderId;

    private String upOrderId;

    private String openId;

    private String ip;

    private String phone;

    private Double fee;

    private Double disprice;

    private Integer zfStatus;

    private String zfMsg;

    private String zfTime;

    private String transactionId;

    private Integer czStatus;

    private String czMsg;

    private String czTime;

    private String agent;

    private String createTime;

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