package com.mchaw.tauruspay.ui.main.home.transferaccounts.presenter;

import com.mchaw.tauruspay.base.mvp.presenter.RxPresenter;
import com.mchaw.tauruspay.bean.home.UserBean;
import com.mchaw.tauruspay.bean.home.TransferAccountsBean;
import com.mchaw.tauruspay.http.ResultObserver;
import com.mchaw.tauruspay.ui.main.home.transferaccounts.constract.TransferAccountsConstract;
import com.mchaw.tauruspay.ui.repository.FundModel;
import com.mchaw.tauruspay.ui.repository.LoginModel;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * @author Bruce Lee
 * @date : 2019/11/6 16:59
 * @description:
 */
public class TransferAccountsPresenter extends RxPresenter<TransferAccountsConstract.View> implements TransferAccountsConstract.Presenter{

    @Inject
    FundModel fundModel;

    @Inject
    LoginModel loginModel;

    @Inject
    public TransferAccountsPresenter() {

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
    public void getTransferAccountsBean(String token, String code, String account, String payname, String amount) {
        Disposable disposable = fundModel.getTransferAccountsBean(token,code,account,payname,amount)
                .subscribeWith(new ResultObserver<TransferAccountsBean>() {
                    @Override
                    public void onSuccess(TransferAccountsBean transferAccountsBean) {
                        if (mView == null) {
                            return;
                        }
                        mView.setTransferAccountsBean(transferAccountsBean);
                    }

                    @Override
                    public void onFail(String msg) {
                        if (mView == null) {
                            return;
                        }
                        mView.setTransferAccountsFail();
                        mView.showError(msg);
                    }
                });
        addSubscribe(disposable);
    }
}
