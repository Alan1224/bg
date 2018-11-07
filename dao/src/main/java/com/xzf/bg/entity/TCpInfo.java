package com.xzf.bg.entity;

import org.springframework.stereotype.Component;

public class TCpInfo {
    private String cpId;
    private String name;
    private String cpFlag;
    private Integer isOpen;
    private String url;
    private String proport;
    private Integer paymentType;
    private Integer synRadio;
    private Integer isMagic;
    private Integer magicRadio;
    private String localSpId;
    private String magicSpId;
    private String memo;
    private Integer backMethod;
    private Integer isShow;
    private Integer cityType;
    private String cityHide;
    private Integer synOpen;
    private String synProvince;
    private String synNum;
    private Double money;
    private Double rate;


    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

//    public TCpInfo(String name, String url, String localSpId) {
//        this.name = name;
//        this.url = url;
//        this.localSpId = localSpId;
//    }

    public String getCpId() {
        return cpId;
    }

    public void setCpId(String cpId) {
        this.cpId = cpId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpFlag() {
        return cpFlag;
    }

    public void setCpFlag(String cpFlag) {
        this.cpFlag = cpFlag;
    }

    public Integer getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProport() {
        return proport;
    }

    public void setProport(String proport) {
        this.proport = proport;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getSynRadio() {
        return synRadio;
    }

    public void setSynRadio(Integer synRadio) {
        this.synRadio = synRadio;
    }

    public Integer getIsMagic() {
        return isMagic;
    }

    public void setIsMagic(Integer isMagic) {
        this.isMagic = isMagic;
    }

    public Integer getMagicRadio() {
        return magicRadio;
    }

    public void setMagicRadio(Integer magicRadio) {
        this.magicRadio = magicRadio;
    }

    public String getLocalSpId() {
        return localSpId;
    }

    public void setLocalSpId(String localSpId) {
        this.localSpId = localSpId;
    }

    public String getMagicSpId() {
        return magicSpId;
    }

    public void setMagicSpId(String magicSpId) {
        this.magicSpId = magicSpId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getBackMethod() {
        return backMethod;
    }

    public void setBackMethod(Integer backMethod) {
        this.backMethod = backMethod;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getCityType() {
        return cityType;
    }

    public void setCityType(Integer cityType) {
        this.cityType = cityType;
    }

    public String getCityHide() {
        return cityHide;
    }

    public void setCityHide(String cityHide) {
        this.cityHide = cityHide;
    }

    public Integer getSynOpen() {
        return synOpen;
    }

    public void setSynOpen(Integer synOpen) {
        this.synOpen = synOpen;
    }

    public String getSynProvince() {
        return synProvince;
    }

    public void setSynProvince(String synProvince) {
        this.synProvince = synProvince;
    }

    public String getSynNum() {
        return synNum;
    }

    public void setSynNum(String synNum) {
        this.synNum = synNum;
    }
}
