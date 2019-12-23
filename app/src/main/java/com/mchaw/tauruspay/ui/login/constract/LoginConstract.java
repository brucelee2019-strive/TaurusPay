package com.mchaw.tauruspay.ui.login.constract;

import com.mchaw.tauruspay.base.mvp.presenter.BasePresenter;
import com.mchaw.tauruspay.base.mvp.view.BaseView;
import com.mchaw.tauruspay.bean.login.LoginBean;
import com.mchaw.tauruspay.bean.login.LoginOutBean;
import com.mchaw.tauruspay.bean.updata.UpDataBean;

/**
 * @author : Bruce Lee
 * @date : 2019/11/4 0004 21:36
 * @description :
 */
public interface LoginConstract {
    interface View extends BaseView {
        void setLoginBean(LoginBean loginBean);
        void setLoginOutBean(LoginOutBean loginOutBean);
        void setLoginFail();
        void setVersion(UpDataBean upDataBean);
    }
    interface Presenter extends BasePresenter<LoginConstract.View> {
        void getLoginBean(String username,String code,String passwd,String ip);
        void getLoginOutBean(String api_token);
        void getVersion();
    }
}
