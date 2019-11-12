package com.mchaw.tauruspay.bean.home;

/**
 * @author Bruce Lee
 * @date : 2019/11/12 16:46
 * @description:
 */
public class HomeDataBean {


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
}
