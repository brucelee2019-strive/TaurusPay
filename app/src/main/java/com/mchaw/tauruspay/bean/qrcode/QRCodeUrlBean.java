package com.mchaw.tauruspay.bean.qrcode;

/**
 * @author Bruce Lee
 * @date : 2019/11/15 16:20
 * @description:
 */
public class QRCodeUrlBean {

    /**
     * id : 13
     * cashierid : 10
     * groupid : 1
     * paytype : 1
     * quota : 998800
     * total : 0
     * paycount : 0
     * scancode : https://qr.alipay.com/fkx08560caqrv098y96dkba
     * bank : null
     * account : null
     * bankname : null
     * cardnumber : null
     * readme : null
     * status : 2
     * created_at : 2019-11-13 10:54:19
     * updated_at : 2019-11-15 20:29:29
     */

    private int id;
    private int cashierid;
    private int groupid;
    private int paytype;
    private int quota;
    private int total;
    private int paycount;
    private String scancode;
    private Object bank;
    private Object account;
    private Object bankname;
    private Object cardnumber;
    private Object readme;
    private int status;
    private String created_at;
    private String updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCashierid() {
        return cashierid;
    }

    public void setCashierid(int cashierid) {
        this.cashierid = cashierid;
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

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPaycount() {
        return paycount;
    }

    public void setPaycount(int paycount) {
        this.paycount = paycount;
    }

    public String getScancode() {
        return scancode;
    }

    public void setScancode(String scancode) {
        this.scancode = scancode;
    }

    public Object getBank() {
        return bank;
    }

    public void setBank(Object bank) {
        this.bank = bank;
    }

    public Object getAccount() {
        return account;
    }

    public void setAccount(Object account) {
        this.account = account;
    }

    public Object getBankname() {
        return bankname;
    }

    public void setBankname(Object bankname) {
        this.bankname = bankname;
    }

    public Object getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(Object cardnumber) {
        this.cardnumber = cardnumber;
    }

    public Object getReadme() {
        return readme;
    }

    public void setReadme(Object readme) {
        this.readme = readme;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
