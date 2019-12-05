package com.mchaw.tauruspay.ui.main.mine.qrcode.constract;

import com.mchaw.tauruspay.base.mvp.presenter.BasePresenter;
import com.mchaw.tauruspay.base.mvp.view.BaseView;
import com.mchaw.tauruspay.bean.qrcode.DeleteQRCodeGroupBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeGroupCreateBean;
import com.mchaw.tauruspay.bean.qrcode.GroupinfoBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeUrlBean;

import java.util.List;

/**
 * @author Bruce Lee
 * @date : 2019/11/13 11:19
 * @description:
 */
public interface QRCodeConstract {
    interface View extends BaseView {
        void setQRCodeGroupList(List<GroupinfoBean> list);
        void setQRCodeGroupListFail();
        void setQRCodeGroupBean(QRCodeGroupCreateBean qrCodeGroupCreateBean);
        void setUpLoadingQRCodeUrlBean(QRCodeUrlBean qrCodeUrlBean);
        void setQRCodeStalls(GroupinfoBean groupinfoBean);
        void setDeleteQRCodeGroup(DeleteQRCodeGroupBean deleteQRCodeGroupBean);
        void setUpLoadingQRCodeUrlBeanFail();
    }

    interface Presenter extends BasePresenter<QRCodeConstract.View> {
        void getQRCodeGroupList(String token);
        void getQRCodeGroupBean(String token,String account,String nick,String paytype);
        void getUpLoadingQRCodeUrlBean(String token,String codeid,String url);
        void getQRCodeStalls(String groupid,String api_token);
        void deleteQRCodeGroup(String groupid,String api_token);
    }
}
