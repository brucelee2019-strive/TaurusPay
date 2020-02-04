package com.mchaw.tauruspay.bean.agency;

/**
 * @author Bruce Lee
 * @date : 2020/1/11 14:18
 * @description:
 */
public class AgencyUser {


    /**
     * name : wu888888
     * point : 0
     * rate : 6
     * code : 2021
     * ydaypoint : 277
     * ydaydeposit : 138900
     * daypoint : 0
     * daydeposit : 0
     */

    private String name;
    private int point;
    private int rate;
    private String code;
    private String ydaypoint;
    private String ydaydeposit;
    private String daypoint;
    private String daydeposit;
    private String quota;

    public String getQuota(){
        return quota;
    }

    public void setQuota(String quota){
        this.quota = quota;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getYdaypoint() {
        return ydaypoint;
    }

    public void setYdaypoint(String ydaypoint) {
        this.ydaypoint = ydaypoint;
    }

    public String getYdaydeposit() {
        return ydaydeposit;
    }

    public void setYdaydeposit(String ydaydeposit) {
        this.ydaydeposit = ydaydeposit;
    }

    public String getDaypoint() {
        return daypoint;
    }

    public void setDaypoint(String daypoint) {
        this.daypoint = daypoint;
    }

    public String getDaydeposit() {
        return daydeposit;
    }

    public void setDaydeposit(String daydeposit) {
        this.daydeposit = daydeposit;
    }
}
