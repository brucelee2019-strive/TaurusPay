package com.mchaw.tauruspay.bean.recharge;

/**
 * @author Bruce Lee
 * @date : 2020/1/10 13:45
 * @description:
 */
public class RechargeAuditBean {

    /**
     * orderid : 000WP
     * amount : 100000
     * status : 1
     * update : 2020-01-10 14:04:49
     */

    private String orderid;
    private String amount;
    private int status;
    private String update;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }
}
