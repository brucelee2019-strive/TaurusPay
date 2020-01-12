package com.mchaw.tauruspay.ui.main.recharge.constract;

import com.mchaw.tauruspay.base.mvp.presenter.BasePresenter;
import com.mchaw.tauruspay.base.mvp.view.BaseView;
import com.mchaw.tauruspay.bean.home.UserBean;
import com.mchaw.tauruspay.bean.recharge.AuditBean;
import com.mchaw.tauruspay.bean.recharge.RechargeAuditBean;
import com.mchaw.tauruspay.bean.recharge.RechargeBean;

import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2020/1/10 11:09
 * @description:
 */
public interface RechargeAuditConstract {
    interface View extends BaseView {
        void setHomeDataBean(UserBean userBean);
        void setRechargeAudit(AuditBean auditBean);
        void setRechargeAuditFail();
        void setRechargeAuditList(List<RechargeAuditBean> list);
        void setRechargeAuditListFail();
    }

    interface Presenter extends BasePresenter<RechargeAuditConstract.View> {
        void getHomeDataBean(String api_token);
        void getRechargeAudit(String api_token,String order,int status);
        void getRechargeAuditList(String api_token,int type,int page);
    }
}
