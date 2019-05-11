package com.zyctd.mvplib.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.renygit.multistateview.MultiStateView;
import com.zyctd.mvplib.utils.AppUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by reny on 2017/6/5.
 */

public abstract class RBaseActivity<P extends RBasePresenter> extends AppCompatActivity implements IRBaseView {

    public final String TAG = this.getClass().getSimpleName();
    private Unbinder mUnbinder;
    protected P presenter;

    private MultiStateView mMultiStateView;
    private RefreshLayout mRefreshLayout;
    private boolean isFirstLoadData = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView
        try {
            int layoutResID = getLayoutId();
            if (layoutResID != 0) {//如果initView返回0,框架则不会调用setContentView(),当然也不会 Bind ButterKnife
                setContentView(layoutResID);
                //绑定到butterknife
                mUnbinder = ButterKnife.bind(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //EventBus
        if(useEventBus()){
            EventBus.getDefault().register(this);
        }

        //Lifecycle register presenter
        presenter = obtainPresenter();
        if(null != presenter) {
            getLifecycle().addObserver(presenter);
        }

        mMultiStateView = getMultiStateView();
        if(null != mMultiStateView){
            mMultiStateView.setOnRetryListener(() -> {
                mMultiStateView.showLoading();
                if(null != presenter) {
                    presenter.loadData(true);
                }
            });
        }
        mRefreshLayout = getRefreshLayout();
        if(null != mRefreshLayout){
            mRefreshLayout.setEnableAutoLoadmore(true);//开启自动加载功能（非必须）
            mRefreshLayout.setEnableLoadmoreWhenContentNotFull(true);//是否在列表不满一页时候开启上拉加载功能
            mRefreshLayout.setOnRefreshListener(rl -> {
                if(null != presenter) {
                    presenter.loadData(true);
                }
            });
            mRefreshLayout.setOnLoadmoreListener(rl -> {
                if(null != presenter) {
                    presenter.loadData(false);
                }
            });
        }

        init(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (null == presenter) presenter = obtainPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(useEventBus() && EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
        if(null != presenter) {
            getLifecycle().removeObserver(presenter);
        }
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) mUnbinder.unbind();
        this.presenter = null;
        this.mUnbinder = null;

        AppUtils.self().release();
    }


    @Override
    public void finishRefresh(boolean isRefresh, boolean isEmpty, boolean isError) {
        if(null != mRefreshLayout && isRefresh){
            mRefreshLayout.setLoadmoreFinished(false);
        }
        if(isRefresh && isFirstLoadData){
            if(isError){
                if(null != mMultiStateView) {
                    if(AppUtils.self().isConnected()) {
                        mMultiStateView.showError();
                    }else {
                        mMultiStateView.showNoNetwork();
                    }
                }
            }else if(isEmpty){
                if(null != mMultiStateView) mMultiStateView.showEmpty();
            }else {
                isFirstLoadData = false;
                if(null != mMultiStateView) mMultiStateView.showContent();
            }
        }
        if(null != mRefreshLayout){
            if(isRefresh) {
                mRefreshLayout.finishRefresh();
            }else {
                mRefreshLayout.finishLoadmore();
                if(isEmpty)mRefreshLayout.setLoadmoreFinished(true);
            }
        }

    }

    public void setFirstLoadData(boolean firstLoadData) {
        isFirstLoadData = firstLoadData;
    }

    @Override
    public boolean useEventBus() {
        return false;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    protected abstract int getLayoutId();

    protected abstract P obtainPresenter();

    protected abstract void init(Bundle savedInstanceState);

    protected abstract MultiStateView getMultiStateView();
    protected abstract RefreshLayout getRefreshLayout();

}
