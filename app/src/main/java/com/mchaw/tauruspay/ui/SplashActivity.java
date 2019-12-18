package com.mchaw.tauruspay.ui;

import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;

import com.mchaw.tauruspay.MyFrameApplication;
import com.mchaw.tauruspay.base.fragment.helper.FragmentStartHelper;
import com.mchaw.tauruspay.main.MainActivity;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.activity.BaseActivity;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.login.LoginFragment;
import com.mchaw.tauruspay.ui.login.LoginFragmentForFirst;

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
//        new Handler().postDelayed(new Runnable() {
//            public void run() {
//                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                startActivity(intent);
//                SplashActivity.this.finish();
//            }
//        }, 1000);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (TextUtils.isEmpty(MyFrameApplication.tokenStr)) {
                    FragmentStartHelper.startFragment(SplashActivity.this, new LoginFragmentForFirst());
                } else {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    SplashActivity.this.finish();
                }
            }
        }, 1000);
    }

    @Override
    public void injectActivityComponent(ActivityComponent component) {
        super.injectActivityComponent(component);
    }
}
