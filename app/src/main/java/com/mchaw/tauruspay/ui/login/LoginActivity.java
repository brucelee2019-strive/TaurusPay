package com.mchaw.tauruspay.ui.login;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.mchaw.tauruspay.MyFrameApplication;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.activity.BaseActivity;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.SplashFragment;
import com.mchaw.tauruspay.ui.main.home.HomeFragment;

/**
 * @author Bruce Lee
 * @date : 2019/11/5 10:18
 * @description:
 */
public class LoginActivity extends BaseActivity {
    public static final int FRAGMENT_LOGIN = 0;
    public static final int FRAGMENT_SPLASH = 1;
    private LoginFragment loginFragment;
    private SplashFragment splashFragment;

    @Override
    public int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    public void initActivity() {
        super.initActivity();
        showFragment(TextUtils.isEmpty(MyFrameApplication.getInstance().tokenStr)?FRAGMENT_LOGIN:FRAGMENT_SPLASH);
    }

    @Override
    public void injectActivityComponent(ActivityComponent component) {
        super.injectActivityComponent(component);
    }

    private void showFragment(int index){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        switch (index) {
            case FRAGMENT_LOGIN:
                if (loginFragment == null) {
                    loginFragment = new LoginFragment();
                    ft.add(R.id.fragment_something_location, loginFragment, LoginFragment.class.getName());
                } else {
                    ft.show(loginFragment);
                }
                break;
            case FRAGMENT_SPLASH:
                if (splashFragment == null) {
                    splashFragment = new SplashFragment();
                    ft.add(R.id.fragment_something_location, splashFragment, SplashFragment.class.getName());
                } else {
                    ft.show(splashFragment);
                }
                break;
            default:
                break;
        }
        ft.commitAllowingStateLoss();
    }

    /**
     * 隐藏fragment
     *
     * @param ft
     */
    private void hideFragment(FragmentTransaction ft) {
        if (loginFragment != null) {
            ft.hide(loginFragment);
        }
        if (splashFragment != null) {
            ft.hide(splashFragment);
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment != loginFragment) {
                ft.remove(fragment);
            }
            if (fragment != splashFragment) {
                ft.remove(fragment);
            }
        }
        ft.commitAllowingStateLoss();
    }
}
