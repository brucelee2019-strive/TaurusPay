package com.mchaw.tauruspay.ui.main.home.forsale.presenter;

import com.mchaw.tauruspay.base.mvp.presenter.RxPresenter;
import com.mchaw.tauruspay.ui.main.home.forsale.constract.ForSaleConstract;
import com.mchaw.tauruspay.ui.main.home.transferaccounts.constract.TransferAccountsConstract;
import com.mchaw.tauruspay.ui.repository.FundModel;

import javax.inject.Inject;

/**
 * @author Bruce Lee
 * @date : 2019/11/7 10:32
 * @description:
 */
public class ForSalePresenter extends RxPresenter<ForSaleConstract.View> implements ForSaleConstract.Presenter{

    @Inject
    FundModel fundModel;

    @Inject
    public ForSalePresenter() {

    }

    @Override
    public void getForSaleBean() {

    }

    @Override
    public void getForSaleList() {

    }

    @Override
    public void getCollectionList() {

    }
}
