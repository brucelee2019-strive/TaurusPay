package com.mchaw.tauruspay.ui.main.home.forsale.presenter;

import com.mchaw.tauruspay.base.mvp.presenter.RxPresenter;
import com.mchaw.tauruspay.bean.home.HomeDataBean;
import com.mchaw.tauruspay.bean.home.SellingOrderBean;
import com.mchaw.tauruspay.http.ResultObserver;
import com.mchaw.tauruspay.ui.main.home.forsale.constract.CollectionListConstract;
import com.mchaw.tauruspay.ui.repository.LoginModel;
import com.mchaw.tauruspay.ui.repository.SellModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * @author Bruce Lee
 * @date : 2019/11/21 9:56
 * @description:
 */
public class CollectionListPresenter extends RxPresenter<CollectionListConstract.View> implements CollectionListConstract.Presenter {

    @Inject
    SellModel sellModel;

    @Inject
    LoginModel loginModel;

    @Inject
    public CollectionListPresenter() {

    }

    Disposable TradingListDisposable;

    @Override
    public void getTradingList(String api_token) {
        removeSubscribe(TradingListDisposable);
        TradingListDisposable = sellModel.getTradingList(api_token)
                .subscribeWith(new ResultObserver<List<SellingOrderBean>>() {
                    @Override
                    public void onSuccess(List<SellingOrderBean> list) {
                        if (mView == null) {
                            return;
                        }
                            mView.setTradingList(list);
                    }

                    @Override
                    public void onFail(String msg) {
                        if (mView == null) {
                            return;
                        }
                        mView.setTradingListFail();
                        mView.showError(msg);
                    }
                });
        addSubscribe(TradingListDisposable);
    }

    @Override
    public void upLodingReceivables(String codeId, String api_token) {
        Disposable disposable = sellModel.upLodingReceivables(codeId, api_token)
                .subscribeWith(new ResultObserver<Integer>() {
                    @Override
                    public void onSuccess(Integer secceed) {

                    }

                    @Override
                    public void onFail(String msg) {
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
                .subscribeWith(new ResultObserver<HomeDataBean>() {
                    @Override
                    public void onSuccess(HomeDataBean homeDataBean) {
                        if (mView == null) {
                            return;
                        }
                        mView.setHomeDataBean(homeDataBean);
                    }

                    @Override
                    public void onFail(String msg) {
                        if (mView == null) {
                            return;
                        }
                        mView.showError(msg);
                    }
                });
        addSubscribe(homeBeanDisposable);
    }
}
