package com.mchaw.tauruspay.ui.main.home.transferaccounts.constract;

import com.mchaw.tauruspay.base.mvp.presenter.BasePresenter;
import com.mchaw.tauruspay.base.mvp.view.BaseView;
import com.mchaw.tauruspay.bean.home.HomeDataBean;
import com.mchaw.tauruspay.bean.home.TransferAccountsBean;

/**
 * @author Bruce Lee
 * @date : 2019/11/6 16:43
 * @description:
 */
public interface TransferAccountsConstract {
    interface View extends BaseView {
        void setTransferAccountsBean(TransferAccountsBean transferAccountsBean);
        void setTransferAccountsFail();
        void setHomeDataBean(HomeDataBean homeDataBean);
    }
    interface Presenter extends BasePresenter<TransferAccountsConstract.View> {
        void getTransferAccountsBean(String token,String code,String account,String payname,String amount);
        void getHomeDataBean(String api_token);
    }
}
