package com.mchaw.tauruspay;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.mchaw.tauruspay.base.activity.BaseActivity;
import com.mchaw.tauruspay.base.activity.BasePresenterActivity;
import com.mchaw.tauruspay.base.fragment.BasePresentFragment;
import com.mchaw.tauruspay.bean.eventbus.LoginoutEvent;
import com.mchaw.tauruspay.bean.home.SellingOrderBean;
import com.mchaw.tauruspay.common.util.NoNullUtils;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.ui.main.besure.BesureFragment;
import com.mchaw.tauruspay.ui.main.home.HomeFragment;
import com.mchaw.tauruspay.ui.main.home.forsale.constract.CollectionListConstract;
import com.mchaw.tauruspay.ui.main.home.forsale.presenter.CollectionListPresenter;
import com.mchaw.tauruspay.ui.main.mine.MineFragment;
import com.mchaw.tauruspay.ui.main.recharge.RechargeFragment;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BasePresenterActivity<CollectionListPresenter> implements CollectionListConstract.View,BottomNavigationView.OnNavigationItemSelectedListener {

    public static final int FRAGMENT_HOME = 0;
    public static final int FRAGMENT_RECHARGE = 1;
    public static final int FRAGMENT_BESURE = 2;
    public static final int FRAGMENT_MINE = 3;

    private HomeFragment homeFragment;
    private RechargeFragment rechargeFragment;
    private BesureFragment besureFragment;
    private MineFragment mineFragment;

    private static List<SellingOrderBean> sellingOrderBeanList;

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
        startPolling(10);
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

    @Override
    public void setTradingList(List<SellingOrderBean> list) {
        sellingOrderBeanList = list;
    }

    //以下是轮询
    private Disposable disposable;
    public void startPolling(int time) {
        disposable = Observable.interval(15, time, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.i("cici","轮询中...");
                        presenter.getTradingList(PreferencesUtils.getString(getApplicationContext(),"token"));
                    }
                });
    }

    public void stopPolling() {
        Log.i("cici","结束轮询");
        if(disposable!=null) {
            disposable.dispose();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopPolling();
    }

    public void  provideToNotice(int amout){
        for(SellingOrderBean sellingOrderBean : sellingOrderBeanList){
            if(amout == sellingOrderBean.getAmount()){
                presenter.upLodingReceivables(sellingOrderBean.getCodeid(),PreferencesUtils.getString(getApplicationContext(),"token"));
            }
        }
    }
}
