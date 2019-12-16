package com.mchaw.tauruspay.main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.azhon.appupdate.config.UpdateConfiguration;
import com.azhon.appupdate.listener.OnButtonClickListener;
import com.azhon.appupdate.listener.OnDownloadListener;
import com.azhon.appupdate.manager.DownloadManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.mchaw.tauruspay.MyFrameApplication;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.activity.BasePresenterActivity;
import com.mchaw.tauruspay.bean.MainPollingBean;
import com.mchaw.tauruspay.bean.eventbus.LoginSucceedEvent;
import com.mchaw.tauruspay.bean.eventbus.LoginoutEvent;
import com.mchaw.tauruspay.bean.eventbus.TradedBeanEvent;
import com.mchaw.tauruspay.bean.eventbus.TradingBeanEvent;
import com.mchaw.tauruspay.bean.eventbus.mainpolling.MainPollingGroupInfoEvent;
import com.mchaw.tauruspay.bean.eventbus.mainpolling.MainPollingReceivablesEvent;
import com.mchaw.tauruspay.bean.eventbus.mainpolling.MainPollingRechargeEvent;
import com.mchaw.tauruspay.bean.eventbus.mainpolling.MainPollingUserEvent;
import com.mchaw.tauruspay.bean.home.ReceivablesBean;
import com.mchaw.tauruspay.common.Constant;
import com.mchaw.tauruspay.common.util.NoNullUtils;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.common.util.WarningToneUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.main.constract.MainConstract;
import com.mchaw.tauruspay.main.presenter.MainPresenter;
import com.mchaw.tauruspay.ui.main.besure.BesureFragment;
import com.mchaw.tauruspay.ui.main.home.HomeFragment;
import com.mchaw.tauruspay.ui.main.mine.MineFragment;
import com.mchaw.tauruspay.ui.main.recharge.RechargeFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BasePresenterActivity<MainPresenter> implements MainConstract.View, BottomNavigationView.OnNavigationItemSelectedListener{

    public static final int FRAGMENT_HOME = 0;
    public static final int FRAGMENT_RECHARGE = 1;
    public static final int FRAGMENT_BESURE = 2;
    public static final int FRAGMENT_MINE = 3;

    private HomeFragment homeFragment;
    private RechargeFragment rechargeFragment;
    private BesureFragment besureFragment;
    private MineFragment mineFragment;

    private static List<ReceivablesBean> receivablesBeanList = new ArrayList<>();

    @BindView(R.id.bottom_view)
    BottomNavigationViewEx bottomView;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initActivity() {
        super.initActivity();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        bottomView.enableAnimation(true);
        bottomView.enableShiftingMode(false);
        bottomView.enableItemShiftingMode(false);
        bottomView.setItemIconTintList(null);
        bottomView.setOnNavigationItemSelectedListener(this);
        showFragment(FRAGMENT_HOME);
        //动态权限申请
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }
        startPolling(0, 5);
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

    @Subscribe
    public void loginSucceed(LoginSucceedEvent event) {
        //startPolling(5,5);
    }

    @Override
    public void setMainPollingBean(MainPollingBean bean) {
        //大轮询成功后
        if (bean == null) {
            return;
        }
        //个人信息
        if (bean.getUser() != null) {
            MainPollingUserEvent mainPollingUserEvent = new MainPollingUserEvent();
            mainPollingUserEvent.setKucun(bean.getUser().getDeposit());
            mainPollingUserEvent.setDangrikeshouedu(bean.getUser().getDayamount());
            mainPollingUserEvent.setDangrikeshoudanshu(bean.getUser().getDaycount());
            mainPollingUserEvent.setDangrishouyi(bean.getUser().getDaypoint());
            mainPollingUserEvent.setDangriyishouedu(bean.getUser().getDaydeposit());
            mainPollingUserEvent.setZaishouzhong(bean.getUser().getDayonsale());
            EventBus.getDefault().post(mainPollingUserEvent);
            //第一次默认MyFrameApplication.groupid为0,强退回来需要强行检验下强退前,二维码组状态。
            MyFrameApplication.groupid = bean.getUser().getGroupid();
        }
        //在售二维码组
        MainPollingGroupInfoEvent mainPollingGroupInfoEvent = new MainPollingGroupInfoEvent();
        mainPollingGroupInfoEvent.setGroupinfo(bean.getGroupinfo());
        EventBus.getDefault().post(mainPollingGroupInfoEvent);
        //充值
        if (bean.getRecharge() != null && bean.getRecharge().size() > 0) {
            MainPollingRechargeEvent mainPollingRechargeEvent = new MainPollingRechargeEvent();
            mainPollingRechargeEvent.setList(bean.getRecharge());
            EventBus.getDefault().post(mainPollingRechargeEvent);
        }
        //交易中订单
        MainPollingReceivablesEvent mainPollingReceivablesEvent = new MainPollingReceivablesEvent();
        mainPollingReceivablesEvent.setReceivables(bean.getReceivables());
        EventBus.getDefault().post(mainPollingReceivablesEvent);
        //专用作小红点与提示音
        redPointAndTone(bean);
    }

    private void redPointAndTone(MainPollingBean bean) {
        TradingBeanEvent tradingBeanEvent = new TradingBeanEvent();
        List<ReceivablesBean> list = bean.getReceivables();
        if (list != null && list.size() > 0) {
            if (receivablesBeanList != null && receivablesBeanList.size() > 0) {
                if (list.size() > receivablesBeanList.size()) {
                    waringTone();
                }
            } else {
                waringTone();
            }
            tradingBeanEvent.setRedPoint(list.size());
        } else {
            tradingBeanEvent.setRedPoint(0);
        }
        receivablesBeanList = list;
        EventBus.getDefault().post(tradingBeanEvent);
    }

    @Override
    public void setUpLodingReceivables() {
        //更新收款列表
        EventBus.getDefault().post(new TradedBeanEvent());
    }

    //以下是轮询
    private Disposable disposable;

    public void startPolling(int start, int time) {
        Log.i("cici", "总程序交易中订单列表，开始轮询...");
        disposable = Observable.interval(start, time, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.i("cici", "总程序交易中订单列表，轮询中...");
                        if (!TextUtils.isEmpty(MyFrameApplication.getInstance().tokenStr)) {
                            //presenter.getTradingList(PreferencesUtils.getString(getApplicationContext(), "token"));
                            //presenter.getHomeDataBean(PreferencesUtils.getString(getApplicationContext(), "token"));
                            presenter.getMainPollingBean(MyFrameApplication.getInstance().tokenStr, MyFrameApplication.groupid);
                        }
                    }
                });
    }

    public void stopPolling() {
        Log.i("cici", "总程序交易中订单列表，结束轮询");
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //startPolling(0, 5);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //stopPolling();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopPolling();
    }

    private void waringTone() {
        if (PreferencesUtils.getBoolean(MainActivity.this, Constant.WARNING_TONE, true)) {
            WarningToneUtils.getInstance().playSound();
        }
    }

    public void provideToNotice(int amout) {
        for (ReceivablesBean receivablesBean : receivablesBeanList) {
            if (amout == receivablesBean.getAmount()) {
                presenter.upLodingReceivables(String.valueOf(receivablesBean.getId()), PreferencesUtils.getString(getApplicationContext(), "token"));
            }
        }
    }
}
