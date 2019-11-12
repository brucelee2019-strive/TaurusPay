package com.mchaw.tauruspay.ui.main.recharge.constract;

import com.mchaw.tauruspay.base.mvp.presenter.BasePresenter;
import com.mchaw.tauruspay.base.mvp.view.BaseView;
import com.mchaw.tauruspay.bean.home.HomeDataBean;
import com.mchaw.tauruspay.bean.recharge.RechargeBean;

import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2019/11/12 12:28
 * @description:
 */
public interface RechargeListConstract {
    interface View extends BaseView {
       void setRechargeList(List<RechargeBean> list);
        void setHomeDataBean(HomeDataBean homeDataBean);
    }
    interface Presenter extends BasePresenter<RechargeListConstract.View> {
        void getRechargeList(String token);
        void getHomeDataBean(String api_token);
    }
}
