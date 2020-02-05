package com.mchaw.tauruspay.ui.main.mine.withdraw;

import com.mchaw.tauruspay.base.mvp.presenter.BasePresenter;
import com.mchaw.tauruspay.base.mvp.view.BaseView;
import com.mchaw.tauruspay.bean.home.UserBean;
import com.mchaw.tauruspay.bean.withdraw.WithdrawBean;

/**
 * @author Bruce Lee
 * @date : 2020/2/5 20:34
 * @description:
 */
public interface WithdrawConstract {
    interface View extends BaseView {
        void setCashSucceed(WithdrawBean withdrawBean);
        void setCashFailed();
        void setHomeDataBean(UserBean userBean);
    }
    interface Presenter extends BasePresenter<WithdrawConstract.View> {
        void getCash(String api_token,String quota,String bank,String bankname,String bankname2,String account,String cardnumber,String phone,String password);
        void getHomeDataBean(String api_token);
    }
}
