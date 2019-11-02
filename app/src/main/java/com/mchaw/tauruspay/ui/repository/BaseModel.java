package com.mchaw.tauruspay.ui.repository;

import com.mchaw.tauruspay.http.APIService;
import com.mchaw.tauruspay.http.ResultDisposable;
import com.mchaw.tauruspay.http.ScheduleTranformer;
import com.mchaw.tauruspay.bean.ResultBean;



import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author : Bruce Lee
 * @description :
 * @date : 2019/10/29 0029 21:46
 */
public class BaseModel {
    @Inject
    protected APIService apiService;

    public <T> Observable<T> convert(Observable<ResultBean<T>> observable){
        return observable
                .compose(new ResultDisposable<T>())
                .compose(new ScheduleTranformer<T>());
    }
}
