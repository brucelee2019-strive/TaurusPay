package com.mchaw.tauruspay.bean.recharge;

/**
 * @author Bruce Lee
 * @date : 2019/11/6 16:08
 * @description:
 */
public class RechargeBean {

    /**
     * orderid : 000009
     * amount : 500000
     * status : 3
     * update : 1970-01-01 08:00:00
     */

    private String orderid;
    private int amount;
    private int status;
    private String update;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
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
