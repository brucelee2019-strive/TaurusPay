package com.mchaw.tauruspay.ui.main.mine.notice.constract;

import com.mchaw.tauruspay.base.mvp.presenter.BasePresenter;
import com.mchaw.tauruspay.base.mvp.view.BaseView;
import com.mchaw.tauruspay.bean.notice.NoticeBean;

/**
 * @author : Bruce Lee
 * @date : 2019/12/17 0017 07:55
 * @description :
 */
public interface NoticeConstract {
    interface View extends BaseView {
        void setNotice(NoticeBean noticeBean);
        void setNoticeFail();
    }
    interface Presenter extends BasePresenter<NoticeConstract.View> {
        void getNotice(String api_token,String noticeId);
    }
}
