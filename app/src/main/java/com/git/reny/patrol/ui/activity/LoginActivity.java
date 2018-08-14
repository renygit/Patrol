package com.git.reny.patrol.ui.activity;

import android.os.Bundle;

import com.git.reny.patrol.R;
import com.git.reny.patrol.core.BaseActivity;
import com.git.reny.patrol.presenter.LoginPresenter;
import com.git.reny.patrol.ui.mvp.LoginView;

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
