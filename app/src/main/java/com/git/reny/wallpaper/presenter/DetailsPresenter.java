package com.git.reny.wallpaper.presenter;

import com.git.reny.wallpaper.core.BasePresenter;
import com.git.reny.wallpaper.core.DisposableCall;
import com.git.reny.wallpaper.core.ServiceHelper;
import com.git.reny.wallpaper.entity.response.HomeRecommendDetails;
import com.git.reny.wallpaper.ui.mvp.DetailsView;
import com.zyctd.mvplib.utils.LogUtils;
import com.zyctd.mvplib.utils.ToastUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 分享的美食详情
 */
public class DetailsPresenter extends BasePresenter<DetailsView> {

    private String ids;

    public void setIds(String ids) {
        this.ids = ids;
    }

    public DetailsPresenter(DetailsView view) {
        super(view);
    }

    @Override
    public void loadData(boolean isRefresh) {
        addDisposable(ServiceHelper.getApi().getHomeRecommendDetails(ids)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCall<HomeRecommendDetails>(this) {
                    @Override
                    public boolean isRefresh() {
                        return isRefresh;
                    }

                    @Override
                    public boolean isEmpty(HomeRecommendDetails value) {
                        return value == null;
                    }

                    @Override
                    public void onSuc(HomeRecommendDetails data) {
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
