package com.xzf.bg.entity;

/**
 * 显示给用户的支付信息
 */
public class InsteadInfo {
    private String cpId;
    private Double dealMoney;
    private Double rate;
    /**
     * 手续费
     */
    private String poundage;
    /**
     * 可提现
     */
    private Double withdraw;

    public String getCpId() {
        return cpId;
    }

    public void setCpId(String cpId) {
        this.cpId = cpId;
    }

    public Double getDealMoney() {
        return dealMoney;
    }

    public void setDealMoney(Double dealMoney) {
        this.dealMoney = dealMoney;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getPoundage() {
        return poundage;
    }

    public void setPoundage(String poundage) {
        this.poundage = poundage;
    }

    public Double getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(Double withdraw) {
        this.withdraw = withdraw;
    }
}
