package com.mchaw.tauruspay.ui.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.activity.BaseActivity;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.home.HomeFragment;

/**
 * @author Bruce Lee
 * @date : 2019/11/5 10:18
 * @description:
 */
public class LoginActivity extends BaseActivity {
    private LoginFragment loginFragment;

    @Override
    public int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    public void initActivity() {
        super.initActivity();
        showFragment();
    }

    @Override
    public void injectActivityComponent(ActivityComponent component) {
        super.injectActivityComponent(component);
    }

    private void showFragment(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (loginFragment == null) {
            loginFragment = new LoginFragment();
            ft.add(R.id.fragment_something_location, loginFragment, LoginFragment.class.getName());
        } else {
            ft.show(loginFragment);
        }
        ft.commitAllowingStateLoss();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment != loginFragment) {
                ft.remove(fragment);
            }
        }
        ft.commitAllowingStateLoss();
    }
}
