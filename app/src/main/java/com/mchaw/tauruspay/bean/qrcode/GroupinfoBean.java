package com.mchaw.tauruspay.bean.qrcode;

import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2019/11/15 18:41
 * @description: 二维码组Item
 */
public class GroupinfoBean {

    private int groupid;
    private int paytype;
    private String account;
    private String nick;
    private int status;
    private String dayamount;
    private String daycount;
    private List<GroupinfoBean.QrcodesBean> qrcodes;

    //用来操控展开，收紧按钮。和作为请求二维码档口的判断依据
    private boolean showItems = false;

    //控制删除按钮是否显示
    private int canDelete;

    //用来操控展开，收紧按钮,能否被点击
    private boolean canClickShowItems = false;

    public boolean isShowItems() {
        return showItems;
    }

    public void setShowItems(boolean showItems) {
        this.showItems = showItems;
    }

    public int getCanDelete() {
        return canDelete;
    }

    public void setCanDelete(int canDelete) {
        this.canDelete = canDelete;
    }

    public boolean isCanClickShowItems() {
        return canClickShowItems;
    }

    public void setCanClickShowItems(boolean canClickShowItems) {
        this.canClickShowItems = canClickShowItems;
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public int getPaytype() {
        return paytype;
    }

    public void setPaytype(int paytype) {
        this.paytype = paytype;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDayamount() {
        return dayamount;
    }

    public void setDayamount(String dayamount) {
        this.dayamount = dayamount;
    }

    public String getDaycount() {
        return daycount;
    }

    public void setDaycount(String daycount) {
        this.daycount = daycount;
    }

    public List<GroupinfoBean.QrcodesBean> getQrcodes() {
        return qrcodes;
    }

    public void setQrcodes(List<GroupinfoBean.QrcodesBean> qrcodes) {
        this.qrcodes = qrcodes;
    }

    public static class QrcodesBean {
        /**
         * id : 26
         * quota : 30300
         * status : 5
         * scancode : https://qr.alipay.com/fkx08313edwjcyhko0uuy1f
         * readme : selling
         */

        private String id;
        private int quota;
        private int status;
        private String scancode;
        private String readme;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getQuota() {
            return quota;
        }

        public void setQuota(int quota) {
            this.quota = quota;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getScancode() {
            return scancode;
        }

        public void setScancode(String scancode) {
            this.scancode = scancode;
        }

        public String getReadme() {
            return readme;
        }

        public void setReadme(String readme) {
            this.readme = readme;
        }
    }
}
