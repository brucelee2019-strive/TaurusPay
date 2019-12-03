package com.mchaw.tauruspay.bean;

import com.mchaw.tauruspay.bean.home.ReceivablesBean;
import com.mchaw.tauruspay.bean.home.UserBean;
import com.mchaw.tauruspay.bean.qrcode.GroupinfoBean;
import com.mchaw.tauruspay.bean.recharge.RechargeBean;

import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2019/12/3 14:31
 * @description:
 */
public class MainPollingBean {

    /**
     * user : {"deposit":"768156","dayamount":0,"daycount":0,"daypoint":"0","daydeposit":"0","state":1}
     * recharge : [{"orderid":"000003","amount":1000000,"status":4,"update":"2019-12-02 13:12:37"}]
     * receivables : [{"id":156,"orderid":"A20191203022713000156","timeout":300,"time":1575354434,"amount":31300,"codeid":"27","status":"0"},{"id":157,"orderid":"A20191203022717000157","timeout":300,"time":1575354438,"amount":30300,"codeid":"26","status":"0"},{"id":158,"orderid":"A20191203022722000158","timeout":300,"time":1575354442,"amount":78500,"codeid":"28","status":"0"}]
     * groupinfo : {"groupid":"3","paytype":"1","account":"wu123123","nick":"wu","status":"1","dayamount":"3859900","daycount":"13","qrcodes":[{"id":"26","quota":"30300","status":"5","scancode":"https://qr.alipay.com/fkx08313edwjcyhko0uuy1f","readme":"selling"},{"id":"27","quota":"31300","status":"5","scancode":"https://qr.alipay.com/fkx08313edwjcyhko0uuy1f","readme":"selling"},{"id":"28","quota":"78500","status":"5","scancode":"https://qr.alipay.com/fkx08313edwjcyhko0uuy1f","readme":"selling"},{"id":"29","quota":"78600","status":"4","scancode":"https://qr.alipay.com/fkx08313edwjcyhko0uuy1f","readme":"selling"},{"id":"30","quota":"121500","status":"4","scancode":"https://qr.alipay.com/fkx08313edwjcyhko0uuy1f","readme":"selling"},{"id":"31","quota":"121600","status":"4","scancode":"https://qr.alipay.com/fkx08313edwjcyhko0uuy1f","readme":"selling"},{"id":"32","quota":"251500","status":"4","scancode":"https://qr.alipay.com/fkx08313edwjcyhko0uuy1f","readme":"selling"},{"id":"33","quota":"251600","status":"4","scancode":"https://qr.alipay.com/fkx08313edwjcyhko0uuy1f","readme":"selling"},{"id":"34","quota":"498500","status":"4","scancode":"https://qr.alipay.com/fkx08313edwjcyhko0uuy1f","readme":"selling"},{"id":"35","quota":"498800","status":"4","scancode":"https://qr.alipay.com/fkx08313edwjcyhko0uuy1f","readme":"selling"},{"id":"36","quota":"798800","status":"4","scancode":"https://qr.alipay.com/fkx08313edwjcyhko0uuy1f","readme":"selling"},{"id":"37","quota":"998800","status":"4","scancode":"https://qr.alipay.com/fkx08313edwjcyhko0uuy1f","readme":"selling"}]}
     * active : true
     */

    private UserBean user;
    private GroupinfoBean groupinfo;
    private boolean active;
    private List<RechargeBean> recharge;
    private List<ReceivablesBean> receivables;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public GroupinfoBean getGroupinfo() {
        return groupinfo;
    }

    public void setGroupinfo(GroupinfoBean groupinfo) {
        this.groupinfo = groupinfo;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<RechargeBean> getRecharge() {
        return recharge;
    }

    public void setRecharge(List<RechargeBean> recharge) {
        this.recharge = recharge;
    }

    public List<ReceivablesBean> getReceivables() {
        return receivables;
    }

    public void setReceivables(List<ReceivablesBean> receivables) {
        this.receivables = receivables;
    }
}
