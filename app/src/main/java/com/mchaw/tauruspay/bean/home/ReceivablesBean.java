package com.mchaw.tauruspay.bean.home;

/**
 * @author Bruce Lee
 * @date : 2019/11/7 14:31
 * @description:
 */
public class ReceivablesBean {
    private int id;
    private String orderid;
    private int timeout;
    private String time;
    private int amount;
    private String codeid;
    private String status;
    private int type;
    private int endtime;
    private boolean sureBtn = false;

    public boolean getSureBean(){
        return sureBtn;
    }

    public void setSureBtn(boolean sureBtn){
        this.sureBtn = sureBtn;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getEndtime() {
        return endtime;
    }

    public void setEndtime(int endtime) {
        this.endtime = endtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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
