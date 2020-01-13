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
    private List<AgencyItemBean> list = new ArrayList<>();

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
