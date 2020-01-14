package com.mchaw.tauruspay.bean.agency;

/**
 * @author Bruce Lee
 * @date : 2020/1/14 17:18
 * @description:
 */
public class RateBean {

    /**
     * min : 0
     * max : 10000000
     * rate : 9
     */

    private String min;
    private String max;
    private int rate;

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
