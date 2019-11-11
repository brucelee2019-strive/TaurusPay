package com.mchaw.tauruspay.ui.login.password.constract;

import com.mchaw.tauruspay.base.mvp.presenter.BasePresenter;
import com.mchaw.tauruspay.base.mvp.view.BaseView;
import com.mchaw.tauruspay.bean.login.PasswordBean;
import com.mchaw.tauruspay.bean.login.RegisterBean;
import com.mchaw.tauruspay.ui.login.register.constract.RegisterConstract;

/**
 * @author Bruce Lee
 * @date : 2019/11/6 10:30
 * @description:
 */
public interface PasswordConstract {
    interface View extends BaseView {
        void setPasswordBean(PasswordBean passwordBean);
        void setPasswordFail();
    }
    interface Presenter extends BasePresenter<PasswordConstract.View> {
        void getPasswordBean(String token,String code,String passwd,String passwd_confirmation);
    }
}
