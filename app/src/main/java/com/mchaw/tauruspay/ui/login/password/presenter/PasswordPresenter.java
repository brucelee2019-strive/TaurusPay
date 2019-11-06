package com.mchaw.tauruspay.ui.login.password.presenter;

import com.mchaw.tauruspay.base.mvp.presenter.RxPresenter;
import com.mchaw.tauruspay.bean.login.PasswordBean;
import com.mchaw.tauruspay.http.ResultObserver;
import com.mchaw.tauruspay.ui.login.password.constract.PasswordConstract;
import com.mchaw.tauruspay.ui.repository.LoginModel;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * @author Bruce Lee
 * @date : 2019/11/6 10:34
 * @description:
 */
public class PasswordPresenter extends RxPresenter<PasswordConstract.View> implements PasswordConstract.Presenter {
    @Inject
    LoginModel loginModel;

    @Inject
    public PasswordPresenter() {

    }

    @Override
    public void getPasswordBean(String token, String code, String passwd, String passwd_confirmation) {
        Disposable disposable = loginModel.getPasswordBean(token,code,passwd,passwd_confirmation)
                .subscribeWith(new ResultObserver<PasswordBean>() {
                    @Override
                    public void onSuccess(PasswordBean passwordBean) {
                        mView.setPasswordBean(passwordBean);
                    }

                    @Override
                    public void onFail(String msg) {
                        mView.showError(msg);
                    }
                });
        addSubscribe(disposable);
    }
}
