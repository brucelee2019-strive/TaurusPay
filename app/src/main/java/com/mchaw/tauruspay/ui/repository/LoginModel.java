package com.mchaw.tauruspay.ui.repository;

import com.mchaw.tauruspay.bean.ScoreAllStateBean;
import com.mchaw.tauruspay.bean.login.LoginBean;
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
}
