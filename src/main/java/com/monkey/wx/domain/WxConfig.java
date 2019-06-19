package com.monkey.wx.domain;

public class WxConfig {
    private String id;

    private String appId;

    private String appSecret;

    private String token;

    private String aeskey;

    private String mchId;

    private String mchKey;

    private String certPath;

    private String zfType;

    private String zfInfo;

    private String templateBillSuccess;

    private String templateBillFail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret == null ? null : appSecret.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getAeskey() {
        return aeskey;
    }

    public void setAeskey(String aeskey) {
        this.aeskey = aeskey == null ? null : aeskey.trim();
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId == null ? null : mchId.trim();
    }

    public String getMchKey() {
        return mchKey;
    }

    public void setMchKey(String mchKey) {
        this.mchKey = mchKey == null ? null : mchKey.trim();
    }

    public String getCertPath() {
        return certPath;
    }

    public void setCertPath(String certPath) {
        this.certPath = certPath == null ? null : certPath.trim();
    }

    public String getZfType() {
        return zfType;
    }

    public void setZfType(String zfType) {
        this.zfType = zfType == null ? null : zfType.trim();
    }

    public String getZfInfo() {
        return zfInfo;
    }

    public void setZfInfo(String zfInfo) {
        this.zfInfo = zfInfo == null ? null : zfInfo.trim();
    }

    public String getTemplateBillSuccess() {
        return templateBillSuccess;
    }

    public void setTemplateBillSuccess(String templateBillSuccess) {
        this.templateBillSuccess = templateBillSuccess == null ? null : templateBillSuccess.trim();
    }

    public String getTemplateBillFail() {
        return templateBillFail;
    }

    public void setTemplateBillFail(String templateBillFail) {
        this.templateBillFail = templateBillFail == null ? null : templateBillFail.trim();
    }
}