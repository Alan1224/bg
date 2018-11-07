package com.xzf.bg.entity;

import org.springframework.stereotype.Component;

public class TOrderReq {
    private String ffId ;//平台单号
    private String cpId ;//下有商户号
    private String imsi ;//
    private String imei ;//
    private String iccid ;//
    private String mobile ;//
    private String spId ;//上游商户号
    private String pmodel ;//
    private String osversion ;//
    private String nwtype ;//
    private String ctech ;//
    private Integer fee ;//金额
    private String ip ;//
    private String province ;//
    private String cpParam ;//下游单号
    private String isSyn ;//
    private Integer isSuccess ;//成功1
    private String synStatus ;//成功1
    private String sdkResult ;//成功1
    private String pck ;//
    private String app ;//
    private String sdkVer ;//
    private java.util.Date createTime ;//订单创建时间
    private java.util.Date updateTime ;//订单回调时间

    public String getFfId() {
        return ffId;
    }

    public void setFfId(String ffId) {
        this.ffId = ffId;
    }

    public String getCpId() {
        return cpId;
    }

    public void setCpId(String cpId) {
        this.cpId = cpId;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId;
    }

    public String getPmodel() {
        return pmodel;
    }

    public void setPmodel(String pmodel) {
        this.pmodel = pmodel;
    }

    public String getOsversion() {
        return osversion;
    }

    public void setOsversion(String osversion) {
        this.osversion = osversion;
    }

    public String getNwtype() {
        return nwtype;
    }

    public void setNwtype(String nwtype) {
        this.nwtype = nwtype;
    }

    public String getCtech() {
        return ctech;
    }

    public void setCtech(String ctech) {
        this.ctech = ctech;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCpParam() {
        return cpParam;
    }

    public void setCpParam(String cpParam) {
        this.cpParam = cpParam;
    }

    public String getIsSyn() {
        return isSyn;
    }

    public void setIsSyn(String isSyn) {
        this.isSyn = isSyn;
    }

    public Integer getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Integer isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getSynStatus() {
        return synStatus;
    }

    public void setSynStatus(String synStatus) {
        this.synStatus = synStatus;
    }

    public String getSdkResult() {
        return sdkResult;
    }

    public void setSdkResult(String sdkResult) {
        this.sdkResult = sdkResult;
    }

    public String getPck() {
        return pck;
    }

    public void setPck(String pck) {
        this.pck = pck;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getSdkVer() {
        return sdkVer;
    }

    public void setSdkVer(String sdkVer) {
        this.sdkVer = sdkVer;
    }

    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.util.Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }
}
