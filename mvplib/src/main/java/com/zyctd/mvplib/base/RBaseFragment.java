package com.zyctd.mvplib.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.renygit.multistateview.MultiStateView;
import com.zyctd.mvplib.utils.AppUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by reny on 2017/1/6.
 */

public abstract class RBaseFragment<P extends RBasePresenter> extends Fragment implements IRBaseView {

    protected final String TAG = this.getClass().getSimpleName();

    private boolean isInit = false;
    private boolean isStart = false;
    private Bundle savedInstanceState;
    public static final String INTENT_BOOLEAN_LAZYLOAD = "intent_boolean_lazyLoad";
    private boolean isLazyLoad = true;
    private View mRoot;

    private int isVisibleToUserState = VISIBLE_STATE_NOTSET;
    //未设置值
    private static final int VISIBLE_STATE_NOTSET = -1;
    //可见
    private static final int VISIBLE_STATE_VISIABLE = 1;
    //不可见
    private static final int VISIBLE_STATE_GONE = 0;


    private Unbinder mUnbinder;
    protected P presenter;

    private MultiStateView mMultiStateView;
    private RefreshLayout mRefreshLayout;
    private boolean isFirstLoadData = true;

    public RBaseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;

        mRoot = inflater.inflate(getLayoutId(), container, false);
        mUnbinder = ButterKnife.bind(this, mRoot);

        Bundle bundle = getArguments();
        if (bundle != null) {
            isLazyLoad = bundle.getBoolean(INTENT_BOOLEAN_LAZYLOAD, isLazyLoad);
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
        //为什么不直接getUserVisibleHint();而是通过自己存isVisibleToUserState变量判断
        //因为v4的25的版本 已经调用 setUserVisibleHint(true)，结果到这里getUserVisibleHint是false
        // （ps:看了FragmentManager源码Fragment被重新创建有直接赋值isVisibleToUser不知道是不是那里和之前v4有改动的地方）
        //所以我默认VISIBLE_STATE_NOTSET，之前没有调用setUserVisibleHint方法，就用系统的getUserVisibleHint，否则就用setUserVisibleHint后保存的值
        //总之就是调用了setUserVisibleHint 就使用setUserVisibleHint的值
        boolean isVisibleToUser;
        if (isVisibleToUserState == VISIBLE_STATE_NOTSET) {
            isVisibleToUser = getUserVisibleHint();
        } else {
            isVisibleToUser = isVisibleToUserState == VISIBLE_STATE_VISIABLE;
        }

        if (isLazyLoad) {
            if (isVisibleToUser) {
                isInit = true;
                onCreateViewLazy(savedInstanceState);
            }
        } else {
            isInit = true;
            onCreateViewLazy(savedInstanceState);
        }

        if(null == mRoot)
            return super.onCreateView(inflater, container, savedInstanceState);
        return mRoot;
    }

    public View getRootView() {
        return mRoot;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (null == presenter) presenter = obtainPresenter();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisibleToUserState = isVisibleToUser ? VISIBLE_STATE_VISIABLE : VISIBLE_STATE_GONE;
        if (isVisibleToUser && !isInit && mRoot != null) {
            isInit = true;
            onCreateViewLazy(savedInstanceState);
            onResumeLazy();
        }
        if (isInit && mRoot != null) {
            if (isVisibleToUser) {
                isStart = true;
                onStartLazy();
            } else {
                isStart = false;
                onStopLazy();
            }
        }
    }


    @Deprecated
    @Override
    public final void onStart() {
        super.onStart();
        if (isInit && !isStart && getUserVisibleHint()) {
            isStart = true;
            onStartLazy();
        }
    }

    @Deprecated
    @Override
    public final void onStop() {
        super.onStop();
        if (isInit && isStart && getUserVisibleHint()) {
            isStart = false;
            onStopLazy();
        }
    }

    @Override
    @Deprecated
    public final void onResume() {
        super.onResume();
        if (isInit) {
            onResumeLazy();
        }
    }

    @Override
    @Deprecated
    public final void onPause() {
        super.onPause();
        if (isInit) {
            onPauseLazy();
        }
    }

    @Override
    @Deprecated
    public final void onDestroyView() {
        super.onDestroyView();
        if(useEventBus() && EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
        if(null != presenter) {
            getLifecycle().removeObserver(presenter);
        }
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) mUnbinder.unbind();
        this.presenter = null;
        this.mUnbinder = null;
        mRoot = null;
        if (isInit) {
            onDestroyViewLazy();
        }
        isInit = false;
    }


    // http://stackoverflow.com/questions/15207305/getting-the-error-java-lang-illegalstateexception-activity-has-been-destroyed
    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
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

    @Override
    public void finish() {
        getActivity().finish();
    }

    @Override
    public boolean useEventBus() {
        return false;
    }

    protected abstract P obtainPresenter();

    protected void onCreateViewLazy(Bundle savedInstanceState){}

    protected void onResumeLazy(){}

    protected void onStartLazy(){}

    protected void onStopLazy(){}

    protected void onPauseLazy(){}

    protected void onDestroyViewLazy(){}


    protected abstract void init(Bundle savedInstanceState);

    protected abstract int getLayoutId();


    protected abstract MultiStateView getMultiStateView();
    protected abstract RefreshLayout getRefreshLayout();

}