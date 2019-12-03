package com.mchaw.tauruspay.ui.main.home.presenter;

import com.mchaw.tauruspay.base.mvp.presenter.RxPresenter;
import com.mchaw.tauruspay.bean.home.UserBean;
import com.mchaw.tauruspay.http.ResultObserver;
import com.mchaw.tauruspay.ui.main.home.constract.HomeConstract;
import com.mchaw.tauruspay.ui.repository.LoginModel;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * @author Bruce Lee
 * @date : 2019/11/12 16:40
 * @description:
 */
public class HomePresenter extends RxPresenter<HomeConstract.View> implements HomeConstract.Presenter{
    @Inject
    LoginModel loginModel;

    @Inject
    public HomePresenter() {

    }

    Disposable homeBeanDisposable;
    @Override
    public void getHomeDataBean(String api_token) {
        removeSubscribe(homeBeanDisposable);
        homeBeanDisposable = loginModel.getHomeDataBean(api_token)
                .subscribeWith(new ResultObserver<UserBean>() {
                    @Override
                    public void onSuccess(UserBean userBean) {
                        if(mView==null){
                           return;
                        }
                        mView.setHomeDataBean(userBean);
                    }

                    @Override
                    public void onFail(String msg) {
                        if(mView==null){
                            return;
                        }
                        mView.showError(msg);
                    }
                });
        addSubscribe(homeBeanDisposable);
    }
}
