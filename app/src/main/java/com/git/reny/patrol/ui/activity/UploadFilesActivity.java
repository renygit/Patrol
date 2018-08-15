package com.git.reny.patrol.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.git.reny.patrol.R;
import com.git.reny.patrol.core.BaseActivity;
import com.zyctd.mvplib.base.RBasePresenter;

import butterknife.BindView;

/**
 * Created by reny on 2018/8/15.
 */

public class UploadFilesActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_upload;
    }

    @Override
    protected Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    protected RBasePresenter obtainPresenter() {
        return null;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvTitle.setText("隐患上报");
    }
}
