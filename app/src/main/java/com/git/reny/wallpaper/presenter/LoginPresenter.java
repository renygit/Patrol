package com.git.reny.wallpaper.presenter;

import com.git.reny.wallpaper.core.BasePresenter;
import com.git.reny.wallpaper.core.DisCall;
import com.git.reny.wallpaper.core.ServiceHelper;
import com.git.reny.wallpaper.entity.response.UserData;
import com.git.reny.wallpaper.ui.mvp.LoginView;
import com.zyctd.mvplib.utils.ToastUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 登录
 */
public class LoginPresenter extends BasePresenter<LoginView> {
    public LoginPresenter(LoginView view) {
        super(view);
    }

    public void login(String name, String pwd) {
        addDisposable(ServiceHelper.getApi().login(name, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisCall<UserData>() {
                    @Override
                    public void onSuc(UserData data) {
                        UserData.saveData(data);
                        ToastUtils.showLong("登录成功");
                        finish();
                    }

                    @Override
                    public void onErr(Throwable e) {
                        ToastUtils.showLong(e.getMessage());
                    }
                })
        );
    }

    public void register(String name, String pwd) {
        addDisposable(ServiceHelper.getApi().register(name, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisCall<UserData>() {
                    @Override
                    public void onSuc(UserData data) {
                        UserData.saveData(data);
                        ToastUtils.showLong("注册成功");
                        finish();
                    }

                    @Override
                    public void onErr(Throwable e) {
                        ToastUtils.showLong(e.getMessage());
                    }
                })
        );
    }

}
