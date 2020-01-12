package com.mchaw.tauruspay.bean.agency;

/**
 * @author Bruce Lee
 * @date : 2020/1/11 14:17
 * @description:
 */
public class AgencyItemBean {

    /**
     * id : 9
     * name : wu123124
     * rate : 5
     * ydaypoint : 0
     * ydaydeposit : 0
     * daypoint : 0
     * daydeposit : 0
     */

    private String id;
    private String name;
    private int rate;
    private String ydaypoint;
    private String ydaydeposit;
    private String daypoint;
    private String daydeposit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
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
