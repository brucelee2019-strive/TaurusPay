package com.mchaw.tauruspay.ui.login.presenter;

import com.mchaw.tauruspay.base.mvp.presenter.RxPresenter;
import com.mchaw.tauruspay.bean.login.LoginBean;
import com.mchaw.tauruspay.bean.login.LoginOutBean;
import com.mchaw.tauruspay.bean.updata.UpDataBean;
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
                        if (mView == null) {
                            return;
                        }
                        mView.setLoginBean(loginBean);
                    }

                    @Override
                    public void onFail(String msg) {
                        if (mView == null) {
                            return;
                        }
                        mView.setLoginFail();
                        mView.showError(msg);
                    }
                });
        addSubscribe(disposable);
    }

    @Override
    public void getLoginOutBean(String api_token) {
        Disposable disposable = loginModel.getLoginOutBean(api_token)
                .subscribeWith(new ResultObserver<LoginOutBean>() {
                    @Override
                    public void onSuccess(LoginOutBean loginOutBean) {
                        if (mView == null) {
                            return;
                        }
                        mView.setLoginOutBean(loginOutBean);
                    }

                    @Override
                    public void onFail(String msg) {
                        if (mView == null) {
                            return;
                        }
                        mView.setLoginFail();
                        mView.showError(msg);
                    }
                });
        addSubscribe(disposable);
    }

    @Override
    public void getVersion() {
        Disposable disposable = loginModel.getVersion()
                .subscribeWith(new ResultObserver<UpDataBean>() {
                    @Override
                    public void onSuccess(UpDataBean upDataBean) {
                        if(mView==null){
                            return;
                        }
                        mView.setVersion(upDataBean);
                    }

                    @Override
                    public void onFail(String msg) {
                        if(mView==null){
                            return;
                        }
                        mView.showError(msg);
                    }
                });
        addSubscribe(disposable);
    }
}
