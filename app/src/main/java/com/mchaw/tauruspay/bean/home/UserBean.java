package com.mchaw.tauruspay.bean.home;

/**
 * @author Bruce Lee
 * @date : 2019/11/12 16:46
 * @description:
 */
public class UserBean {


    /**
     * deposit : 4880000
     * dayamount : 0
     * daycount : 0
     * daypoint : 0
     * daydeposit : 0
     */

    private long deposit;
    private long dayamount;
    private long daycount;
    private long daypoint;
    private long daydeposit;
    private long dayonsale;
    private int state;
    private int groupid;
    private int frozen;
    private String alipay1;
    private String alipay2;
    private String wechat1;
    private String wechat2;
    private int type;
    private String code;
    private int rate;
    private long quota;

    public long getQuota() {
        return quota;
    }

    public void setQuota(long quota) {
        this.quota = quota;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getFrozen() {
        return frozen;
    }

    public void setFrozen(int frozen) {
        this.frozen = frozen;
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getDeposit() {
        return deposit;
    }

    public void setDeposit(long deposit) {
        this.deposit = deposit;
    }

    public long getDayamount() {
        return dayamount;
    }

    public void setDayamount(long dayamount) {
        this.dayamount = dayamount;
    }

    public long getDaycount() {
        return daycount;
    }

    public void setDaycount(long daycount) {
        this.daycount = daycount;
    }

    public long getDaypoint() {
        return daypoint;
    }

    public void setDaypoint(long daypoint) {
        this.daypoint = daypoint;
    }

    public long getDaydeposit() {
        return daydeposit;
    }

    public void setDaydeposit(long daydeposit) {
        this.daydeposit = daydeposit;
    }

    public long getDayonsale() {
        return dayonsale;
    }

    public void setDayonsale(long dayonsale) {
        this.dayonsale = dayonsale;
    }

    public String getAlipay1() {
        return alipay1;
    }

    public String getAlipay2() {
        return alipay2;
    }

    public String getWechat1() {
        return wechat1;
    }

    public String getWechat2() {
        return wechat2;
    }
}
