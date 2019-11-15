package com.mchaw.tauruspay.ui.main.mine.qrcode.presenter;

import com.mchaw.tauruspay.base.mvp.presenter.RxPresenter;
import com.mchaw.tauruspay.bean.ALiYunCodeBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeGroupBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeGroupCreateBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeStallBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeUrlBean;
import com.mchaw.tauruspay.http.ResultObserver;
import com.mchaw.tauruspay.ui.main.mine.qrcode.constract.QRCodeConstract;
import com.mchaw.tauruspay.ui.repository.QRCodeModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * @author Bruce Lee
 * @date : 2019/11/13 11:20
 * @description:
 */
public class QRCodePresenter extends RxPresenter<QRCodeConstract.View> implements QRCodeConstract.Presenter{

    @Inject
    QRCodeModel qrCodeModel;

    @Inject
    public QRCodePresenter() {

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
    public void getQRCodeGroupBean(String token, String account, String nick, String paytype) {
        Disposable disposable = qrCodeModel.getQRCodeGroupBean(token,account,nick,paytype)
                .subscribeWith(new ResultObserver<QRCodeGroupCreateBean>() {
                    @Override
                    public void onSuccess(QRCodeGroupCreateBean qrCodeGroupCreateBean) {
                        mView.setQRCodeGroupBean(qrCodeGroupCreateBean);
                    }

                    @Override
                    public void onFail(String msg) {
                        mView.showError(msg);
                    }
                });
        addSubscribe(disposable);
    }

    @Override
    public void getUpLoadingQRCodeUrlBean(String token,String codeid,String url) {
        Disposable disposable = qrCodeModel.getUpLoadingQRCodeUrlBean(token,codeid,url)
                .subscribeWith(new ResultObserver<QRCodeUrlBean>() {
                    @Override
                    public void onSuccess(QRCodeUrlBean bean) {
                       mView.setUpLoadingQRCodeUrlBean(bean);
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
