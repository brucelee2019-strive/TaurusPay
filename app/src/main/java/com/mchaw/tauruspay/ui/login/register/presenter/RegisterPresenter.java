package com.mchaw.tauruspay.ui.login.register.presenter;

import com.mchaw.tauruspay.base.mvp.presenter.RxPresenter;
import com.mchaw.tauruspay.bean.login.RegisterBean;
import com.mchaw.tauruspay.http.ResultObserver;
import com.mchaw.tauruspay.ui.login.register.constract.RegisterConstract;
import com.mchaw.tauruspay.ui.repository.LoginModel;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * @author Bruce Lee
 * @date : 2019/11/5 16:48
 * @description:
 */
public class RegisterPresenter extends RxPresenter<RegisterConstract.View> implements RegisterConstract.Presenter{
    @Inject
    LoginModel loginModel;

    @Inject
    public RegisterPresenter() {

    }

    @Override
    public void getRegisterBean(String account, String mobile, String code, String passwd, String passwd_confirmation, String payaccount, String activecode) {
        Disposable disposable = loginModel.getRegisterBean(account,mobile,code,passwd,passwd_confirmation,payaccount,activecode)
                .subscribeWith(new ResultObserver<RegisterBean>() {
                    @Override
                    public void onSuccess(RegisterBean registerBean) {
                        mView.setRegisterBean(registerBean);
                    }

                    @Override
                    public void onFail(String msg) {
                        mView.showError(msg);
                        mView.setRegisterFail();
                    }
                });
        addSubscribe(disposable);
    }
}
