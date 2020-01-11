package com.mchaw.tauruspay;

import android.app.Application;

import com.mchaw.tauruspay.common.Constant;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.common.util.WarningToneUtils;
import com.mchaw.tauruspay.di.component.AppComponent;
import com.mchaw.tauruspay.di.component.DaggerAppComponent;
import com.mchaw.tauruspay.di.module.ApiServiceModule;
import com.mchaw.tauruspay.di.module.AppModule;

public class MyFrameApplication extends Application {

    private static MyFrameApplication mInstance;

    public static MyFrameApplication getInstance(){
        return mInstance;
    }

    private AppComponent appComponent;

    public AppComponent getAppComponent(){
        return appComponent;
    }

    public static String tokenStr = "";

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        WarningToneUtils.getInstance().init();
        //现在需要创建AppComponent，因为这是全局的Component，
        // 自然是在Application中创建了
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiServiceModule(new ApiServiceModule())
                .build();
        tokenStr = PreferencesUtils.getString(MyFrameApplication.this,"token");
    }

    /**
     * 获取sessionKey
     *
     * @return
     */
    public String getSessionKey() {
        return PreferencesUtils.getString(this, Constant.PRE_SESSION_KEY);
    }

    public  static String alipayContextA = "成功收款";
    public  static String alipayContextB = "元。享免费提现等更多专属服务，点击查看";
    public  static String wechatContextA = "微信支付收款";
    public  static String wechatContextB = "朋友到店";

    //代售分组id
    public static int groupid = 0;
    //开始待售组独自拥有 开始代售分组的位置需本地保存
    public static int startingPosition = -1;
    //公网Ip
    public static String pIp = "";
    //用户类型 0:普通用户 1:一级代理 2:二级代理 3:三级代理
    public static int userType = 0;
    //如果是一级,二级代理会有 userInviteCode 代理推广码
    public static String userInviteCode = "";
    //如果是一级，二级代理会有 userRate 代理返点率
    public static int userRate = 0;
}
