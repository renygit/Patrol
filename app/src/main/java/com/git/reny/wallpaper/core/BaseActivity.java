package com.git.reny.wallpaper.core;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.git.reny.wallpaper.utils.SwipeBackUtils;
import com.jaeger.library.StatusBarUtil;
import com.jude.swipbackhelper.SwipeBackHelper;
import com.renygit.multistateview.MultiStateView;
import com.umeng.analytics.MobclickAgent;
import com.zyctd.mvplib.base.RBaseActivity;
import com.zyctd.mvplib.base.RBasePresenter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

/**
 * Created by reny on 2017/11/15.
 */

public abstract class BaseActivity<P extends RBasePresenter> extends RBaseActivity<P> implements IBaseView{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (isEnableSwipeBack()) {
            SwipeBackHelper.onCreate(this);
            SwipeBackUtils.EnableSwipeActivity(this, 0.1f);
        }

        if (isTranslucentStatusBar()) {
            StatusBarUtil.setTranslucentForImageViewInFragment(this, null);
        }

        StatusBarUtil.setLightMode(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        super.onCreate(savedInstanceState);

        if (null != getToolbar()) {
            getToolbar().setTitle("");
            setSupportActionBar(getToolbar());
            //给左上角图标的左边加上一个返回的图标
            if(null != getSupportActionBar()) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            getToolbar().setNavigationOnClickListener(v -> onBackPressed());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!hasFragment()) {//如果没有Fragment 需要统计页面   如果有Fragment onPageStart写在Fragment中
            //LogUtils.e("pageName:" + TAG);
            MobclickAgent.onPageStart(TAG); //统计页面
        }
        MobclickAgent.onResume(this);//统计时长 只在Activity中写
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!hasFragment()) {//如果没有Fragment 需要统计页面   如果有Fragment onPageEnd写在Fragment中
            MobclickAgent.onPageEnd(TAG); //统计页面
        }
        MobclickAgent.onPause(this);//统计时长 只在Activity中写
    }

    @Override
    protected MultiStateView getMultiStateView() {
        return null;
    }

    @Override
    protected RefreshLayout getRefreshLayout() {
        return null;
    }

    protected Toolbar getToolbar(){
        return null;
    }

    //是否启动滑动退出
    protected boolean isEnableSwipeBack() {
        return true;
    }

    //是否设置状态栏为透明
    protected boolean isTranslucentStatusBar() {
        return false;
    }

    //用于统计 判断是否包含Fragment 只有Activity需要调用MobclickAgent.onResume、onPause
    protected boolean hasFragment() {
        return false;
    }

}
