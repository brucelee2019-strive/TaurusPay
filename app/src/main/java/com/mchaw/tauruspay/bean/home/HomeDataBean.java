package com.mchaw.tauruspay.bean.home;

/**
 * @author Bruce Lee
 * @date : 2019/11/12 16:46
 * @description:
 */
public class HomeDataBean {

    /**
     * id : null
     * deposit : 4880000
     * credit : null
     * sellcount : null
     * sellamount : null
     * point : null
     * daycount : 0
     * dayamount : 0
     * daypoint : 0
     * daydeposit : 0
     */

    private Object id;
    private int deposit;
    private Object credit;
    private Object sellcount;
    private Object sellamount;
    private Object point;
    private int daycount;
    private int dayamount;
    private int daypoint;
    private int daydeposit;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public Object getCredit() {
        return credit;
    }

    public void setCredit(Object credit) {
        this.credit = credit;
    }

    public Object getSellcount() {
        return sellcount;
    }

    public void setSellcount(Object sellcount) {
        this.sellcount = sellcount;
    }

    public Object getSellamount() {
        return sellamount;
    }

    public void setSellamount(Object sellamount) {
        this.sellamount = sellamount;
    }

    public Object getPoint() {
        return point;
    }

    public void setPoint(Object point) {
        this.point = point;
    }

    public int getDaycount() {
        return daycount;
    }

    public void setDaycount(int daycount) {
        this.daycount = daycount;
    }

    public int getDayamount() {
        return dayamount;
    }

    public void setDayamount(int dayamount) {
        this.dayamount = dayamount;
    }

    public int getDaypoint() {
        return daypoint;
    }

    public void setDaypoint(int daypoint) {
        this.daypoint = daypoint;
    }

    public int getDaydeposit() {
        return daydeposit;
    }

    public void setDaydeposit(int daydeposit) {
        this.daydeposit = daydeposit;
    }
}
