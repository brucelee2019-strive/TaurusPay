package com.mchaw.tauruspay.ui.main.mine.notice.presenter;

import com.mchaw.tauruspay.base.mvp.presenter.RxPresenter;
import com.mchaw.tauruspay.bean.activate.ActivateCodeBean;
import com.mchaw.tauruspay.bean.notice.NoticeBean;
import com.mchaw.tauruspay.bean.notice.NoticeListBean;
import com.mchaw.tauruspay.http.ResultObserver;
import com.mchaw.tauruspay.ui.main.mine.notice.constract.NoticeConstract;
import com.mchaw.tauruspay.ui.repository.LoginModel;
import com.mchaw.tauruspay.ui.repository.SellModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

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

    @Override
    public void getNotice(String api_token,String noticeId) {
        Disposable disposable = loginModel.getNotice(api_token,noticeId)
                .subscribeWith(new ResultObserver<NoticeBean>() {
                    @Override
                    public void onSuccess(NoticeBean noticeBean) {
                        if (mView == null) {
                            return;
                        }
                        mView.setNotice(noticeBean);
                    }

                    @Override
                    public void onFail(String msg) {
                        if (mView == null) {
                            return;
                        }
                        mView.setNoticeFail();
                        mView.showError(msg);
                    }
                });
        addSubscribe(disposable);
    }
}
