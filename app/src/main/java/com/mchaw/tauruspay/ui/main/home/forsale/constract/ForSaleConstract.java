package com.mchaw.tauruspay.ui.main.home.forsale.constract;

import com.mchaw.tauruspay.base.mvp.presenter.BasePresenter;
import com.mchaw.tauruspay.base.mvp.view.BaseView;
import com.mchaw.tauruspay.bean.home.UserBean;

/**
 * @author Bruce Lee
 * @date : 2019/11/7 10:31
 * @description:
 */
public interface ForSaleConstract {
    interface View extends BaseView {
        void setHomeDataBean(UserBean userBean);
    }
    interface Presenter extends BasePresenter<View> {
        void getHomeDataBean(String api_token);
    }
}
