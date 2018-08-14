package com.zyctd.mvplib.base;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Intent;

import com.zyctd.mvplib.utils.LogUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by reny on 2017/11/15.
 */

public abstract class RBasePresenter<V extends IRBaseView> implements IRPresenter,IRBaseView,LifecycleObserver {

    protected final String TAG = this.getClass().getSimpleName();
    private V view;
    public V getView() {
        return view;
    }

    private CompositeDisposable mCompositeDisposable;
    private RxPermissions rxPermissions;


    public RBasePresenter(V view) {
        this.view = view;
    }

    @Override
    public void addDisposable(Disposable disposable) {
        if(null == mCompositeDisposable)mCompositeDisposable = new CompositeDisposable();
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void checkPermissions(DisposableObserver<Boolean> onNext, String[] permissions) {
        if(null == rxPermissions)rxPermissions = new RxPermissions(this.view.getActivity());
        addDisposable(rxPermissions.request(permissions).subscribeWith(onNext));
    }

    @Override
    public void loadData(boolean isRefresh){

    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }



    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void ON_CREATE() {
        if(useEventBus()){
            EventBus.getDefault().register(this);
        }
        onCreate();
        //LogUtils.e("BasePresenter onCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void ON_DESTROY() {
        onDestroy();
        this.view = null;
        if(null != mCompositeDisposable) {
            this.mCompositeDisposable.clear();
            this.mCompositeDisposable = null;
        }
        if(useEventBus() && EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
        //LogUtils.e("BasePresenter onDestroy");
    }




    @Override
    public Activity getActivity() {
        if(null != getView()) {
            return getView().getActivity();
        }
        else {
            LogUtils.e("view is null, can't getActivity");
            return null;
        }
    }

    @Override
    public void startActivity(Intent intent) {
        if(null != getView()) {
            getView().startActivity(intent);
        }else {
            LogUtils.e("view is null, can't startActivity");
        }
    }

    @Override
    public void finish() {
        if(null != getView()) {
            getView().finish();
        }else {
            LogUtils.e("view is null, can't finish");
        }
    }

    /**
     * 是否使用 {@link EventBus},默认为使用(false)
     * @return
     */
    @Override
    public boolean useEventBus() {
        return false;
    }

}
