package com.mchaw.tauruspay.bean.eventbus.mainpolling;

import com.mchaw.tauruspay.bean.recharge.RechargeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2019/12/3 17:53
 * @description:充值刷新列表轮询通知
 */
public class MainPollingRechargeEvent {
    public List<RechargeBean> getList() {
        return list;
    }

    public void setList(List<RechargeBean> list) {
        this.list = list;
    }

    List<RechargeBean> list = new ArrayList<>();
}
