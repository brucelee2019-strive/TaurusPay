package com.mchaw.tauruspay.ui.main.home.forsale.presenter;

import com.mchaw.tauruspay.base.mvp.presenter.RxPresenter;
import com.mchaw.tauruspay.bean.home.UserBean;
import com.mchaw.tauruspay.http.ResultObserver;
import com.mchaw.tauruspay.ui.main.home.forsale.constract.ForSaleConstract;
import com.mchaw.tauruspay.ui.repository.FundModel;
import com.mchaw.tauruspay.ui.repository.LoginModel;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * @author Bruce Lee
 * @date : 2019/11/7 10:32
 * @description:
 */
public class ForSalePresenter extends RxPresenter<ForSaleConstract.View> implements ForSaleConstract.Presenter{

    @Inject
    FundModel fundModel;

    @Inject
    LoginModel loginModel;

    @Inject
    public ForSalePresenter() {

    }

    Disposable homeBeanDisposable;
    @Override
    public void getHomeDataBean(String api_token) {
        removeSubscribe(homeBeanDisposable);
        homeBeanDisposable = loginModel.getHomeDataBean(api_token)
                .subscribeWith(new ResultObserver<UserBean>() {
                    @Override
                    public void onSuccess(UserBean userBean) {
                        if(mView == null){
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
