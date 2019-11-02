package com.mchaw.tauruspay.http;

import com.mchaw.tauruspay.common.exception.CustomException;
import com.mchaw.tauruspay.common.exception.EmptyException;
import com.mchaw.tauruspay.common.exception.SessionInvalidException;
import com.mchaw.tauruspay.bean.ResultBean;

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
    //请求成功
    private static final int CODE_SUCCESS = 200;
    //请求失败
    private static final int CODE_FAIL = 500;
    //Session失效
    private static final int CODE_SESSION_INVALID = 9999;
    //请求失败
    private static final int CODE_SERVICE_ERROR = 10010008;

    //1100013 赔率发生变化1100013
    public static final int CODE_RATE_CHANGE = 1100013;

    //1100038 竞猜下单赛事不存在
    public static final int CODE_RATE_MATCH_STOP = 1100038;


    @Override
    public ObservableSource<T> apply(Observable<ResultBean<T>> upstream) {
        return upstream.flatMap(new Function<ResultBean<T>, ObservableSource<T>>() {

            @Override
            public ObservableSource<T> apply(ResultBean<T> resultInfo) throws Exception {
                if(resultInfo.code == CODE_SUCCESS) {
                    if (resultInfo.data == null || resultInfo.data == "") {
                        return Observable.error(new EmptyException());
                    }else{
                        return Observable.just(resultInfo.data);
                    }
                }else if (resultInfo.code == CODE_SESSION_INVALID) {
                    return Observable.error(new SessionInvalidException());
                }else {
                    return Observable.error(new CustomException(resultInfo.code,resultInfo.msg,resultInfo.data));
                }
            }
        });
    }
}
