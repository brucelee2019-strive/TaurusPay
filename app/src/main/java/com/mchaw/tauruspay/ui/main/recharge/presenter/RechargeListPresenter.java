package com.mchaw.tauruspay.ui.main.recharge.presenter;

import com.mchaw.tauruspay.base.mvp.presenter.RxPresenter;
import com.mchaw.tauruspay.bean.home.HomeDataBean;
import com.mchaw.tauruspay.bean.recharge.RechargeBean;
import com.mchaw.tauruspay.bean.recharge.RechargeNextBean;
import com.mchaw.tauruspay.http.ResultObserver;
import com.mchaw.tauruspay.ui.main.recharge.constract.RechargeListConstract;
import com.mchaw.tauruspay.ui.repository.FundModel;
import com.mchaw.tauruspay.ui.repository.LoginModel;

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
    LoginModel loginModel;

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
                        mView.setRecaargeListFail();
                        mView.showError(msg);
                    }
                });
        addSubscribe(reChargeListDisposable);
    }

    Disposable homeBeanDisposable;
    @Override
    public void getHomeDataBean(String api_token) {
        removeSubscribe(homeBeanDisposable);
        homeBeanDisposable = loginModel.getHomeDataBean(api_token)
                .subscribeWith(new ResultObserver<HomeDataBean>() {
                    @Override
                    public void onSuccess(HomeDataBean homeDataBean) {
                        if(mView==null){
                            return;
                        }
                        mView.setHomeDataBean(homeDataBean);
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

    Disposable reChargeUpdateListDisposable;
    @Override
    public void getRechargeUpdateList(String api_token) {
        removeSubscribe(reChargeUpdateListDisposable);
        reChargeUpdateListDisposable = fundModel.getRechargeUpdateList(api_token)
                .subscribeWith(new ResultObserver<List<RechargeBean>>() {
                    @Override
                    public void onSuccess(List<RechargeBean> list) {
                        mView.setRechargeUpdateList(list);
                    }

                    @Override
                    public void onFail(String msg) {
                        mView.showError(msg);
                    }
                });
        addSubscribe(reChargeUpdateListDisposable);
    }
}
