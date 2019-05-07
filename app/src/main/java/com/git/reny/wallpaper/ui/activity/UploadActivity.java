package com.git.reny.wallpaper.ui.activity;

import android.os.Bundle;

import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.core.BaseActivity;
import com.zyctd.mvplib.base.RBasePresenter;

public class UploadActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_upload;
    }

    @Override
    protected RBasePresenter obtainPresenter() {
        return null;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

}
