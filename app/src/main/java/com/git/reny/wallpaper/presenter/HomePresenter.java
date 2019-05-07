package com.git.reny.wallpaper.presenter;

import com.git.reny.wallpaper.core.BasePresenter;
import com.git.reny.wallpaper.core.DisposableCall;
import com.git.reny.wallpaper.core.ServiceHelper;
import com.git.reny.wallpaper.entity.response.ListResults;
import com.git.reny.wallpaper.ui.mvp.HomeView;
import com.git.reny.wallpaper.utils.CommonUtils;
import com.zyctd.mvplib.utils.AppUtils;
import com.zyctd.mvplib.utils.LogUtils;
import com.zyctd.mvplib.utils.ToastUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 首页
 */
public class HomePresenter extends BasePresenter<HomeView> {

    public HomePresenter(HomeView view) {
        super(view);
    }

    @Override
    public void loadData(boolean isRefresh) {
        addDisposable(ServiceHelper.getApi().getHomeCategorys()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCall<ListResults>(this) {
                    @Override
                    public boolean isRefresh() {
                        return isRefresh;
                    }

                    @Override
                    public boolean isEmpty(ListResults value) {
                        return value == null || CommonUtils.isEmpty(value.getListData());
                    }

                    @Override
                    public void onSuc(ListResults data) {
                        if(!isEmpty(data)){
                            getView().setData(isRefresh, data);
                        }
                    }
                    @Override
                    public void onErr(Throwable e) {
                        ToastUtils.showLong(e.getMessage());
                        LogUtils.e(TAG + ":" + e.getMessage());
                    }
                })
        );
    }


}
