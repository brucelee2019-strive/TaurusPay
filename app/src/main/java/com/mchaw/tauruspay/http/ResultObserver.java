package com.mchaw.tauruspay.http;

import com.google.gson.JsonSyntaxException;
import com.mchaw.tauruspay.MyFrameApplication;
import com.mchaw.tauruspay.common.Constant;
import com.mchaw.tauruspay.common.exception.CustomException;
import com.mchaw.tauruspay.common.exception.EmptyException;
import com.mchaw.tauruspay.common.exception.SessionInvalidException;
import com.mchaw.tauruspay.common.util.PreferencesUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

/**
 * @author : Bruce Lee
 * @description :
 * @date : 2019/10/30 0030 10:07
 */
public abstract class ResultObserver<T> extends DisposableObserver<T> {
    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof EmptyException) {
            onSuccess(null);
        } else if (e instanceof SessionInvalidException) {
            onFail(null);
            sessionInvalid();
        } else if (e instanceof UnknownHostException
                || e instanceof HttpException
                || e instanceof SocketTimeoutException
                || e instanceof ConnectException) {
            onFail(null);
        } else if (e instanceof JsonSyntaxException){
            onFail(null);
        } else if (e instanceof CustomException) {
            CustomException exception = (CustomException) e;
            if (exception.getCode() == 500 && "抱歉，您的操作出问题了，请反馈客服处理，谢谢".equals(exception.getMessage())) {
                onFail(null);
            } else {
                onFail(((CustomException) e).getCode(),e.getMessage(),((CustomException) e).getData());
            }
        }else {
            onFail(e.getMessage());
        }
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T t);

    public void onFail(String msg) {
    }

    public void onFail(int code, String msg) {
        onFail(msg);
    }

    public void onFail(int code, String msg, Object data) {
        onFail(code, msg);
    }

    private void sessionInvalid() {
        PreferencesUtils.putString(MyFrameApplication.getInstance(), Constant.PRE_SESSION_KEY, "");
    }
}
