package com.mchaw.tauruspay.ui.main.mine.bill.presenter;

import com.mchaw.tauruspay.base.mvp.presenter.RxPresenter;
import com.mchaw.tauruspay.bean.bill.BillBean;
import com.mchaw.tauruspay.http.ResultObserver;
import com.mchaw.tauruspay.ui.main.mine.bill.constract.BillConstract;
import com.mchaw.tauruspay.ui.repository.LoginModel;
import com.mchaw.tauruspay.ui.repository.SellModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * @author Bruce Lee
 * @date : 2019/11/25 18:32
 * @description:
 */
public class BillPresenter extends RxPresenter<BillConstract.View> implements BillConstract.Presenter{

    @Inject
    SellModel sellModel;

    @Inject
    public BillPresenter() {

    }

    @Override
    public void getBillList(String api_token) {
        Disposable disposable = sellModel.getBillList(api_token)
                .subscribeWith(new ResultObserver<List<BillBean>>() {
                    @Override
                    public void onSuccess(List<BillBean> list) {
                        mView.setBillList(list);
                    }

                    @Override
                    public void onFail(String msg) {
                        mView.showError(msg);
                    }
                });
        addSubscribe(disposable);
    }
}
