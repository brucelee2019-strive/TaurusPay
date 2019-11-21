package com.mchaw.tauruspay.bean.home;

/**
 * @author Bruce Lee
 * @date : 2019/11/7 14:31
 * @description:
 */
public class SellingOrderBean {

    /**
     * id : 2
     * timeout : 300
     * time : 1574320705
     * amount : 30300
     * codeid : 2
     * status : 0
     */

    private int id;
    private int timeout;
    private int time;
    private int amount;
    private String codeid;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCodeid() {
        return codeid;
    }

    public void setCodeid(String codeid) {
        this.codeid = codeid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
