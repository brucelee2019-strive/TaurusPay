package com.mchaw.tauruspay.ui.login.register.constract;

import com.mchaw.tauruspay.base.mvp.presenter.BasePresenter;
import com.mchaw.tauruspay.base.mvp.view.BaseView;
import com.mchaw.tauruspay.bean.login.RegisterBean;

/**
 * @author Bruce Lee
 * @date : 2019/11/5 16:22
 * @description:
 */
public interface RegisterConstract {
    interface View extends BaseView {
        void setRegisterBean(RegisterBean registerBean);
    }
    interface Presenter extends BasePresenter<RegisterConstract.View> {
        void getRegisterBean(String account,String mobile,String code,String passwd,String passwd_confirmation,String payaccount,String activecode);
    }
}
