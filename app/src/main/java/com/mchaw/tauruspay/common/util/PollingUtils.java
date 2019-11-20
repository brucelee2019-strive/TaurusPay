package com.mchaw.tauruspay.common.util;



import android.util.Log;

import com.mchaw.tauruspay.ui.main.mine.dialog.ChangeBankCardDialog;

import org.reactivestreams.Subscriber;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Bruce Lee
 * @date : 2019/11/20 9:19
 * @description: 轮询仿写类
 */
public class PollingUtils {
    private Disposable disposable;
    public void startPolling(int time) {
        disposable = Observable.interval(0, time, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.e("MainActivity", "----------RxJava 定时轮询任务----------");
                    }
                });
    }

    public void stopPolling() {
        if(disposable!=null) {
            disposable.dispose();
        }
    }
}
