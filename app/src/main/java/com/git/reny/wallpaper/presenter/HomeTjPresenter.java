package com.git.reny.wallpaper.presenter;

import com.git.reny.wallpaper.core.BasePresenter;
import com.git.reny.wallpaper.core.DisposableCall;
import com.git.reny.wallpaper.core.ServiceHelper;
import com.git.reny.wallpaper.entity.response.HomeRecommend;
import com.git.reny.wallpaper.entity.response.HomeRecommendList;
import com.git.reny.wallpaper.ui.mvp.HomeTjView;
import com.git.reny.wallpaper.utils.CommonUtils;
import com.zyctd.mvplib.utils.AppUtils;
import com.zyctd.mvplib.utils.LogUtils;
import com.zyctd.mvplib.utils.ToastUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 首页-推荐
 */
public class HomeTjPresenter extends BasePresenter<HomeTjView> {

    private String lastid = "";

    public HomeTjPresenter(HomeTjView view) {
        super(view);
    }

    @Override
    public void loadData(boolean isRefresh) {
        if(isRefresh) {
            lastid = "";
            addDisposable(ServiceHelper.getApi().getHomeRecommend()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableCall<HomeRecommend>(this) {
                        @Override
                        public boolean isRefresh() {
                            return isRefresh;
                        }

                        @Override
                        public boolean isEmpty(HomeRecommend value) {
                            return value == null;
                        }

                        @Override
                        public void onSuc(HomeRecommend data) {
                            if (!isEmpty(data)) {
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
        loadListData(isRefresh);
    }

    private void loadListData(boolean isRefresh){
        addDisposable(ServiceHelper.getApi().getHomeRecommendList(lastid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCall<HomeRecommendList>(this) {
                    @Override
                    public boolean isRefresh() {
                        return isRefresh;
                    }

                    @Override
                    public boolean isEmpty(HomeRecommendList value) {
                        return value == null || CommonUtils.isEmpty(value.getListData());
                    }

                    @Override
                    public void onSuc(HomeRecommendList data) {
                        if (!isEmpty(data)) {
                            lastid = data.getListData().get(data.getListData().size() - 1).getId();
                            getView().setListData(isRefresh, data);
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
