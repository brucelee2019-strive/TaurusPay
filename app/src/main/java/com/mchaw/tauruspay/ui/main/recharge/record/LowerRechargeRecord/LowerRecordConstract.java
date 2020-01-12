package com.mchaw.tauruspay.ui.main.recharge.record.LowerRechargeRecord;

import com.mchaw.tauruspay.base.mvp.presenter.BasePresenter;
import com.mchaw.tauruspay.base.mvp.view.BaseView;
import com.mchaw.tauruspay.bean.recharge.RechargeAuditBean;

import java.util.List;

/**
 * @author : Bruce Lee
 * @date : 2020/1/12 0012 14:34
 * @description :
 */
public interface LowerRecordConstract {
    interface View extends BaseView {
        void setRechargeAuditList(List<RechargeAuditBean> list);
        void setRechargeAuditListFail();
    }

    interface Presenter extends BasePresenter<LowerRecordConstract.View> {
        void getRechargeAuditList(String api_token,int type,int page);
    }
}
