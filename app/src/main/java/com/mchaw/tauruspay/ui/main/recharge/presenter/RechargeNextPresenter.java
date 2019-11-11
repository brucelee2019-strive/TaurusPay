package com.mchaw.tauruspay.ui.main.recharge.presenter;

import com.mchaw.tauruspay.base.mvp.presenter.RxPresenter;
import com.mchaw.tauruspay.bean.login.LoginBean;
import com.mchaw.tauruspay.bean.recharge.RechargeNextBean;
import com.mchaw.tauruspay.http.ResultObserver;
import com.mchaw.tauruspay.ui.main.recharge.constract.RechargeNextConstract;
import com.mchaw.tauruspay.ui.repository.FundModel;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * @author Bruce Lee
 * @date : 2019/11/11 17:04
 * @description:
 */
public class RechargeNextPresenter extends RxPresenter<RechargeNextConstract.View> implements RechargeNextConstract.Presenter{
    @Inject
    FundModel fundModel;

    @Inject
    public RechargeNextPresenter() {

    }


    @Override
    public void getRechargeNextBean(String token) {
        Disposable disposable = fundModel.getRechargeNextBean(token)
                .subscribeWith(new ResultObserver<RechargeNextBean>() {
                    @Override
                    public void onSuccess(RechargeNextBean loginBean) {
                        mView.setRechargeNextBean(loginBean);
                    }

                    @Override
                    public void onFail(String msg) {
                        mView.setRechargeNextFail();
                        mView.showError(msg);
                    }
                });
        addSubscribe(disposable);
    }
}
