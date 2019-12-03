package com.mchaw.tauruspay.ui.main.home.forsale.presenter;

import com.mchaw.tauruspay.base.mvp.presenter.RxPresenter;
import com.mchaw.tauruspay.bean.home.StartOrOverSellBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeGroupBean;
import com.mchaw.tauruspay.bean.qrcode.GroupinfoBean;
import com.mchaw.tauruspay.http.ResultObserver;
import com.mchaw.tauruspay.ui.main.home.forsale.constract.ForSaleListConstract;
import com.mchaw.tauruspay.ui.repository.QRCodeModel;
import com.mchaw.tauruspay.ui.repository.SellModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * @author Bruce Lee
 * @date : 2019/11/20 15:40
 * @description:
 */
public class ForSaleListPresenter extends RxPresenter<ForSaleListConstract.View> implements ForSaleListConstract.Presenter{
    @Inject
    QRCodeModel qrCodeModel;

    @Inject
    SellModel sellModel;

    @Inject
    public ForSaleListPresenter() {

    }

    @Override
    public void getQRCodeGroupList(String token) {
        Disposable disposable = qrCodeModel.getQRCodeGroupList(token)
                .subscribeWith(new ResultObserver<List<QRCodeGroupBean>>() {
                    @Override
                    public void onSuccess(List<QRCodeGroupBean> list) {
                        if (mView == null) {
                            return;
                        }
                        mView.setQRCodeGroupList(list);
                    }

                    @Override
                    public void onFail(String msg) {
                        if (mView == null) {
                            return;
                        }
                        mView.setQRCodeGroupListFail();
                        mView.showError(msg);
                    }
                });
        addSubscribe(disposable);
    }

    Disposable qrCodeStallsDisposable;
    @Override
    public void getQRCodeStalls(String groupid, String api_token) {
        removeSubscribe(qrCodeStallsDisposable);
        qrCodeStallsDisposable = qrCodeModel.getQRCodeStalls(groupid,api_token)
                .subscribeWith(new ResultObserver<GroupinfoBean>() {
                    @Override
                    public void onSuccess(GroupinfoBean bean) {
                        if (mView == null) {
                            return;
                        }
                        mView.setQRCodeStalls(bean);
                    }

                    @Override
                    public void onFail(String msg) {
                        if (mView == null) {
                            return;
                        }
                        mView.showError(msg);
                    }
                });
        addSubscribe(qrCodeStallsDisposable);
    }

    @Override
    public void startingOrOverSell(String groupid, int state, String token) {
        Disposable disposable = sellModel.startingOrOverSell(groupid,state,token)
                .subscribeWith(new ResultObserver<StartOrOverSellBean>() {
                    @Override
                    public void onSuccess(StartOrOverSellBean bean) {
                        if (mView == null) {
                            return;
                        }
                        mView.setStartingOrOverSell(bean);
                    }

                    @Override
                    public void onFail(String msg) {
                        if (mView == null) {
                            return;
                        }
                        mView.showError(msg);
                    }
                });
        addSubscribe(disposable);
    }
}
