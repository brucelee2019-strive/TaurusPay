package com.mchaw.tauruspay.bean.agency;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2020/1/11 14:17
 * @description:
 */
public class AgencyBean {
    private AgencyUser agencyUser;
    private List<AgencyItemBean> list = new ArrayList<>();

    public AgencyUser getAgencyUser() {
        return agencyUser;
    }

    public void setAgencyUser(AgencyUser agencyUser) {
        this.agencyUser = agencyUser;
    }

    public List<AgencyItemBean> getList() {
        return list;
    }

    public void setList(List<AgencyItemBean> list) {
        this.list = list;
    }
}
