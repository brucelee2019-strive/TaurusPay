package com.mchaw.tauruspay;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.mchaw.tauruspay.base.activity.BaseActivity;
import com.mchaw.tauruspay.bean.eventbus.LoginoutEvent;
import com.mchaw.tauruspay.common.util.NoNullUtils;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.besure.BesureFragment;
import com.mchaw.tauruspay.ui.main.home.HomeFragment;
import com.mchaw.tauruspay.ui.main.mine.MineFragment;
import com.mchaw.tauruspay.ui.main.recharge.RechargeFragment;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static final int FRAGMENT_HOME = 0;
    public static final int FRAGMENT_RECHARGE = 1;
    public static final int FRAGMENT_BESURE = 2;
    public static final int FRAGMENT_MINE = 3;

    private HomeFragment homeFragment;
    private RechargeFragment rechargeFragment;
    private BesureFragment besureFragment;
    private MineFragment mineFragment;

    @BindView(R.id.bottom_view)
    BottomNavigationViewEx bottomView;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initActivity() {
        super.initActivity();
        bottomView.enableAnimation(true);
        bottomView.enableShiftingMode(false);
        bottomView.enableItemShiftingMode(false);
        bottomView.setItemIconTintList(null);
        bottomView.setOnNavigationItemSelectedListener(this);
        showFragment(FRAGMENT_HOME);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment != homeFragment
                    && fragment != rechargeFragment
                    && fragment != besureFragment
                    && fragment != mineFragment) {
                ft.remove(fragment);
            }
        }
        ft.commitAllowingStateLoss();
    }

    @Override
    public void injectActivityComponent(ActivityComponent component) {
        component.inject(this);
    }

    private void showFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        switch (index) {
            case FRAGMENT_HOME:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.fragment_content, homeFragment, HomeFragment.class.getName());
                } else {
                    ft.show(homeFragment);
                }
                break;
            case FRAGMENT_RECHARGE:
                if (rechargeFragment == null) {
                    rechargeFragment = new RechargeFragment();
                    ft.add(R.id.fragment_content, rechargeFragment, RechargeFragment.class.getName());
                } else {
                    ft.show(rechargeFragment);
                }
                break;
            case FRAGMENT_BESURE:
                if (besureFragment == null) {
                    besureFragment = new BesureFragment();
                    ft.add(R.id.fragment_content, besureFragment, BesureFragment.class.getName());
                } else {
                    ft.show(besureFragment);
                }
                break;
            case FRAGMENT_MINE:
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    ft.add(R.id.fragment_content, mineFragment, MineFragment.class.getName());
                } else {
                    ft.show(mineFragment);
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
        if (homeFragment != null) {
            ft.hide(homeFragment);
        }

        if (rechargeFragment != null) {
            ft.hide(rechargeFragment);
        }

        if (besureFragment != null) {
            ft.hide(besureFragment);
        }

        if (mineFragment != null) {
            ft.hide(mineFragment);
        }
        if (mineFragment != null) {
            ft.hide(mineFragment);
        }
    }

    /**
     * 当前tab选中
     */
    private MenuItem currentItem;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        //去重
        if (NoNullUtils.checkNoNull(currentItem) && currentItem.getItemId() == menuItem.getItemId()) {
            return true;
        }
        currentItem = menuItem;
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                showFragment(FRAGMENT_HOME);
                break;
            case R.id.navigation_score:
                showFragment(FRAGMENT_RECHARGE);
                break;
            case R.id.navigation_discover:
                showFragment(FRAGMENT_BESURE);
                break;
            case R.id.navigation_mine:
                showFragment(FRAGMENT_MINE);
                break;
            default:
                break;
        }
        return true;
    }

    @Subscribe
    public void loginouted(LoginoutEvent event) {
        if (event == null) {
            return;
        }
        bottomView.setCurrentItem(0);
    }
}
