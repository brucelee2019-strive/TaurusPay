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

    public static int groupid = 0;
    //开始待售组独自拥有
    public static int startingPosition = -1;
    //公网Ip
    public static String pIp = "";
}
