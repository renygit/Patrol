package com.git.reny.wallpaper.presenter;

import com.git.reny.wallpaper.core.BasePresenter;
import com.git.reny.wallpaper.core.DisposableCall;
import com.git.reny.wallpaper.core.ServiceHelper;
import com.git.reny.wallpaper.entity.response.CookList;
import com.git.reny.wallpaper.ui.mvp.ReplyRecommandView;
import com.git.reny.wallpaper.utils.CommonUtils;
import com.zyctd.mvplib.utils.LogUtils;
import com.zyctd.mvplib.utils.ToastUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 首页-
 */
public class ReplyRecommandPresenter extends BasePresenter<ReplyRecommandView> {

    public ReplyRecommandPresenter(ReplyRecommandView view) {
        super(view);
    }

    @Override
    public void loadData(boolean isRefresh) {
        addDisposable(ServiceHelper.getApi().getCookList(10, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCall<CookList>(this) {
                    @Override
                    public boolean isRefresh() {
                        return isRefresh;
                    }

                    @Override
                    public boolean isEmpty(CookList value) {
                        return value == null || CommonUtils.isEmpty(value.getListData());
                    }

                    @Override
                    public void onSuc(CookList data) {
                        if(!isEmpty(data)){
                            getView().setData(isRefresh, data.getListData());
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
