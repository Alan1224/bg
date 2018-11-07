package com.xzf.bg.dto;

/**
 * 关于交易订单展示给用户的信息
 * 实时成功率
 */
public class TOderReq {
    private String cpId;
    private String name;
    private Integer count;
    private Integer success;
    private Double rate;
    private Double rateMinute10;
    private Double rateMinute30;
    private Double rateHOUR;
    private Double fee;
    private Double successFee;

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Double getSuccessFee() {
        return successFee;
    }

    public void setSuccessFee(Double successFee) {
        this.successFee = successFee;
    }

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getRateMinute10() {
        return rateMinute10;
    }

    public void setRateMinute10(Double rateMinute10) {
        this.rateMinute10 = rateMinute10;
    }

    public Double getRateMinute30() {
        return rateMinute30;
    }

    public void setRateMinute30(Double rateMinute30) {
        this.rateMinute30 = rateMinute30;
    }

    public Double getRateHOUR() {
        return rateHOUR;
    }

    public void setRateHOUR(Double rateHOUR) {
        this.rateHOUR = rateHOUR;
    }
}