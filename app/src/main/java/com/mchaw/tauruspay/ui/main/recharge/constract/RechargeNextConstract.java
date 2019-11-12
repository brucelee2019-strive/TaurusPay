package com.mchaw.tauruspay.ui.main.recharge.constract;

import com.mchaw.tauruspay.base.mvp.presenter.BasePresenter;
import com.mchaw.tauruspay.base.mvp.view.BaseView;
import com.mchaw.tauruspay.bean.login.LoginBean;
import com.mchaw.tauruspay.bean.recharge.RechargeNextBean;
import com.mchaw.tauruspay.bean.recharge.RechargeSureBean;
import com.mchaw.tauruspay.ui.login.constract.LoginConstract;

/**
 * @author Bruce Lee
 * @date : 2019/11/11 17:03
 * @description:
 */
public interface RechargeNextConstract {
    interface View extends BaseView {
       void setRechargeNextBean(RechargeNextBean rechargeNextBean);
       void setRechargeNextFail();

       void setRechargeSureBean(RechargeSureBean rechargeSureBean);
    }
    interface Presenter extends BasePresenter<RechargeNextConstract.View> {
        void getRechargeNextBean(String paymentNum,String token);

        void getRechargeSureBean(String orderId,String token);
    }
}
