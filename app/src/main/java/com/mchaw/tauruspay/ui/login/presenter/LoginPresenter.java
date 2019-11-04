package com.mchaw.tauruspay.ui.login.presenter;

import com.mchaw.tauruspay.base.mvp.presenter.RxPresenter;
import com.mchaw.tauruspay.bean.login.LoginBean;
import com.mchaw.tauruspay.http.ResultObserver;
import com.mchaw.tauruspay.ui.login.constract.LoginConstract;
import com.mchaw.tauruspay.ui.repository.LoginModel;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * @author : Bruce Lee
 * @date : 2019/11/4 0004 21:37
 * @description :
 */
public class LoginPresenter extends RxPresenter<LoginConstract.View> implements LoginConstract.Presenter{
    @Inject
    LoginModel loginModel;

    @Inject
    public LoginPresenter() {

    }

    @Override
    public void getLoginBean(String username, String code, String passwd) {
        Disposable disposable = loginModel.getLoginBean(username,code,passwd)
                .subscribeWith(new ResultObserver<LoginBean>() {
                    @Override
                    public void onSuccess(LoginBean loginBean) {
                        mView.setLoginBean(loginBean);
                    }

                    @Override
                    public void onFail(String msg) {
                        mView.showError(msg);
                    }
                });
        addSubscribe(disposable);
    }
}
