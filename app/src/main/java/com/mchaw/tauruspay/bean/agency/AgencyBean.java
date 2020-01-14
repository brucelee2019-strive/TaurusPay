package com.mchaw.tauruspay.bean.agency;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2020/1/11 14:17
 * @description:
 */
public class AgencyBean {
    private AgencyUser user;
    private List<RateBean> rate = new ArrayList<>();
    private List<AgencyItemBean> list = new ArrayList<>();

    public List<RateBean> getRate() {
        return rate;
    }

    public void setRate(List<RateBean> rate) {
        this.rate = rate;
    }

    public AgencyUser getAgencyUser() {
        return user;
    }

    public void setAgencyUser(AgencyUser user) {
        this.user = user;
    }

    public List<AgencyItemBean> getList() {
        return list;
    }

    public void setList(List<AgencyItemBean> list) {
        this.list = list;
    }
}
