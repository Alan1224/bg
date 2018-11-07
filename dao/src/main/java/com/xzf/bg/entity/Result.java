package com.xzf.bg.entity;

import org.springframework.stereotype.Component;

public class Result {
    String cpId;
    String ffId;
    double success;
    String fee;
    int count;
    String rate;

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCpId() {
        return cpId;
    }

    public void setCpId(String cpId) {
        this.cpId = cpId;
    }

    public String getFfId() {
        return ffId;
    }

    public void setFfId(String ffId) {
        this.ffId = ffId;
    }

    public Double getSuccess() {
        return success;
    }

    public void setSuccess(double success) {
        this.success = success;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }
}
