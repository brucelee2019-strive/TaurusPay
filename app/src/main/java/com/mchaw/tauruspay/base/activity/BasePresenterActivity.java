package com.mchaw.tauruspay.base.activity;

import android.text.TextUtils;

import com.mchaw.tauruspay.base.mvp.presenter.BasePresenter;
import com.mchaw.tauruspay.base.mvp.view.BaseView;
import com.mchaw.tauruspay.common.util.ToastUtils;

import javax.inject.Inject;

/**
 * @author : Bruce Lee
 * @description :
 * @date : 2019/10/30 0030 11:25
 */
public abstract class BasePresenterActivity<T extends BasePresenter> extends BaseActivity implements BaseView {
    @Inject
    protected T presenter;

    @Override
    public void initActivity() {
        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter != null) {
            presenter.detachView();
        }
    }

    @Override
    public void showError(String msg) {
        if(!TextUtils.isEmpty(msg)) {
            ToastUtils.showShortToast(this,msg);
        }
    }
}
