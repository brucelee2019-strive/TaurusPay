package com.mchaw.tauruspay.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.content.IntentFilter;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.mchaw.tauruspay.MyFrameApplication;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.activity.BasePresenterActivity;
import com.mchaw.tauruspay.bean.MainPollingBean;
import com.mchaw.tauruspay.bean.eventbus.ForbiddenEvent;
import com.mchaw.tauruspay.bean.eventbus.LoginSucceedEvent;
import com.mchaw.tauruspay.bean.eventbus.LoginoutEvent;
import com.mchaw.tauruspay.bean.eventbus.NoticeEvent;
import com.mchaw.tauruspay.bean.eventbus.NoticeSureEvent;
import com.mchaw.tauruspay.bean.eventbus.TradedBeanEvent;
import com.mchaw.tauruspay.bean.eventbus.TradingBeanEvent;
import com.mchaw.tauruspay.bean.eventbus.mainpolling.MainPollingGroupInfoEvent;
import com.mchaw.tauruspay.bean.eventbus.mainpolling.MainPollingReceivablesEvent;
import com.mchaw.tauruspay.bean.eventbus.mainpolling.MainPollingRechargeEvent;
import com.mchaw.tauruspay.bean.eventbus.mainpolling.MainPollingUserEvent;
import com.mchaw.tauruspay.bean.home.ReceivablesBean;
import com.mchaw.tauruspay.bean.notice.NoticeBean;
import com.mchaw.tauruspay.common.Constant;
import com.mchaw.tauruspay.common.util.NoNullUtils;
import com.mchaw.tauruspay.common.util.PreferencesUtils;
import com.mchaw.tauruspay.common.util.WarningToneUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.main.constract.MainConstract;
import com.mchaw.tauruspay.main.presenter.MainPresenter;
import com.mchaw.tauruspay.ui.login.LoginActivity;
import com.mchaw.tauruspay.ui.main.besure.BesureFragment;
import com.mchaw.tauruspay.ui.main.home.HomeFragment;
import com.mchaw.tauruspay.ui.main.mine.MineFragment;
import com.mchaw.tauruspay.service.PayNotifiService;
//import com.mchaw.tauruspay.ui.SplashActivity;
import android.content.BroadcastReceiver;

import com.mchaw.tauruspay.ui.main.recharge.RechargeFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends BasePresenterActivity<MainPresenter> implements MainConstract.View, BottomNavigationView.OnNavigationItemSelectedListener {

    public static final int FRAGMENT_HOME = 0;
    public static final int FRAGMENT_RECHARGE = 1;
    public static final int FRAGMENT_BESURE = 2;
    public static final int FRAGMENT_MINE = 3;

    private MyReceiver receiver = null;
    private HomeFragment homeFragment;
    private RechargeFragment rechargeFragment;
    private BesureFragment besureFragment;
    private MineFragment mineFragment;

    private static List<ReceivablesBean> receivablesBeanList = new ArrayList<>();

    @BindView(R.id.bottom_view)
    BottomNavigationViewEx bottomView;

    private QBadgeView qBadgeView;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }


    @Override
    public void initActivity() {
        super.initActivity();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        bottomView.enableAnimation(true);
        bottomView.enableShiftingMode(false);
        bottomView.enableItemShiftingMode(false);
        bottomView.setItemIconTintList(null);
        bottomView.setOnNavigationItemSelectedListener(this);
        //启动服务
        runPayNptifyService(this);
        //注册广播接收器
        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.mchaw.tauruspay.service.PayNotifyService");
        MainActivity.this.registerReceiver(receiver, filter);

        showFragment(FRAGMENT_HOME);
        //动态权限申请
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }
        //大轮询与通知轮询
        startPolling(1, 5);
        noticeStartPolling(10, 120);
        //我的上通知小红点
        qBadgeView = new QBadgeView(this);
        qBadgeView.setBadgeNumber(0)
                .setGravityOffset(12, 2, true)
                .bindTarget(bottomView.getBottomNavigationItemView(3));
        acquireWakeLock();
    }

    private void runPayNptifyService(Context context) {
        ComponentName collectorComponent = new ComponentName(context, PayNotifiService.class);
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        boolean isRunning = false;
        List<ActivityManager.RunningServiceInfo> runningServices = null;
        if (manager != null) runningServices = manager.getRunningServices(Integer.MAX_VALUE);
        if (runningServices == null) return;
        for (ActivityManager.RunningServiceInfo service : runningServices) {
            if (service.service.equals(collectorComponent)) {
                if (service.pid == android.os.Process.myPid()) {
                    isRunning = true;
                }
            }
        }
        if (!isRunning) toggleNotificationListenerService();
    }

    private void toggleNotificationListenerService() {
        //Intent intent = new Intent(this, PayNotifiService.class);//启动服务
        //startService(intent);//启动服务
        PackageManager pm = getPackageManager();
        pm.setComponentEnabledSetting(new ComponentName(this, PayNotifiService.class),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        pm.setComponentEnabledSetting(new ComponentName(this, PayNotifiService.class),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
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


    /**
     * 退出登录
     * @param event
     */
    @Subscribe
    public void loginouted(LoginoutEvent event) {
        if (event == null) {
            return;
        }
        bottomView.setCurrentItem(0);
        stopPolling();
        noticeStopPolling();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * 登录成功
     * @param event
     */
    @Subscribe
    public void loginSucceed(LoginSucceedEvent event) {
        startPolling(1, 5);
        noticeStartPolling(10, 120);
    }

    /**
     * 大轮询成功
     * @param bean
     */
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
            mainPollingUserEvent.setDongjiejine(bean.getUser().getFrozen());
            EventBus.getDefault().post(mainPollingUserEvent);
            //第一次默认MyFrameApplication.groupid为0,强退回来需要强行检验下强退前,二维码组状态。
            MyFrameApplication.groupid = bean.getUser().getGroupid();
            MyFrameApplication.alipayContextA = bean.getUser().getAlipay1();
            MyFrameApplication.alipayContextB = bean.getUser().getAlipay2();
            MyFrameApplication.wechatContextA = bean.getUser().getWechat1();
            MyFrameApplication.wechatContextB = bean.getUser().getWechat2();
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

    /**
     * 红点声音
     * @param bean
     */
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

    @Override
    public void setNotice(NoticeBean noticeBean) {
        if (noticeBean == null) {
            return;
        }
        qBadgeView.setBadgeNumber(noticeBean.getNotice());
        NoticeEvent noticeEvent = new NoticeEvent();
        noticeEvent.setNoticeNum(noticeBean.getNotice());
        EventBus.getDefault().post(noticeEvent);
    }

    //以下是大轮询
    private Disposable disposable;

    public void startPolling(int start, int time) {
        //runPayNptifyService(this);
        Log.i("cici", "总程序交易中订单列表，开始轮询...");
        disposable = Observable.interval(start, time, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.i("cici", "总程序交易中订单列表，轮询中...");
                        presenter.getMainPollingBean(MyFrameApplication.getInstance().tokenStr, MyFrameApplication.groupid);
                    }
                });
    }

    public void stopPolling() {
        Log.i("cici", "总程序交易中订单列表，结束轮询");
        if (disposable != null) {
            disposable.dispose();
        }
    }

    //以下是消息通知轮询
    private Disposable noticeDisposable;

    public void noticeStartPolling(int start, int time) {
        toggleNotificationListenerService();
        Log.i("cici", "消息通知，开始轮询...");
        noticeDisposable = Observable.interval(start, time, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.i("cici", "消息通知，轮询中...");
                        if (!TextUtils.isEmpty(MyFrameApplication.getInstance().tokenStr)) {
                            if (!TextUtils.isEmpty(MyFrameApplication.getInstance().tokenStr)) {
                                presenter.getNotice(MyFrameApplication.getInstance().tokenStr, "0");
                            }
                        }
                    }
                });
    }

    public void noticeStopPolling() {
        Log.i("cici", "消息通知，结束轮询");
        if (noticeDisposable != null) {
            noticeDisposable.dispose();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopPolling();
        noticeStopPolling();
        releaseWakeLock();
    }

    private void waringTone() {
        if (PreferencesUtils.getBoolean(MainActivity.this, Constant.WARNING_TONE, true)) {
            WarningToneUtils.getInstance().playSound();
        }
    }

    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            int amout = bundle.getInt("amout");
            for (ReceivablesBean receivablesBean : receivablesBeanList) {
                if (amout == receivablesBean.getAmount()) {
                    presenter.upLodingReceivables(String.valueOf(receivablesBean.getId()), PreferencesUtils.getString(getApplicationContext(), "token"));
                }
            }
        }
    }

    /**
     * 账号禁用
     * @param event
     */
    @Subscribe
    public void forbidden(ForbiddenEvent event) {
        stopPolling();
        noticeStopPolling();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Subscribe
    public void setNoticeRedPoint(NoticeSureEvent noticeSureEvent) {
        if (noticeSureEvent == null) {
            return;
        }
        qBadgeView.setBadgeNumber(noticeSureEvent.getNoticeNum());
    }

    /**
     * 系统通知比对
     * @param amout
     */
    public void provideToNotice(int amout) {
        for (ReceivablesBean receivablesBean : receivablesBeanList) {
            if (amout == receivablesBean.getAmount()) {
                presenter.upLodingReceivables(String.valueOf(receivablesBean.getId()), PreferencesUtils.getString(getApplicationContext(), "token"));
            }
        }
    }

    PowerManager.WakeLock wakeLock = null;

    //获取电源锁，保持该服务在屏幕熄灭时仍然获取CPU时，保持运行
    @SuppressLint("InvalidWakeLockTag")
    private void acquireWakeLock() {
        if (null == wakeLock) {
            PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, "PostLocationService");
            if (null != wakeLock) {
                wakeLock.acquire();
            }
        }
    }

    //释放设备电源锁
    private void releaseWakeLock() {
        if (null != wakeLock) {
            wakeLock.release();
            wakeLock = null;
        }
    }
}
