package com.mchaw.tauruspay.bean.eventbus.mainpolling;

import com.mchaw.tauruspay.bean.qrcode.GroupinfoBean;

/**
 * @author Bruce Lee
 * @date : 2019/12/3 17:58
 * @description:销售中二维码挡位刷新轮询通知
 */
public class MainPollingGroupInfoEvent {
    private GroupinfoBean groupinfo;

    public GroupinfoBean getGroupinfo() {
        return groupinfo;
    }

    public void setGroupinfo(GroupinfoBean groupinfo) {
        this.groupinfo = groupinfo;
    }
}
