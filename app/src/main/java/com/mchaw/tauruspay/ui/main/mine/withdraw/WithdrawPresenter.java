package com.mchaw.tauruspay.ui.main.mine.withdraw;

import com.mchaw.tauruspay.base.mvp.presenter.RxPresenter;
import com.mchaw.tauruspay.bean.home.UserBean;
import com.mchaw.tauruspay.bean.login.PasswordBean;
import com.mchaw.tauruspay.bean.withdraw.WithdrawBean;
import com.mchaw.tauruspay.http.ResultObserver;
import com.mchaw.tauruspay.ui.login.password.constract.PasswordConstract;
import com.mchaw.tauruspay.ui.repository.FundModel;
import com.mchaw.tauruspay.ui.repository.LoginModel;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * @author Bruce Lee
 * @date : 2020/2/5 20:35
 * @description:
 */
public class WithdrawPresenter extends RxPresenter<WithdrawConstract.View> implements WithdrawConstract.Presenter {
    @Inject
    FundModel fundModel;

    @Inject
    LoginModel loginModel;

    @Inject
    public WithdrawPresenter() {

    }

    @Override
    public void getCash(String api_token, String quota, String bank, String bankname, String bankname2, String account, String cardnumber, String phone, String password) {
        Disposable disposable = fundModel.getCash(api_token,quota,bank,bankname,bankname2,account,cardnumber,phone,password)
                .subscribeWith(new ResultObserver<WithdrawBean>() {
                    @Override
                    public void onSuccess(WithdrawBean withdrawBean) {
                        if (mView == null) {
                            return;
                        }
                        mView.setCashSucceed(withdrawBean);
                    }

                    @Override
                    public void onFail(String msg) {
                        if (mView == null) {
                            return;
                        }
                        mView.setCashFailed();
                        mView.showError(msg);
                    }
                });
        addSubscribe(disposable);
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
}
