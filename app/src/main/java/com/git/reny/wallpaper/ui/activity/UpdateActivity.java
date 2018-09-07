package com.git.reny.wallpaper.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.flyco.roundview.RoundTextView;
import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.core.BaseActivity;
import com.git.reny.wallpaper.entity.event.UpdateProgress;
import com.git.reny.wallpaper.service.UpdateService;
import com.github.lzyzsd.circleprogress.ArcProgress;
import com.zyctd.mvplib.base.RBasePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import butterknife.BindView;

/**
 * Created by reny on 2018/2/7.
 * 只有强制更新才显示的更新界面
 * 该页面不能进行任何操作，只有等待更新完成 重新安装
 */

public class UpdateActivity extends BaseActivity {

    @BindView(R.id.arc_progress)
    ArcProgress arcProgress;
    @BindView(R.id.tv_install)
    RoundTextView tvInstall;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(UpdateProgress event) {
        arcProgress.setProgress((int) ((float) event.getSoFarBytes() / event.getTotalBytes() * 100));
        if (arcProgress.getProgress() == 100) {
            arcProgress.setVisibility(View.GONE);
            tvInstall.setVisibility(View.VISIBLE);

            tvInstall.setOnClickListener(view -> {
                UpdateService.installApk(new File(event.getDownLoadPath()));
            });
        }
    }

    @Override
    public boolean useEventBus() {
        return true;
    }

    @Override
    protected boolean isEnableSwipeBack() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update;
    }

    @Override
    protected RBasePresenter obtainPresenter() {
        return null;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    public void onBackPressed() {
        //让返回键无效
    }
}
