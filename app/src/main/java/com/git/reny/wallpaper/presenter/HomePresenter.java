package com.git.reny.wallpaper.presenter;

import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.core.BasePresenter;
import com.git.reny.wallpaper.core.DisposableCall;
import com.git.reny.wallpaper.core.ServiceHelper;
import com.git.reny.wallpaper.entity.response.ImgListData;
import com.git.reny.wallpaper.ui.mvp.HomeView;
import com.git.reny.wallpaper.utils.CommonUtils;
import com.git.reny.wallpaper.utils.ResUtils;
import com.zyctd.mvplib.utils.AppUtils;
import com.zyctd.mvplib.utils.LogUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 首页
 */
public class HomePresenter extends BasePresenter<HomeView> {

    private int page = 1;
    private int pageSize = ResUtils.getInteger(R.integer.pageSize);

    public HomePresenter(HomeView view) {
        super(view);
    }

    @Override
    public void loadData(boolean isRefresh) {
        addDisposable(ServiceHelper.getApi().getImgs(null, pageSize, isRefresh ? 1 : page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCall<ImgListData>(this) {
                    @Override
                    public boolean isRefresh() {
                        return isRefresh;
                    }

                    @Override
                    public boolean isEmpty(ImgListData value) {
                        return value == null || CommonUtils.isEmpty(value.getListData());
                    }

                    @Override
                    public void onSuc(ImgListData data) {
                        page = isRefresh ? 2 : ++page;
                        if(!isEmpty(data)){
                            getView().setData(isRefresh, data);
                        }
                    }

                    @Override
                    public void onErr(Throwable e) {
                        AppUtils.self().showToast(e.getMessage());
                        LogUtils.e(TAG + ":" + e.getMessage());
                    }
                })
        );
    }


}
