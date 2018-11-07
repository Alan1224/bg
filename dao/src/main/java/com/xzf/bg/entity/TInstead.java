package com.xzf.bg.entity;

import java.util.Date;

public class TInstead {
    private String insteadId;
    private String cpId;
    private Double money;
    private Integer status;
    private Date createTime;
    private Date updateTime;

    public String getInsteadId() {
        return insteadId;
    }

    public void setInsteadId(String insteadId) {
        this.insteadId = insteadId;
    }

    public String getCpId() {
        return cpId;
    }

    public void setCpId(String cpId) {
        this.cpId = cpId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}