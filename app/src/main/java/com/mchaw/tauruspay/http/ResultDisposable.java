package com.mchaw.tauruspay.http;

import com.mchaw.tauruspay.bean.eventbus.ForbiddenEvent;
import com.mchaw.tauruspay.bean.eventbus.QRCodeNotPassEvent;
import com.mchaw.tauruspay.common.exception.CustomException;
import com.mchaw.tauruspay.common.exception.EmptyException;
import com.mchaw.tauruspay.common.exception.SessionInvalidException;
import com.mchaw.tauruspay.bean.ResultBean;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * @author : Bruce Lee
 * @description :
 * @date : 2019/10/29 0029 21:54
 */
public class ResultDisposable<T> implements ObservableTransformer<ResultBean<T>, T> {
    //"0"表示请求成功
    private static final int CODE_SUCCESS = 0;
    //"-1"表示账号禁用或token失效
    private static final int CODE_FORBIDDEN = -1;
    //"1004"表示代售分组收款码没有上传
    private static final int QR_CODE_NOT_PASS = 1004;
    //请求失败
    private static final int CODE_FAIL = 500;
    //Session失效
    private static final int CODE_SESSION_INVALID = 9999;
    //请求失败
    private static final int CODE_SERVICE_ERROR = 10010008;

    @Override
    public ObservableSource<T> apply(Observable<ResultBean<T>> upstream) {
        return upstream.flatMap(new Function<ResultBean<T>, ObservableSource<T>>() {

            @Override
            public ObservableSource<T> apply(ResultBean<T> resultInfo) throws Exception {
                if (resultInfo.code == CODE_SUCCESS) {
                    if (resultInfo.data == null || resultInfo.data == "") {
                        return Observable.error(new EmptyException());
                    } else {
                        return Observable.just(resultInfo.data);
                    }
                } else if (resultInfo.code == CODE_FORBIDDEN) {
                    //EventBus
                    EventBus.getDefault().post(new ForbiddenEvent());
                    return Observable.error(new SessionInvalidException());
                } else if (resultInfo.code == QR_CODE_NOT_PASS) {
                    //EventBus
                    EventBus.getDefault().post(new QRCodeNotPassEvent());
                    return Observable.error(new SessionInvalidException());
                } else if (resultInfo.code == CODE_SESSION_INVALID) {
                    return Observable.error(new SessionInvalidException());
                } else {
                    return Observable.error(new CustomException(resultInfo.code, resultInfo.msg, resultInfo.data));
                }
            }
        });
    }
}
