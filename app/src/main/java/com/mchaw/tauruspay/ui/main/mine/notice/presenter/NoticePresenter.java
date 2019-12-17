package com.mchaw.tauruspay.ui.main.mine.notice.presenter;

import com.mchaw.tauruspay.base.mvp.presenter.RxPresenter;
import com.mchaw.tauruspay.ui.main.mine.notice.constract.NoticeConstract;
import com.mchaw.tauruspay.ui.repository.LoginModel;
import com.mchaw.tauruspay.ui.repository.SellModel;

import javax.inject.Inject;

/**
 * @author : Bruce Lee
 * @date : 2019/12/17 0017 07:56
 * @description :
 */
public class NoticePresenter extends RxPresenter<NoticeConstract.View> implements NoticeConstract.Presenter {
    @Inject
    LoginModel loginModel;

    @Inject
    public NoticePresenter() {

    }
}
