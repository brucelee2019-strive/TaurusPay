package com.mchaw.tauruspay.bean.notice;

import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2019/12/17 9:14
 * @description:
 */
public class NoticeBean {

    /**
     * list : [{"noticeid":"15764898618","cashierid":"8","title":"测试","content":"测试一下亲","type":0,"created_at":"2019-12-16 17:12:01"}]
     * notice : 1
     */

    private int notice;
    private List<NoticeListBean> list;

    public int getNotice() {
        return notice;
    }

    public void setNotice(int notice) {
        this.notice = notice;
    }

    public List<NoticeListBean> getList() {
        return list;
    }

    public void setList(List<NoticeListBean> list) {
        this.list = list;
    }
}
