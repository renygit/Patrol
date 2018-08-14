package com.zyctd.mvplib.base;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by reny on 2017/11/15.
 */

public interface IRPresenter {
    void addDisposable(Disposable disposable);
    void checkPermissions(DisposableObserver<Boolean> onNext, String... permissions);
    void loadData(boolean isRefresh);

    void onCreate();
    void onDestroy();
}
