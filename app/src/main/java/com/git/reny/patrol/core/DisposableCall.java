package com.git.reny.patrol.core;

import com.zyctd.mvplib.utils.AppUtils;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by reny on 2017/11/16.
 */

public abstract class DisposableCall<T> extends DisposableObserver<T> {

    private BasePresenter presenter;

    protected DisposableCall(BasePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onNext(T value) {
        AppUtils.self().hideLoading();
        if (null != presenter && null != presenter.getActivity()) {
            presenter.getView().finishRefresh(isRefresh(), isEmpty(value), false);
            onSuc(value);
        }
    }

    @Override
    public void onError(Throwable e) {
        AppUtils.self().hideLoading();
        if (null != presenter && null != presenter.getActivity()) {
            presenter.getView().finishRefresh(isRefresh(), true, true);
            onErr(e);
        }
    }

    @Override
    public void onComplete() {
        AppUtils.self().hideLoading();
    }

    public abstract boolean isRefresh();

    public abstract boolean isEmpty(T value);

    public abstract void onSuc(T data);

    public abstract void onErr(Throwable e);

}
