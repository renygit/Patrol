package com.git.reny.wallpaper.presenter;

import com.git.reny.wallpaper.core.BasePresenter;
import com.git.reny.wallpaper.core.DisCall;
import com.git.reny.wallpaper.core.ServiceHelper;
import com.git.reny.wallpaper.entity.response.CollectData;
import com.git.reny.wallpaper.entity.response.UserData;
import com.git.reny.wallpaper.ui.mvp.Web_View;
import com.zyctd.mvplib.utils.AppUtils;
import com.zyctd.mvplib.utils.ToastUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 登录
 */
public class WebPresenter extends BasePresenter<Web_View> {

    public WebPresenter(Web_View view) {
        super(view);
    }

    public void isCollect(String cookId) {
        if(!UserData.isLogin()) return;
        AppUtils.self().showLoading(getActivity(), true);
        addDisposable(ServiceHelper.getApi().isCollect(UserData.getUserId(), cookId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisCall<CollectData>() {
                    @Override
                    public void onSuc(CollectData data) {
                        getView().setCollectData(data);
                    }

                    @Override
                    public void onErr(Throwable e) {
                        ToastUtils.showLong(e.getMessage());
                    }
                })
        );
    }

    public void collect(String cookId) {
        if(!UserData.isLogin(getActivity())) return;
        AppUtils.self().showLoading(getActivity(), true);
        addDisposable(ServiceHelper.getApi().collect(UserData.getUserId(), cookId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisCall<CollectData>() {
                    @Override
                    public void onSuc(CollectData data) {
                        ToastUtils.showLong(data.isCollect() ? "收藏成功" : "取消收藏");
                        getView().setCollectData(data);
                    }

                    @Override
                    public void onErr(Throwable e) {
                        ToastUtils.showLong(e.getMessage());
                    }
                })
        );
    }

}
