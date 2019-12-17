package com.mchaw.tauruspay.bean.notice;

/**
 * @author Bruce Lee
 * @date : 2019/12/17 9:16
 * @description:
 */
public class NoticeListBean {
    private String noticeid;
    private String cashierid;
    private String title;
    private String content;
    private int type;
    private String created_at;

    public String getNoticeid() {
        return noticeid;
    }

    public void setNoticeid(String noticeid) {
        this.noticeid = noticeid;
    }

    public String getCashierid() {
        return cashierid;
    }

    public void setCashierid(String cashierid) {
        this.cashierid = cashierid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
