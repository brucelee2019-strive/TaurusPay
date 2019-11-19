package com.mchaw.tauruspay.ui;

import android.content.Intent;
import android.os.Handler;

import com.mchaw.tauruspay.MainActivity;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.activity.BaseActivity;
import com.mchaw.tauruspay.di.component.ActivityComponent;

/**
 * @author Bruce Lee
 * @date : 2019/11/19 16:38
 * @description:
 */
public class SplashActivity extends BaseActivity {
    @Override
    public int getContentViewId() {
        return R.layout.fragment_splash;
    }

    @Override
    public void initActivity() {
        super.initActivity();
        new Handler().postDelayed(new Runnable(){
            public void run(){
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        },1000);
    }

    @Override
    public void injectActivityComponent(ActivityComponent component) {
        super.injectActivityComponent(component);
    }
}
