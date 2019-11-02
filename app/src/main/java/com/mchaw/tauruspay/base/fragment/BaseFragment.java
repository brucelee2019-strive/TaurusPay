package com.mchaw.tauruspay.base.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mchaw.tauruspay.MyFrameApplication;
import com.mchaw.tauruspay.base.activity.BaseActivity;
import com.mchaw.tauruspay.base.fragment.helper.FragmentOnBackStackListener;
import com.mchaw.tauruspay.base.fragment.helper.FragmentStartHelper;
import com.mchaw.tauruspay.base.mvp.view.ui_view.FragmentStartHelperView;
import com.mchaw.tauruspay.bean.eventbus.EmptyEvent;
import com.mchaw.tauruspay.di.component.ActivityComponent;
import com.mchaw.tauruspay.di.component.DaggerActivityComponent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author : Bruce Lee
 * @description :
 * @date : 2019/10/27 0027 22:04
 */
public abstract class BaseFragment extends Fragment implements FragmentStartHelperView, FragmentOnBackStackListener {

    /**
     * Fragment的View加载完毕的标记
     **/
    private boolean isViewCreated;

    /**
     * Fragment对用户可见的标记
     **/
    private boolean isUIVisible;

    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getContentViewId(), null);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).setFragmentOnBackStackListener(this);
        }
        try {
            EventBus.getDefault().register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        isViewCreated = true;
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }

    /**
     * 懒加载操作
     */
    private void lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isUIVisible) {
            ActivityComponent component = DaggerActivityComponent.builder()
                    .appComponent(MyFrameApplication.getInstance().getAppComponent())
                    .build();
            injectFragmentComponent(component);
            initFragment();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUIVisible = false;
        }
    }

    /**
     * 获取布局LayoutId
     *
     * @return id
     */
    protected abstract int getContentViewId();

    /**
     * Fragment操作
     */
    protected abstract void initFragment();


    protected void getIntentData() {}

    /**
     * 注入网络请求
     *
     * @param component
     */
    public void injectFragmentComponent(ActivityComponent component) {

    }

    /**
     * false:fragment内部处理 true:activity处理
     *
     * @return
     */
    @Override
    public boolean onBackPressed() {
        return true;
    }

    @Override
    public void startFragment(BaseFragment baseFragment) {
        startFragmentForResult(baseFragment,-1);
    }

    @Override
    public void startFragmentForResult(BaseFragment baseFragment, int requestCode) {
        startFragmentForResult(baseFragment,BaseActivity.class,null,requestCode);
    }

    @Override
    public void startActivity(Class<?> cls, Bundle bundle) {
        startActivityForResult(cls,bundle,-1);
    }

    @Override
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        startFragmentForResult(null,cls,bundle,requestCode);
    }

    @Override
    public void startFragmentForResult(BaseFragment baseFragment, Class<?> cls, Bundle bundle, int requestCode) {
        if (requestCode != -1 && getActivity() instanceof BaseActivity){
            ((BaseActivity)getActivity()).setResultFragment(this);
        }
        FragmentStartHelper.startFragmentForResult(getActivity(),baseFragment,cls,bundle,requestCode);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        try {
            EventBus.getDefault().unregister(this);
        } catch (Exception e) {
            if (!TextUtils.isEmpty(e.getMessage())) {
                Log.e("EventBus error", e.getMessage());
            }
        }
    }

    @Subscribe
    public void onEmpty(EmptyEvent event) {
    }
}
