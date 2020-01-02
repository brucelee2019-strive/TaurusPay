package com.mchaw.tauruspay.ui.repository;

import com.mchaw.tauruspay.bean.entry.MultipleItem;
import com.mchaw.tauruspay.bean.qrcode.DeleteQRCodeGroupBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeGroupCreateBean;
import com.mchaw.tauruspay.bean.qrcode.GroupinfoBean;
import com.mchaw.tauruspay.bean.qrcode.QRCodeUrlBean;
import com.mchaw.tauruspay.http.ResultDisposable;
import com.mchaw.tauruspay.http.ScheduleTranformer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Bruce Lee
 * @date : 2019/11/13 11:29
 * @description:二维码档口相关订阅名目
 */
public class QRCodeModel extends BaseModel{
    @Inject
    public QRCodeModel() {

    }

    //二维码组列表
    public Observable<List<GroupinfoBean>> getQRCodeGroupList(String api_token) {
        return apiService.getQRCodeGroupList(api_token)
                .compose(new ResultDisposable<List<GroupinfoBean>>())
                .compose(new ScheduleTranformer<List<GroupinfoBean>>());
    }

    //创建二维码组
    public Observable<QRCodeGroupCreateBean> getQRCodeGroupBean(String api_token,String account,String nick,String paytype) {
        return apiService.getQRCodeGroupBean(api_token,account,nick,paytype)
                .compose(new ResultDisposable<QRCodeGroupCreateBean>())
                .compose(new ScheduleTranformer<QRCodeGroupCreateBean>());
    }

    //上传二维码url
    public Observable<QRCodeUrlBean> getUpLoadingQRCodeUrlBean(String token,String codeid,String url) {
        return apiService.getUpLoadingQRCodeUrlBean(token,codeid,url)
                .compose(new ResultDisposable<QRCodeUrlBean>())
                .compose(new ScheduleTranformer<QRCodeUrlBean>());
    }

    //获取分组二维码信息
    public Observable<GroupinfoBean> getQRCodeStalls(String groupid, String api_token) {
        return apiService.getQRCodeStalls(groupid,api_token)
                .compose(new ResultDisposable<GroupinfoBean>())
                .compose(new ScheduleTranformer<GroupinfoBean>());
    }

    //删除二维码分组
    public Observable<DeleteQRCodeGroupBean> deleteQRCodeGroup(String groupid, String api_token) {
        return apiService.deleteQRCodeGroup(groupid,api_token)
                .compose(new ResultDisposable<DeleteQRCodeGroupBean>())
                .compose(new ScheduleTranformer<DeleteQRCodeGroupBean>());
    }

}
