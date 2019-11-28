package com.mchaw.tauruspay.base.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;

import com.mchaw.tauruspay.base.mvp.presenter.BasePresenter;
import com.mchaw.tauruspay.base.mvp.view.BaseView;
import com.mchaw.tauruspay.common.util.ToastUtils;

import javax.inject.Inject;

/**
 * @author : Bruce Lee
 * @date : 2019/11/27 0027 21:17
 * @description :
 */
public abstract class BasePresentListFragment<T extends BasePresenter> extends BaseFragment implements BaseView {
    protected View loadingView;
    protected View notDataView;
    protected View errorView;

    @Inject
    protected T presenter;

    @Override
    protected  void initFragment() {
        if (presenter != null) {
            presenter.attachView(this);
        }
        initHintViews();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(presenter != null){
            presenter.detachView();
        }
    }

    @Override
    public void showError(String msg) {
        if(!TextUtils.isEmpty(msg)) {
            if(!TextUtils.isEmpty(msg)) {
                ToastUtils.showShortToast(getActivity(),msg);
            }
        }
    }

    protected abstract void initHintViews();

    protected abstract void onRefresh();
}
