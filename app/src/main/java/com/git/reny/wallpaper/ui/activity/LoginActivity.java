package com.git.reny.wallpaper.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.core.BaseActivity;
import com.git.reny.wallpaper.presenter.LoginPresenter;
import com.git.reny.wallpaper.ui.mvp.LoginView;

import java.util.Objects;

import butterknife.BindView;
import shem.com.materiallogin.DefaultLoginView;
import shem.com.materiallogin.DefaultRegisterView;
import shem.com.materiallogin.MaterialLoginView;

/**
 * 登录页
 **/

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.login)
    MaterialLoginView login;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    protected LoginPresenter obtainPresenter() {
        return new LoginPresenter(this);
    }


    @Override
    protected void init(Bundle bundle) {
        ((DefaultLoginView) login.getLoginView()).setListener((loginUser, loginPass) -> {
            String name = Objects.requireNonNull(loginUser.getEditText()).getText().toString();
            if (TextUtils.isEmpty(name)) {
                loginUser.setError("用户名不能为空");
                return;
            }
            loginUser.setError("");

            String pwd = Objects.requireNonNull(loginPass.getEditText()).getText().toString();
            if (TextUtils.isEmpty(pwd)) {
                loginPass.setError("密码不能为空");
                return;
            }
            loginPass.setError("");

            presenter.login(name, pwd);
        });

        ((DefaultRegisterView) login.getRegisterView()).setListener((registerUser, registerPass, registerPassRep) -> {
            String name = Objects.requireNonNull(registerUser.getEditText()).getText().toString();
            if (TextUtils.isEmpty(name)) {
                registerUser.setError("用户名不能为空");
                return;
            }
            registerUser.setError("");

            String pwd = Objects.requireNonNull(registerPass.getEditText()).getText().toString();
            if (TextUtils.isEmpty(pwd)) {
                registerPass.setError("密码不能为空");
                return;
            }
            registerPass.setError("");

            String pwdRep = Objects.requireNonNull(registerPassRep.getEditText()).getText().toString();
            if (!pwd.equals(pwdRep)) {
                registerPassRep.setError("两次输入密码不一致");
                return;
            }
            registerPassRep.setError("");

            presenter.register(name, pwd);
        });
    }

}
