package com.mchaw.tauruspay.ui.main.recharge.record.LowerRechargeRecord;

import com.mchaw.tauruspay.base.mvp.presenter.RxPresenter;
import com.mchaw.tauruspay.bean.recharge.RechargeAuditBean;
import com.mchaw.tauruspay.http.ResultObserver;
import com.mchaw.tauruspay.ui.repository.FundModel;
import com.mchaw.tauruspay.ui.repository.LoginModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * @author : Bruce Lee
 * @date : 2020/1/12 0012 14:33
 * @description :
 */
public class LowerRecordPresenter extends RxPresenter<LowerRecordConstract.View> implements LowerRecordConstract.Presenter{
    @Inject
    FundModel fundModel;

    @Inject
    public LowerRecordPresenter() {

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
                        mView.setRechargeAuditListFail();
                        mView.showError(msg);
                    }
                });
        addSubscribe(disposable);
    }
}
