package com.mchaw.tauruspay.bean.qrcode;

import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2019/11/13 11:23
 * @description: 二维码库list集合的itemBean
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

    //用来操控展开，收紧按钮。和作为请求二维码档口的判断依据
    private boolean showItems = false;

    //QrcodesBean的list
    private List<GroupinfoBean.QrcodesBean> qrcodes;

    //控制删除按钮是否显示
    private int canDelete;

    //用来操控展开，收紧按钮,能否被点击
    private boolean canClickShowItems = false;

    public boolean isCanClickShowItems() {
        return canClickShowItems;
    }

    public void setCanClickShowItems(boolean canClickShowItems) {
        this.canClickShowItems = canClickShowItems;
    }

    public int getCanDelete() {
        return canDelete;
    }

    public void setCanDelete(int canDelete) {
        this.canDelete = canDelete;
    }

    public List<GroupinfoBean.QrcodesBean> getQrcodes() {
        return qrcodes;
    }

    public void setQrcodes(List<GroupinfoBean.QrcodesBean> qrcodes) {
        this.qrcodes = qrcodes;
    }

    public boolean isShowItems() {
        return showItems;
    }

    public void setShowItems(boolean showItems) {
        this.showItems = showItems;
    }

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
