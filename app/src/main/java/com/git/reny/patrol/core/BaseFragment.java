package com.git.reny.patrol.core;

import android.os.Bundle;

import com.renygit.multistateview.MultiStateView;
import com.umeng.analytics.MobclickAgent;
import com.zyctd.mvplib.base.RBaseFragment;
import com.zyctd.mvplib.base.RBasePresenter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

/**
 * Created by admin on 2017/6/7.
 */

public abstract class BaseFragment<P extends RBasePresenter> extends RBaseFragment<P> implements IBaseView{

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
    }

    @Override
    protected void onResumeLazy() {
        super.onResumeLazy();
        MobclickAgent.onPageStart(TAG); //统计页面 记得父Activity onResume中就不用写这句了
    }

    @Override
    protected void onPauseLazy() {
        super.onPauseLazy();
        MobclickAgent.onPageEnd(TAG); //统计页面
    }

    @Override
    protected MultiStateView getMultiStateView() {
        return null;
    }

    @Override
    protected RefreshLayout getRefreshLayout() {
        return null;
    }
}
