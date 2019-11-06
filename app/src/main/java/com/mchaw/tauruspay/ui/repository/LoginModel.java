package com.mchaw.tauruspay.ui.repository;

import com.mchaw.tauruspay.bean.ScoreAllStateBean;
import com.mchaw.tauruspay.bean.login.LoginBean;
import com.mchaw.tauruspay.bean.login.PasswordBean;
import com.mchaw.tauruspay.bean.login.RegisterBean;
import com.mchaw.tauruspay.http.ResultDisposable;
import com.mchaw.tauruspay.http.ScheduleTranformer;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author : Bruce Lee
 * @date : 2019/11/4 0004 21:48
 * @description :
 */
public class LoginModel extends BaseModel{
    @Inject
    public LoginModel() {

    }

    public Observable<LoginBean> getLoginBean(String username, String code, String passwd) {
        return apiService.getLoginBean(username,code,passwd)
                .compose(new ResultDisposable<LoginBean>())
                .compose(new ScheduleTranformer<LoginBean>());
    }

    public Observable<RegisterBean> getRegisterBean(String account, String mobile, String code, String passwd, String passwd_confirmation, String payaccount, String activecode) {
        return apiService.getRegisterBean(account,mobile,code,passwd,passwd_confirmation,payaccount,activecode)
                .compose(new ResultDisposable<RegisterBean>())
                .compose(new ScheduleTranformer<RegisterBean>());
    }

    public Observable<PasswordBean> getPasswordBean(String token, String code, String passwd, String passwd_confirmation) {
        return apiService.getRegisterBean(token,code,passwd,passwd_confirmation)
                .compose(new ResultDisposable<PasswordBean>())
                .compose(new ScheduleTranformer<PasswordBean>());
    }
}
