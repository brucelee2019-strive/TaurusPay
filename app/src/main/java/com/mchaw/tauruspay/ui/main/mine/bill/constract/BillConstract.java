package com.mchaw.tauruspay.ui.main.mine.bill.constract;

import com.mchaw.tauruspay.base.mvp.presenter.BasePresenter;
import com.mchaw.tauruspay.base.mvp.view.BaseView;
import com.mchaw.tauruspay.bean.bill.BillTotalBean;

/**
 * @author Bruce Lee
 * @date : 2019/11/25 18:31
 * @description:
 */
public interface BillConstract {
    interface View extends BaseView {
        void setBillList(BillTotalBean billTotalBean);
        void setBillListFail();
    }
    interface Presenter extends BasePresenter<BillConstract.View> {
        void getBillList(String api_token,int status,int page);
    }
}
