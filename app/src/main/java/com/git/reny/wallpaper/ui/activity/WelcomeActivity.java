package com.git.reny.wallpaper.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.core.BaseActivity;
import com.git.reny.wallpaper.core.BasePresenter;
import com.git.reny.wallpaper.utils.Constans;
import com.zyctd.mvplib.base.RBasePresenter;
import com.zyctd.mvplib.utils.AppUtils;
import com.zyctd.mvplib.utils.LogUtils;
import com.zyctd.mvplib.utils.ToastUtils;

import butterknife.BindView;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by reny on 2018/2/3.
 */

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.iv)
    ImageView iv;
    /*@BindView(R.id.tv_leftTime)
    RoundTextView tvLeftTime;*/

    private int jumpTime = 3;//闪屏时间 秒

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected boolean isEnableSwipeBack() {
        return false;
    }

    @Override
    protected RBasePresenter obtainPresenter() {
        return new BasePresenter(this) {};
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        openApp();
    }


    private void openApp() {
        presenter.checkPermissions(new DisposableObserver<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    start();
                } else {
                    ToastUtils.showLong("缺少APP启动需要的基本权限");
                    finish();
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e("权限获取出错：" + e.getMessage());
                start();
            }

            @Override
            public void onComplete() {
            }
        }, Constans.StoragePermissions);

    }

    private void start() {
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        startActivity(new Intent(getActivity(), MainActivity.class));
        finish();
        /*tvLeftTime.setVisibility(View.VISIBLE);
        tvLeftTime.setText("跳过" + jumpTime + "s");
        CommonUtils.delayRun(presenter, jumpTime, 1, () -> {
            --jumpTime;
            tvLeftTime.setText("跳过" + jumpTime + "s");
            if(jumpTime == 0){
                onClick();
            }
        });*/
    }

    /*@OnClick(R.id.tv_leftTime)
    public void onClick() {
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        startActivity(new Intent(getActivity(), MainActivity.class));
        finish();
    }*/
}
