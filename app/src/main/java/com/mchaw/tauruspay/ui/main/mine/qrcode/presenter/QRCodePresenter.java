package com.mchaw.tauruspay.ui.main.mine.qrcode.presenter;

import com.mchaw.tauruspay.base.mvp.presenter.RxPresenter;
import com.mchaw.tauruspay.bean.entry.MultipleItem;
import com.mchaw.tauruspay.bean.qrcode.DeleteQRCodeGroupBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeGroupCreateBean;
import com.mchaw.tauruspay.bean.qrcode.GroupinfoBean;
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
public class QRCodePresenter extends RxPresenter<QRCodeConstract.View> implements QRCodeConstract.Presenter {

    @Inject
    QRCodeModel qrCodeModel;

    @Inject
    public QRCodePresenter() {

    }

    @Override
    public void getQRCodeGroupList(String token) {
        Disposable disposable = qrCodeModel.getQRCodeGroupList(token)
                .subscribeWith(new ResultObserver<List<GroupinfoBean>>() {
                    @Override
                    public void onSuccess(List<GroupinfoBean> list) {
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

    @Override
    public void getQRCodeGroupBean(String token, String account, String nick, String paytype) {
        Disposable disposable = qrCodeModel.getQRCodeGroupBean(token, account, nick, paytype)
                .subscribeWith(new ResultObserver<QRCodeGroupCreateBean>() {
                    @Override
                    public void onSuccess(QRCodeGroupCreateBean qrCodeGroupCreateBean) {
                        if (mView == null) {
                            return;
                        }
                        mView.setQRCodeGroupBean(qrCodeGroupCreateBean);
                    }

                    @Override
                    public void onFail(String msg) {
                        if (mView == null) {
                            return;
                        }
                        mView.setQRCodeGroupBeanFail();
                        mView.showError(msg);
                    }
                });
        addSubscribe(disposable);
    }

    @Override
    public void getUpLoadingQRCodeUrlBean(String token, String codeid, String url) {
        Disposable disposable = qrCodeModel.getUpLoadingQRCodeUrlBean(token, codeid, url)
                .subscribeWith(new ResultObserver<QRCodeUrlBean>() {
                    @Override
                    public void onSuccess(QRCodeUrlBean bean) {
                        if (mView == null) {
                            return;
                        }
                        mView.setUpLoadingQRCodeUrlBean(bean);
                    }

                    @Override
                    public void onFail(String msg) {
                        if (mView == null) {
                            return;
                        }
                        mView.setUpLoadingQRCodeUrlBeanFail();
                        mView.showError(msg);
                    }
                });
        addSubscribe(disposable);
    }

    Disposable qrCodeStallsDisposable;

    @Override
    public void getQRCodeStalls(String groupid, String api_token) {
        removeSubscribe(qrCodeStallsDisposable);
        qrCodeStallsDisposable = qrCodeModel.getQRCodeStalls(groupid, api_token)
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
    public void deleteQRCodeGroup(String groupid, String api_token) {
        Disposable disposable = qrCodeModel.deleteQRCodeGroup(groupid, api_token)
                .subscribeWith(new ResultObserver<DeleteQRCodeGroupBean>() {
                    @Override
                    public void onSuccess(DeleteQRCodeGroupBean bean) {
                        if (mView == null) {
                            return;
                        }
                        mView.setDeleteQRCodeGroup(bean);
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
