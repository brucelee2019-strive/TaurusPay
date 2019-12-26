package com.mchaw.tauruspay.ui.main.recharge.presenter;

import com.mchaw.tauruspay.base.mvp.presenter.RxPresenter;
import com.mchaw.tauruspay.bean.recharge.RechargeNextBean;
import com.mchaw.tauruspay.bean.recharge.RechargeSureBean;
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
    public void getRechargeNextBean(String paymentNum,String token) {
        Disposable disposable = fundModel.getRechargeNextBean(paymentNum,token)
                .subscribeWith(new ResultObserver<RechargeNextBean>() {
                    @Override
                    public void onSuccess(RechargeNextBean rechargeNextBean) {
                        if (mView == null) {
                            return;
                        }
                        mView.setRechargeNextBean(rechargeNextBean);
                    }

                    @Override
                    public void onFail(String msg) {
                        if (mView == null) {
                            return;
                        }
                        mView.setRechargeNextFail();
                        mView.showError(msg);
                    }
                });
        addSubscribe(disposable);
    }

    @Override
    public void getRechargeSureBean(String orderId,String token) {
        Disposable disposable = fundModel.getRechargeSureBean(orderId,token)
                .subscribeWith(new ResultObserver<RechargeSureBean>() {
                    @Override
                    public void onSuccess(RechargeSureBean rechargeSureBean) {
                        if (mView == null) {
                            return;
                        }
                        mView.setRechargeSureBean(rechargeSureBean);
                    }

                    @Override
                    public void onFail(String msg) {
                        if (mView == null) {
                            return;
                        }
                        mView.setRechargeNextFail();
                        mView.showError(msg);
                    }
                });
        addSubscribe(disposable);
    }
}
