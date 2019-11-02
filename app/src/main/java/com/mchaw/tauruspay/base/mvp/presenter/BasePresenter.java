package com.mchaw.tauruspay.base.mvp.presenter;

import com.mchaw.tauruspay.base.mvp.view.BaseView;

/**
 * @author : Bruce Lee
 * @description :
 * @date : 2019/10/28 0028 23:30
 */
public interface BasePresenter <T extends BaseView>{

    /**
     * 关联View
     * @param view
     */
    void attachView(T view);

    /**
     * 取消关联
     */
    void detachView();
}
