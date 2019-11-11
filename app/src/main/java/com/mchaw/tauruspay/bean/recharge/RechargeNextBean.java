package com.mchaw.tauruspay.bean.recharge;

/**
 * @author Bruce Lee
 * @date : 2019/11/11 17:00
 * @description:
 */
public class RechargeNextBean {

    /**
     * paytype : 2
     * bank : 建设银行
     * account : 郭英杰
     * bankname : 黄埔支行
     * cardnumber : 1111-2222-3333-4444-56
     * orderid : 00000A
     * amount : 100000
     * remarks : sanmao
     */

    private int paytype;
    private String bank;
    private String account;
    private String bankname;
    private String cardnumber;
    private String orderid;
    private String amount;
    private String remarks;

    public int getPaytype() {
        return paytype;
    }

    public void setPaytype(int paytype) {
        this.paytype = paytype;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
