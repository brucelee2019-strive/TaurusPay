package com.mchaw.tauruspay.base.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.mchaw.tauruspay.MyFrameApplication;
import com.mchaw.tauruspay.R;
import com.mchaw.tauruspay.base.fragment.helper.FragmentOnBackStackListener;
import com.mchaw.tauruspay.bean.eventbus.SessionInvalidEvent;
import com.mchaw.tauruspay.common.util.ToastUtils;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.di.component.DaggerActivityComponent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.mchaw.tauruspay.base.fragment.helper.FragmentStartHelper.INTENT_FRAGMENT;

/**
 * @author : Bruce Lee
 * @description :
 * @date : 2019/10/27 0027 21:43
 */
public class BaseActivity extends AppCompatActivity {
    private Unbinder unbinder;
    private Fragment resultFragment;
    private FragmentOnBackStackListener fragmentOnBackStackListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getContentViewId() != -1) {
            setContentView(getContentViewId());
        } else {
            setContentView(R.layout.activity_base);
        }

        unbinder = ButterKnife.bind(this);
        //DebouncingOnClickUtils.bind(unbinder);
        EventBus.getDefault().register(this);
        //通常情况下每个Activity会对应一个Component
        ActivityComponent component = DaggerActivityComponent.builder()
                .appComponent(MyFrameApplication.getInstance().getAppComponent())
                .build();
        injectActivityComponent(component);
        initActivity();
        addFragment();
    }

    public void initActivity() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultFragment != null){
            resultFragment.onActivityResult(requestCode,resultCode,data);
        }
    }

    /**
     * @param resultFragment
     */
    public void setResultFragment(Fragment resultFragment) {
        this.resultFragment = resultFragment;
    }

    @Override
    public void onBackPressed() {
        if (fragmentOnBackStackListener != null && !fragmentOnBackStackListener.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    public void setFragmentOnBackStackListener(FragmentOnBackStackListener fragmentOnBackStackListener) {
        if (this.fragmentOnBackStackListener != null) {
            return; //由第一个fragment进行事件分发
        }
        this.fragmentOnBackStackListener = fragmentOnBackStackListener;
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onSaveInstanceState(Bundle outState) {
    }

    /**
     * 获取布局文件ID
     *
     * @return
     */
    public int getContentViewId() {
        return -1;
    }

    /**
     * 简单容器填充fragment
     */
    private void addFragment() {
        if (getContentViewId() != -1){
            return;
        }
        try {
            Fragment baseFragment = (Fragment) Class.forName(getIntent().getStringExtra(INTENT_FRAGMENT)).newInstance();
            if (baseFragment == null) {
                return;
            }
            if (getIntent() != null) {
                baseFragment.setArguments(getIntent().getExtras());
            }
            resultFragment = baseFragment;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.base_activity_fl_root,baseFragment,baseFragment.getClass().getSimpleName());
            ft.commitNowAllowingStateLoss();
            baseFragment.setUserVisibleHint(true);
        } catch (Exception e) {
            ToastUtils.showShortToast(MyFrameApplication.getInstance().getApplicationContext(), e.getMessage());
            finish();
        }
    }

    /**
     * 注入网络请求
     *
     * @param component
     */
    public void injectActivityComponent(ActivityComponent component) {
        component.inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void nullEvent(SessionInvalidEvent event) {

    }
}
