package com.git.reny.wallpaper.presenter;

import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.core.BasePresenter;
import com.git.reny.wallpaper.core.DisposableCall;
import com.git.reny.wallpaper.core.ServiceHelper;
import com.git.reny.wallpaper.entity.response.HomeList;
import com.git.reny.wallpaper.ui.mvp.HomeOtherView;
import com.git.reny.wallpaper.utils.CommonUtils;
import com.zyctd.mvplib.utils.ResUtils;
import com.zyctd.mvplib.utils.AppUtils;
import com.zyctd.mvplib.utils.LogUtils;
import com.zyctd.mvplib.utils.ToastUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 首页-推荐
 */
public class HomeOtherPresenter extends BasePresenter<HomeOtherView> {

    private int page = 1;
    private int pageSize = ResUtils.getInteger(R.integer.pageSize);
    private String category;

    public void setCategory(String category) {
        this.category = category;
    }

    public HomeOtherPresenter(HomeOtherView view) {
        super(view);
    }

    @Override
    public void loadData(boolean isRefresh) {
        addDisposable(ServiceHelper.getApi().getHomeList(category, pageSize, isRefresh ? 1 : page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCall<HomeList>(this) {
                    @Override
                    public boolean isRefresh() {
                        return isRefresh;
                    }

                    @Override
                    public boolean isEmpty(HomeList value) {
                        return value == null || CommonUtils.isEmpty(value.getListData());
                    }

                    @Override
                    public void onSuc(HomeList data) {
                        page = isRefresh ? 2 : ++page;
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

}
