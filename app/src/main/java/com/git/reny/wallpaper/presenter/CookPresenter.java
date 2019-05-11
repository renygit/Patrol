package com.git.reny.wallpaper.presenter;

import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.core.BasePresenter;
import com.git.reny.wallpaper.core.DisCall;
import com.git.reny.wallpaper.core.DisposableCall;
import com.git.reny.wallpaper.core.ServiceHelper;
import com.git.reny.wallpaper.entity.response.ReplyList;
import com.git.reny.wallpaper.entity.response.UserData;
import com.git.reny.wallpaper.ui.mvp.CookView;
import com.git.reny.wallpaper.utils.CommonUtils;
import com.zyctd.mvplib.utils.AppUtils;
import com.zyctd.mvplib.utils.LogUtils;
import com.zyctd.mvplib.utils.ResUtils;
import com.zyctd.mvplib.utils.ToastUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 分享的美食详情
 */
public class CookPresenter extends BasePresenter<CookView> {

    private int page = 1;
    private int pageSize = ResUtils.getInteger(R.integer.pageSize);

    private String cookId;

    public void setCookId(String cookId) {
        this.cookId = cookId;
    }

    public CookPresenter(CookView view) {
        super(view);
    }

    @Override
    public void loadData(boolean isRefresh) {
        addDisposable(ServiceHelper.getApi().getReplyList(cookId, pageSize, isRefresh ? 1 : page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCall<ReplyList>(this) {
                    @Override
                    public boolean isRefresh() {
                        return isRefresh;
                    }

                    @Override
                    public boolean isEmpty(ReplyList value) {
                        return value == null || CommonUtils.isEmpty(value.getListData());
                    }

                    @Override
                    public void onSuc(ReplyList data) {
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

    public void reply(String replyId, String content) {
        AppUtils.self().showLoading(getActivity(), true);
        addDisposable(ServiceHelper.getApi().reply(UserData.getUserId(), replyId, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisCall<Object>() {
                    @Override
                    public void onSuc(Object data) {
                        ToastUtils.showLong("评论成功，刷新可以查看");
                    }

                    @Override
                    public void onErr(Throwable e) {
                        ToastUtils.showLong(e.getMessage());
                    }
                })
        );
    }
}
