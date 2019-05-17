package com.git.reny.wallpaper.presenter;

import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.core.BasePresenter;
import com.git.reny.wallpaper.core.DisposableCall;
import com.git.reny.wallpaper.core.ServiceHelper;
import com.git.reny.wallpaper.entity.response.HomeList;
import com.git.reny.wallpaper.ui.mvp.SearchView;
import com.git.reny.wallpaper.ui.mvp.UploadView;
import com.git.reny.wallpaper.utils.CommonUtils;
import com.zyctd.mvplib.utils.LogUtils;
import com.zyctd.mvplib.utils.ResUtils;
import com.zyctd.mvplib.utils.ToastUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 搜索
 */
public class SearchPresenter extends BasePresenter<SearchView> {

    private int page = 1;
    private int pageSize = ResUtils.getInteger(R.integer.pageSize);
    private String keyWord;

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public SearchPresenter(SearchView view) {
        super(view);
    }

    @Override
    public void loadData(boolean isRefresh) {
        addDisposable(ServiceHelper.getApi().getSearchList(keyWord, pageSize, isRefresh ? 1 : page)
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
                        getView().setData(isRefresh, data);
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
