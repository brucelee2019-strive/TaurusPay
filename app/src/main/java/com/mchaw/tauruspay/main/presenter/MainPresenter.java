package com.mchaw.tauruspay.main.presenter;

import com.mchaw.tauruspay.base.mvp.presenter.RxPresenter;
import com.mchaw.tauruspay.bean.MainPollingBean;
import com.mchaw.tauruspay.bean.bill.TradingBean;
import com.mchaw.tauruspay.bean.notice.NoticeBean;
import com.mchaw.tauruspay.bean.recharge.RechargeAuditBean;
import com.mchaw.tauruspay.http.ResultObserver;
import com.mchaw.tauruspay.main.constract.MainConstract;
import com.mchaw.tauruspay.ui.repository.FundModel;
import com.mchaw.tauruspay.ui.repository.LoginModel;
import com.mchaw.tauruspay.ui.repository.SellModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * @author Bruce Lee
 * @date : 2019/12/3 15:25
 * @description:
 */
public class MainPresenter extends RxPresenter<MainConstract.View> implements MainConstract.Presenter{
    @Inject
    FundModel fundModel;

    @Inject
    SellModel sellModel;

    @Inject
    LoginModel loginModel;

    @Inject
    public MainPresenter() {

    }

    @Override
    public void getMainPollingBean(String api_token, int groupid) {
        Disposable disposable = loginModel.getMainPollingBean(api_token,groupid)
                .subscribeWith(new ResultObserver<MainPollingBean>() {
                    @Override
                    public void onSuccess(MainPollingBean mainPollingBean) {
                        if (mView == null) {
                            return;
                        }
                        mView.setMainPollingBean(mainPollingBean);
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

    @Override
    public void upLodingReceivables(String codeId, String api_token) {
        Disposable disposable = sellModel.upLodingReceivables(codeId, api_token)
                .subscribeWith(new ResultObserver<TradingBean>() {
                    @Override
                    public void onSuccess(TradingBean secceed) {
                        if (mView == null) {
                            return;
                        }
                        mView.setUpLodingReceivables();
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

    @Override
    public void getNotice(String api_token,String noticeId) {
        Disposable disposable = loginModel.getNotice(api_token,noticeId)
                .subscribeWith(new ResultObserver<NoticeBean>() {
                    @Override
                    public void onSuccess(NoticeBean noticeBean) {
                        if (mView == null) {
                            return;
                        }
                        mView.setNotice(noticeBean);
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

    @Override
    public void getRechargeAuditList(String api_token, int type,int page) {
        Disposable disposable = fundModel.getRechargeAuditList(api_token,type,page)
                .subscribeWith(new ResultObserver<List<RechargeAuditBean>>() {
                    @Override
                    public void onSuccess(List<RechargeAuditBean> list) {
                        if (mView == null) {
                            return;
                        }
                        mView.setRechargeAuditList(list);
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
