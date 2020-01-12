package com.mchaw.tauruspay.ui.main.recharge.presenter;

import com.mchaw.tauruspay.base.mvp.presenter.RxPresenter;
import com.mchaw.tauruspay.bean.home.UserBean;
import com.mchaw.tauruspay.bean.recharge.AuditBean;
import com.mchaw.tauruspay.bean.recharge.RechargeAuditBean;
import com.mchaw.tauruspay.bean.recharge.RechargeBean;
import com.mchaw.tauruspay.http.ResultObserver;
import com.mchaw.tauruspay.ui.main.recharge.constract.RechargeAuditConstract;
import com.mchaw.tauruspay.ui.main.recharge.constract.RechargeListConstract;
import com.mchaw.tauruspay.ui.repository.FundModel;
import com.mchaw.tauruspay.ui.repository.LoginModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * @author Bruce Lee
 * @date : 2020/1/10 11:11
 * @description:
 */
public class RechargeAuditPresenter extends RxPresenter<RechargeAuditConstract.View> implements RechargeAuditConstract.Presenter {
    @Inject
    FundModel fundModel;

    @Inject
    LoginModel loginModel;

    @Inject
    public RechargeAuditPresenter() {

    }

    Disposable homeBeanDisposable;
    @Override
    public void getHomeDataBean(String api_token) {
        removeSubscribe(homeBeanDisposable);
        homeBeanDisposable = loginModel.getHomeDataBean(api_token)
                .subscribeWith(new ResultObserver<UserBean>() {
                    @Override
                    public void onSuccess(UserBean userBean) {
                        if(mView==null){
                            return;
                        }
                        mView.setHomeDataBean(userBean);
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

    @Override
    public void getRechargeAudit(String api_token,String order,int status) {
        Disposable disposable = fundModel.getRechargeAudit(api_token,order,status)
                .subscribeWith(new ResultObserver<AuditBean>() {
                    @Override
                    public void onSuccess(AuditBean auditBean) {
                        if (mView == null) {
                            return;
                        }
                        mView.setRechargeAudit(auditBean);
                    }

                    @Override
                    public void onFail(String msg) {
                        if (mView == null) {
                            return;
                        }
                        mView.setRechargeAuditFail();
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
                        mView.setRechargeAuditListFail();
                        mView.showError(msg);
                    }
                });
        addSubscribe(disposable);
    }
}
