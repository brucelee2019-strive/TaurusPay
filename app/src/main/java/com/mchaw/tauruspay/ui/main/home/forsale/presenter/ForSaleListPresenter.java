package com.mchaw.tauruspay.ui.main.home.forsale.presenter;

import com.mchaw.tauruspay.base.mvp.presenter.RxPresenter;
import com.mchaw.tauruspay.bean.qrcode.QRCodeGroupBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeStallBean;
import com.mchaw.tauruspay.http.ResultObserver;
import com.mchaw.tauruspay.ui.main.home.forsale.constract.ForSaleListConstract;
import com.mchaw.tauruspay.ui.repository.QRCodeModel;

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
    public ForSaleListPresenter() {

    }
    @Override
    public void getQRCodeGroupList(String token) {
        Disposable disposable = qrCodeModel.getQRCodeGroupList(token)
                .subscribeWith(new ResultObserver<List<QRCodeGroupBean>>() {
                    @Override
                    public void onSuccess(List<QRCodeGroupBean> list) {
                        mView.setQRCodeGroupList(list);
                    }

                    @Override
                    public void onFail(String msg) {
                        mView.showError(msg);
                    }
                });
        addSubscribe(disposable);
    }

    @Override
    public void getQRCodeStalls(String groupid, String api_token) {
        Disposable disposable = qrCodeModel.getQRCodeStalls(groupid,api_token)
                .subscribeWith(new ResultObserver<QRCodeStallBean>() {
                    @Override
                    public void onSuccess(QRCodeStallBean bean) {
                        mView.setQRCodeStalls(bean);
                    }

                    @Override
                    public void onFail(String msg) {
                        mView.showError(msg);
                    }
                });
        addSubscribe(disposable);
    }
}
