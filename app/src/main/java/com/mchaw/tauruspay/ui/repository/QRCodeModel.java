package com.mchaw.tauruspay.ui.repository;

import com.mchaw.tauruspay.bean.ALiYunCodeBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeGroupBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeGroupCreateBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeStallBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeUrlBean;
import com.mchaw.tauruspay.http.ResultDisposable;
import com.mchaw.tauruspay.http.ScheduleTranformer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Bruce Lee
 * @date : 2019/11/13 11:29
 * @description:
 */
public class QRCodeModel extends BaseModel{
    @Inject
    public QRCodeModel() {

    }

    //二维码组列表
    public Observable<List<QRCodeGroupBean>> getQRCodeGroupList(String api_token) {
        return apiService.getQRCodeGroupList(api_token)
                .compose(new ResultDisposable<List<QRCodeGroupBean>>())
                .compose(new ScheduleTranformer<List<QRCodeGroupBean>>());
    }

    //创建二维码列表
    public Observable<QRCodeGroupCreateBean> getQRCodeGroupBean(String api_token,String account,String nick,String paytype) {
        return apiService.getQRCodeGroupBean(api_token,account,nick,paytype)
                .compose(new ResultDisposable<QRCodeGroupCreateBean>())
                .compose(new ScheduleTranformer<QRCodeGroupCreateBean>());
    }

    public Observable<QRCodeUrlBean> getUpLoadingQRCodeUrlBean(String token,String codeid,String url) {
        return apiService.getUpLoadingQRCodeUrlBean(token,codeid,url)
                .compose(new ResultDisposable<QRCodeUrlBean>())
                .compose(new ScheduleTranformer<QRCodeUrlBean>());
    }



    public Observable<QRCodeStallBean> getQRCodeStalls(String groupid, String api_token) {
        return apiService.getQRCodeStalls(groupid,api_token)
                .compose(new ResultDisposable<QRCodeStallBean>())
                .compose(new ScheduleTranformer<QRCodeStallBean>());
    }
}
