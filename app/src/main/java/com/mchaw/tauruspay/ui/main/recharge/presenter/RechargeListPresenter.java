package com.mchaw.tauruspay.ui.main.recharge.presenter;

import com.mchaw.tauruspay.base.mvp.presenter.RxPresenter;
import com.mchaw.tauruspay.bean.recharge.RechargeBean;
import com.mchaw.tauruspay.bean.recharge.RechargeNextBean;
import com.mchaw.tauruspay.http.ResultObserver;
import com.mchaw.tauruspay.ui.main.recharge.constract.RechargeListConstract;
import com.mchaw.tauruspay.ui.repository.FundModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * @author Bruce Lee
 * @date : 2019/11/12 12:29
 * @description:
 */
public class RechargeListPresenter extends RxPresenter<RechargeListConstract.View> implements RechargeListConstract.Presenter{

    @Inject
    FundModel fundModel;

    @Inject
    public RechargeListPresenter() {

    }

    Disposable reChargeListDisposable;
    @Override
    public void getRechargeList(String token) {
        removeSubscribe(reChargeListDisposable);
        reChargeListDisposable = fundModel.getRechargeList(token)
                .subscribeWith(new ResultObserver<List<RechargeBean>>() {
                    @Override
                    public void onSuccess(List<RechargeBean> list) {
                        mView.setRechargeList(list);
                    }

                    @Override
                    public void onFail(String msg) {
                        mView.showError(msg);
                    }
                });
        addSubscribe(reChargeListDisposable);
    }
}
