package com.mchaw.tauruspay.ui.main.mine.activate.constract;

import com.mchaw.tauruspay.base.mvp.presenter.BasePresenter;
import com.mchaw.tauruspay.base.mvp.view.BaseView;
import com.mchaw.tauruspay.bean.activate.ActivateCodeBean;

import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2019/11/25 17:44
 * @description:
 */
public interface ActivateConstract {
    interface View extends BaseView {
        void setActiveCodeList(List<ActivateCodeBean> list);
        void setActiveCodeListFail();
    }
    interface Presenter extends BasePresenter<ActivateConstract.View> {
        void getActiveCodeList(String api_token);
    }
}
