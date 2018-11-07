package com.xzf.bg.entity;

public class RateTime {
    private String cpId;
    private Integer count;
    private Integer success;
    private Integer fee;
    private Integer successFee;
    private String name;

    public Integer getSuccessFee() {
        return successFee;
    }

    public void setSuccessFee(Integer successFee) {
        this.successFee = successFee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpId() {
        return cpId;
    }

    public void setCpId(String cpId) {
        this.cpId = cpId;
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

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }
}
