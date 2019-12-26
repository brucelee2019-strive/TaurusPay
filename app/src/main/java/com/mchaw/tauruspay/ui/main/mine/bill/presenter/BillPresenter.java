package com.mchaw.tauruspay.ui.main.mine.bill.presenter;

import com.mchaw.tauruspay.base.mvp.presenter.RxPresenter;
import com.mchaw.tauruspay.bean.bill.BillTotalBean;
import com.mchaw.tauruspay.http.ResultObserver;
import com.mchaw.tauruspay.ui.main.mine.bill.constract.BillConstract;
import com.mchaw.tauruspay.ui.repository.SellModel;

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
    public void getBillList(String api_token,int status,int page) {
        Disposable disposable = sellModel.getBillList(api_token,status,page)
                .subscribeWith(new ResultObserver<BillTotalBean>() {
                    @Override
                    public void onSuccess(BillTotalBean billTotalBean) {
                        if (mView == null) {
                            return;
                        }
                        mView.setBillList(billTotalBean);
                    }

                    @Override
                    public void onFail(String msg) {
                        if (mView == null) {
                            return;
                        }
                        mView.setBillListFail();
                        mView.showError(msg);
                    }
                });
        addSubscribe(disposable);
    }
}
