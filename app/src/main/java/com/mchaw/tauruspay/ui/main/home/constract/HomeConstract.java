package com.mchaw.tauruspay.ui.main.home.constract;

import com.mchaw.tauruspay.base.mvp.presenter.BasePresenter;
import com.mchaw.tauruspay.base.mvp.view.BaseView;
import com.mchaw.tauruspay.bean.home.UserBean;
import com.mchaw.tauruspay.bean.updata.UpDataBean;

/**
 * @author Bruce Lee
 * @date : 2019/11/12 16:40
 * @description:
 */
public interface HomeConstract {
    interface View extends BaseView {
        void setHomeDataBean(UserBean userBean);
        void setVersion(UpDataBean upDataBean);
    }
    interface Presenter extends BasePresenter<HomeConstract.View> {
        void getHomeDataBean(String api_token);
        void getVersion();
    }
}
