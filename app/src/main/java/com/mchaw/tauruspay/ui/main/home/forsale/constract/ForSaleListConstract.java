package com.mchaw.tauruspay.ui.main.home.forsale.constract;

import com.mchaw.tauruspay.base.mvp.presenter.BasePresenter;
import com.mchaw.tauruspay.base.mvp.view.BaseView;
import com.mchaw.tauruspay.bean.home.StartOrOverSellBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeGroupBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeStallBean;

import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2019/11/20 15:19
 * @description:
 */
public interface ForSaleListConstract {
    interface View extends BaseView {
        void setQRCodeGroupList(List<QRCodeGroupBean> list);
        void setQRCodeGroupListFail();
        void setQRCodeStalls(QRCodeStallBean qrCodeStallBean);
        void setStartingOrOverSell(StartOrOverSellBean startOrOverSellBean);
    }

    interface Presenter extends BasePresenter<ForSaleListConstract.View> {
        void getQRCodeGroupList(String token);
        void getQRCodeStalls(String groupid,String api_token);
        void startingOrOverSell(String groupid,int state,String token);
    }
}
