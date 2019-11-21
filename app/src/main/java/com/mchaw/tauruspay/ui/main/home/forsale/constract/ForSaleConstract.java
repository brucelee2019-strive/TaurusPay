package com.mchaw.tauruspay.ui.main.home.forsale.constract;

import com.mchaw.tauruspay.base.mvp.presenter.BasePresenter;
import com.mchaw.tauruspay.base.mvp.view.BaseView;
import com.mchaw.tauruspay.bean.home.StartOrOverSellBean;
import com.mchaw.tauruspay.bean.home.HomeDataBean;
import com.mchaw.tauruspay.bean.home.SelledOrderBean;
import com.mchaw.tauruspay.bean.home.SellingOrderBean;

import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2019/11/7 10:31
 * @description:
 */
public interface ForSaleConstract {
    interface View extends BaseView {
        void setHomeDataBean(HomeDataBean homeDataBean);
    }
    interface Presenter extends BasePresenter<View> {
        void getHomeDataBean(String api_token);
    }
}
