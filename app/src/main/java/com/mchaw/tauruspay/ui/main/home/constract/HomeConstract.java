package com.mchaw.tauruspay.ui.main.home.constract;

import com.mchaw.tauruspay.base.mvp.presenter.BasePresenter;
import com.mchaw.tauruspay.base.mvp.view.BaseView;

/**
 * @author Bruce Lee
 * @date : 2019/11/12 16:40
 * @description:
 */
public interface HomeConstract {
    interface View extends BaseView {

    }
    interface Presenter extends BasePresenter<HomeConstract.View> {

    }
}
