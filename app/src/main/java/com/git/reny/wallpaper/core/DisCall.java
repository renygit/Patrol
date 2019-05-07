package com.git.reny.wallpaper.core;

import com.zyctd.mvplib.utils.AppUtils;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by reny on 2017/11/16.
 */

public abstract class DisCall<T> extends DisposableObserver<T> {

    @Override
    public void onNext(T value) {
        AppUtils.self().hideLoading();
        onSuc(value);
    }

    @Override
    public void onError(Throwable e) {
        AppUtils.self().hideLoading();
        onErr(e);
    }

    @Override
    public void onComplete() {
        AppUtils.self().hideLoading();
    }

    public abstract void onSuc(T data);

    public abstract void onErr(Throwable e);

}
