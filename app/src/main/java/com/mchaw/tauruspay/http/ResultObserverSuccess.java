package com.mchaw.tauruspay.http;

import com.mchaw.tauruspay.common.exception.EmptyException;

import io.reactivex.observers.DisposableObserver;

/**
 * @author : Bruce Lee
 * @description :
 * @date : 2019/10/30 0030 10:42
 */
public abstract class ResultObserverSuccess<T> extends DisposableObserver<T> {

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof EmptyException){
            onSuccess(null);
        }
    }

    @Override
    public void onComplete() {
    }

    public abstract void onSuccess(T t);
}
