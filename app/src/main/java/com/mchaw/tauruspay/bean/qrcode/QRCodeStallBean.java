package com.mchaw.tauruspay.bean.qrcode;

import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2019/11/15 18:41
 * @description:
 */
public class QRCodeStallBean {

    /**
     * groupid : 1
     * paytype : 1
     * account : sanmao
     * nick : 三毛
     * status : 0
     * deposit : 4880000
     * sellamount : 0
     * sellcount : 0
     * qrcodes : [{"id":2,"quota":30300,"status":0,"scancode":null,"readme":null,"sellcount":0},{"id":3,"quota":31300,"status":0,"scancode":null,"readme":null,"sellcount":0},{"id":4,"quota":78500,"status":0,"scancode":null,"readme":null,"sellcount":0},{"id":5,"quota":78600,"status":0,"scancode":null,"readme":null,"sellcount":0},{"id":6,"quota":121500,"status":0,"scancode":null,"readme":null,"sellcount":0},{"id":7,"quota":121600,"status":0,"scancode":null,"readme":null,"sellcount":0},{"id":8,"quota":251500,"status":0,"scancode":null,"readme":null,"sellcount":0},{"id":9,"quota":251600,"status":0,"scancode":null,"readme":null,"sellcount":0},{"id":10,"quota":498500,"status":0,"scancode":null,"readme":null,"sellcount":0},{"id":11,"quota":498800,"status":0,"scancode":null,"readme":null,"sellcount":0},{"id":12,"quota":798800,"status":0,"scancode":null,"readme":null,"sellcount":0},{"id":13,"quota":998800,"status":0,"scancode":null,"readme":null,"sellcount":0}]
     */

    private int groupid;
    private int paytype;
    private String account;
    private String nick;
    private int status;
    private int deposit;
    private int sellamount;
    private int sellcount;
    //12个二维码集合
    private List<QrcodesBean> qrcodes;

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

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public int getSellamount() {
        return sellamount;
    }

    public void setSellamount(int sellamount) {
        this.sellamount = sellamount;
    }

    public int getSellcount() {
        return sellcount;
    }

    public void setSellcount(int sellcount) {
        this.sellcount = sellcount;
    }

    public List<QrcodesBean> getQrcodes() {
        return qrcodes;
    }

    public void setQrcodes(List<QrcodesBean> qrcodes) {
        this.qrcodes = qrcodes;
    }

    public static class QrcodesBean {
        /**
         * id : 2
         * quota : 30300
         * status : 0
         * scancode : null
         * readme : null
         * sellcount : 0
         */

        private String id;
        private int quota;
        private int status;
        private Object scancode;
        private Object readme;
        private int sellcount;

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

        public Object getScancode() {
            return scancode;
        }

        public void setScancode(Object scancode) {
            this.scancode = scancode;
        }

        public Object getReadme() {
            return readme;
        }

        public void setReadme(Object readme) {
            this.readme = readme;
        }

        public int getSellcount() {
            return sellcount;
        }

        public void setSellcount(int sellcount) {
            this.sellcount = sellcount;
        }
    }
}
