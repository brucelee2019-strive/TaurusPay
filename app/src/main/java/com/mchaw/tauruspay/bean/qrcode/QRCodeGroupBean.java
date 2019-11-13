package com.mchaw.tauruspay.bean.qrcode;

/**
 * @author Bruce Lee
 * @date : 2019/11/13 11:23
 * @description:
 */
public class QRCodeGroupBean {

    /**
     * id : 1
     * paytype : 1
     * account : sanmao
     * nick : 三毛
     * status : 0
     * sellamount : 0
     * sellcount : 0
     */

    private int id;
    private int paytype;
    private String account;
    private String nick;
    private int status;
    private int sellamount;
    private int sellcount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
