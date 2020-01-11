package com.mchaw.tauruspay.ui.main.mine.agency.constract;

import com.mchaw.tauruspay.base.mvp.presenter.BasePresenter;
import com.mchaw.tauruspay.base.mvp.view.BaseView;
import com.mchaw.tauruspay.bean.agency.AgencyBean;

/**
 * @author Bruce Lee
 * @date : 2020/1/11 14:04
 * @description:
 */
public interface AgencyListConstract {
    interface View extends BaseView {
        void setAgent(AgencyBean agencyBean);
        void setAgentFail();
    }
    interface Presenter extends BasePresenter<AgencyListConstract.View> {
        void getAgent(String api_token);
    }
}
