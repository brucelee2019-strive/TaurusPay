package com.mchaw.tauruspay.ui.main.mine.agency.presenter;

import com.mchaw.tauruspay.base.mvp.presenter.RxPresenter;
import com.mchaw.tauruspay.bean.agency.AgencyBean;
import com.mchaw.tauruspay.bean.agency.LowerRateBean;
import com.mchaw.tauruspay.http.ResultObserver;
import com.mchaw.tauruspay.ui.main.mine.agency.constract.AgencyListConstract;
import com.mchaw.tauruspay.ui.repository.LoginModel;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * @author Bruce Lee
 * @date : 2020/1/11 14:05
 * @description:
 */
public class AgencyListPresenter extends RxPresenter<AgencyListConstract.View> implements AgencyListConstract.Presenter{
    @Inject
    LoginModel loginModel;

    @Inject
    public AgencyListPresenter() {

    }

    @Override
    public void getAgent(String api_token) {
        Disposable disposable = loginModel.getAgent(api_token)
                .subscribeWith(new ResultObserver<AgencyBean>() {
                    @Override
                    public void onSuccess(AgencyBean agencyBean) {
                        if (mView == null) {
                            return;
                        }
                        mView.setAgent(agencyBean);
                    }

                    @Override
                    public void onFail(String msg) {
                        if (mView == null) {
                            return;
                        }
                        mView.setAgentFail();
                        mView.showError(msg);
                    }
                });
        addSubscribe(disposable);
    }

    @Override
    public void changeLowerRate(String api_token,String cashierid,String rate) {
        Disposable disposable = loginModel.changeLowerRate(api_token,cashierid,rate)
                .subscribeWith(new ResultObserver<LowerRateBean>() {
                    @Override
                    public void onSuccess(LowerRateBean lowerRateBean) {
                        if (mView == null) {
                            return;
                        }
                        mView.setChangeLowerRate(lowerRateBean);
                    }

                    @Override
                    public void onFail(String msg) {
                        if (mView == null) {
                            return;
                        }
                        mView.setChangeLowerRateFail();
                        mView.showError(msg);
                    }
                });
        addSubscribe(disposable);
    }
}
