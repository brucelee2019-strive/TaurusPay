package com.mchaw.tauruspay.ui.main.mine.activate.presenter;

import com.mchaw.tauruspay.base.mvp.presenter.RxPresenter;
import com.mchaw.tauruspay.bean.activate.ActivateCodeBean;
import com.mchaw.tauruspay.bean.login.LoginBean;
import com.mchaw.tauruspay.http.ResultObserver;
import com.mchaw.tauruspay.ui.main.mine.activate.constract.ActivateConstract;
import com.mchaw.tauruspay.ui.repository.LoginModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * @author Bruce Lee
 * @date : 2019/11/25 17:47
 * @description:
 */
public class ActivatePresenter extends RxPresenter<ActivateConstract.View> implements ActivateConstract.Presenter{
    @Inject
    LoginModel loginModel;

    @Inject
    public ActivatePresenter() {

    }

    @Override
    public void getActiveCodeList(String api_token) {
        Disposable disposable = loginModel.getActiveCodeList(api_token)
                .subscribeWith(new ResultObserver<List<ActivateCodeBean>>() {
                    @Override
                    public void onSuccess(List<ActivateCodeBean> list) {
                        mView.setActiveCodeList(list);
                    }

                    @Override
                    public void onFail(String msg) {
                        mView.setActiveCodeListFail();
                        mView.showError(msg);
                    }
                });
        addSubscribe(disposable);
    }
}
