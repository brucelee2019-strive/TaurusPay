package com.mchaw.tauruspay.ui.repository;

import com.mchaw.tauruspay.bean.MainPollingBean;
import com.mchaw.tauruspay.bean.activate.ActivateCodeBean;
import com.mchaw.tauruspay.bean.home.UserBean;
import com.mchaw.tauruspay.bean.login.LoginBean;
import com.mchaw.tauruspay.bean.login.PasswordBean;
import com.mchaw.tauruspay.bean.login.RegisterBean;
import com.mchaw.tauruspay.http.ResultDisposable;
import com.mchaw.tauruspay.http.ScheduleTranformer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author : Bruce Lee
 * @date : 2019/11/4 0004 21:48
 * @description : 登录注册及个人信息订阅名目
 */
public class LoginModel extends BaseModel{
    @Inject
    public LoginModel() {

    }
    //登录
    public Observable<LoginBean> getLoginBean(String username, String code, String passwd) {
        return apiService.getLoginBean(username,code,passwd)
                .compose(new ResultDisposable<LoginBean>())
                .compose(new ScheduleTranformer<LoginBean>());
    }
    //注册
    public Observable<RegisterBean> getRegisterBean(String account, String mobile, String code, String passwd, String passwd_confirmation, String payaccount, String activecode) {
        return apiService.getRegisterBean(account,mobile,code,passwd,passwd_confirmation,payaccount,activecode)
                .compose(new ResultDisposable<RegisterBean>())
                .compose(new ScheduleTranformer<RegisterBean>());
    }
    //修改密码
    public Observable<PasswordBean> getPasswordBean(String token, String code, String passwd, String passwd_confirmation) {
        return apiService.getRegisterBean(token,code,passwd,passwd_confirmation)
                .compose(new ResultDisposable<PasswordBean>())
                .compose(new ScheduleTranformer<PasswordBean>());
    }
    //获取个人信息
    public Observable<UserBean> getHomeDataBean(String token) {
        return apiService.getHomeDataBean(token)
                .compose(new ResultDisposable<UserBean>())
                .compose(new ScheduleTranformer<UserBean>());
    }

    //获取个人邀请码
    public Observable<List<ActivateCodeBean>> getActiveCodeList(String api_token) {
        return apiService.getActiveCodeList(api_token)
                .compose(new ResultDisposable<List<ActivateCodeBean>>())
                .compose(new ScheduleTranformer<List<ActivateCodeBean>>());
    }

    //大轮询
    public Observable<MainPollingBean> getMainPollingBean(String api_token, String groupid) {
        return apiService.getMainPollingBean(api_token,groupid)
                .compose(new ResultDisposable<MainPollingBean>())
                .compose(new ScheduleTranformer<MainPollingBean>());
    }
}
