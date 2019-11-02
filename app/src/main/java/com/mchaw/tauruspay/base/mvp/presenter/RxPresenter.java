package com.mchaw.tauruspay.base.mvp.presenter;

import com.mchaw.tauruspay.base.mvp.view.BaseView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author : Bruce Lee
 * @description :
 * @date : 2019/10/28 0028 23:32
 */
public class RxPresenter <T extends BaseView> implements BasePresenter<T>{
    protected  T mView;
    protected CompositeDisposable mCompositeDisposable;

    protected void addSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    protected void removeSubscribe(Disposable disposable){
        if (mCompositeDisposable != null && disposable != null){
            mCompositeDisposable.remove(disposable);
        }
    }

    protected void unSubscribe() {
        if(mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }
}
