package com.mchaw.tauruspay.main.constract;

import com.mchaw.tauruspay.base.mvp.presenter.BasePresenter;
import com.mchaw.tauruspay.base.mvp.view.BaseView;
import com.mchaw.tauruspay.bean.MainPollingBean;
import com.mchaw.tauruspay.bean.notice.NoticeBean;
import com.mchaw.tauruspay.bean.recharge.RechargeAuditBean;

import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2019/12/3 15:25
 * @description:
 */
public interface MainConstract {
    interface View extends BaseView {
        void setMainPollingBean(MainPollingBean bean);
        void setUpLodingReceivables();
        void setNotice(NoticeBean noticeBean);
        void setRechargeAuditList(List<RechargeAuditBean> list);
    }

    interface Presenter extends BasePresenter<MainConstract.View> {
        void getMainPollingBean(String api_token,int groupid);
        void upLodingReceivables(String codeId,String api_token);
        void getNotice(String api_token,String noticeId);
        void getRechargeAuditList(String api_token,int type,int page);
    }
}
