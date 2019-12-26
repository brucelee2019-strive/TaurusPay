package com.mchaw.tauruspay.bean.bill;

/**
 * @author Bruce Lee
 * @date : 2019/11/25 18:34
 * @description:
 */
public class BillBean {

    /**
     * orderid : A20191125103853000006
     * amount : 63
     * class : 2
     * subclass : 2
     * update : 2019-11-25 10:11:00
     */

    private String orderid;
    private int amount;
    private int mainclass;
    private int subclass;
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

    public int getMainclass() {
        return mainclass;
    }

    public void setMainclass(int mainclass) {
        this.mainclass = mainclass;
    }

    public int getSubclass() {
        return subclass;
    }

    public void setSubclass(int subclass) {
        this.subclass = subclass;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }
}
