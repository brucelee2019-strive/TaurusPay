package com.mchaw.tauruspay;

import android.app.Application;

import com.mchaw.tauruspay.common.Constant;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
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

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        //现在需要创建AppComponent，因为这是全局的Component，
        // 自然是在Application中创建了
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiServiceModule(new ApiServiceModule())
                .build();
    }

    /**
     * 获取sessionKey
     *
     * @return
     */
    public String getSessionKey() {
        return PreferencesUtils.getString(this, Constant.PRE_SESSION_KEY);
    }
}
