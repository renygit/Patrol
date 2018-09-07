package com.git.reny.wallpaper.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.core.BaseActivity;
import com.git.reny.wallpaper.presenter.UploadPresenter;
import com.git.reny.wallpaper.ui.mvp.UploadView;
import com.git.reny.wallpaper.utils.CommonUtils;
import com.xw.repo.XEditText;

import butterknife.BindView;

/**
 * Created by reny on 2018/8/15.
 */

public class SearchActivity extends BaseActivity<UploadPresenter> implements UploadView {

    @BindView(R.id.et_key_word)
    XEditText etKeyWord;
    @BindView(R.id.tv_search)
    ImageView tvSearch;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    protected UploadPresenter obtainPresenter() {
        return new UploadPresenter(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    public void onBackPressed() {
        CommonUtils.hideSoftInput(etKeyWord);
        super.onBackPressed();
    }
}
