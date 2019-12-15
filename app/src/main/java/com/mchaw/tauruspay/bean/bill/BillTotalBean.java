package com.mchaw.tauruspay.bean.bill;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2019/12/15 13:54
 * @description:
 */
public class BillTotalBean {
    private String profit;
    private String singular;
    private String forsale;

    public String getForsale() {
        return forsale;
    }

    public void setForsale(String forsale) {
        this.forsale = forsale;
    }

    private List<BillBean> list = new ArrayList<>();

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public String getSingular() {
        return singular;
    }

    public void setSingular(String singular) {
        this.singular = singular;
    }

    public List<BillBean> getList() {
        return list;
    }

    public void setList(List<BillBean> list) {
        this.list = list;
    }
}
