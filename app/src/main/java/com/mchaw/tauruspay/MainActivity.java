package com.mchaw.tauruspay;

import androidx.fragment.app.FragmentTransaction;

import com.mchaw.tauruspay.base.activity.BaseActivity;
import com.mchaw.tauruspay.ui.fragment.MainFragment;
import com.mchaw.tauruspay.di.component.ActivityComponent;

public class MainActivity extends BaseActivity {

    private MainFragment homeFragment;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
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
        homeFragment = new MainFragment();
        ft.add(R.id.fragment_content, homeFragment, MainFragment.class.getName());
        ft.commitAllowingStateLoss();
    }

}
