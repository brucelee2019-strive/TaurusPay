package com.mchaw.tauruspay.bean.eventbus.mainpolling;

import com.mchaw.tauruspay.bean.home.ReceivablesBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2019/12/3 17:56
 * @description:交易中订单轮询通知
 */
public class MainPollingReceivablesEvent {
    private List<ReceivablesBean> receivables = new ArrayList();

    public List<ReceivablesBean> getReceivables() {
        return receivables;
    }

    public void setReceivables(List<ReceivablesBean> receivables) {
        this.receivables = receivables;
    }
}
