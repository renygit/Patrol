package com.git.reny.wallpaper.ui.activity;

import android.os.Bundle;

import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.core.BaseActivity;
import com.git.reny.wallpaper.presenter.LoginPresenter;
import com.git.reny.wallpaper.ui.mvp.LoginView;

/**
 * 登录页
 **/

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected boolean isEnableSwipeBack() {
        return false;
    }

    @Override
    protected LoginPresenter obtainPresenter() {
        return new LoginPresenter(this);
    }


    @Override
    protected void init(Bundle bundle) {

    }

}
