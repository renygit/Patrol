package com.git.reny.patrol.presenter;

import com.git.reny.patrol.core.BasePresenter;
import com.git.reny.patrol.ui.mvp.LoginView;
import com.zyctd.mvplib.utils.AppUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 登录
 */
public class LoginPresenter extends BasePresenter<LoginView> {
    public LoginPresenter(LoginView view) {
        super(view);
    }

    public void login(String LoginName, String Password, String ValidateCode) {
        /*addDisposable(ServiceHelper.getApi().login(getRequest("Login")
                        .setCommand("AndroidUserSignService/Login")
                        .set("LoginName", LoginName)
                        .set("Password", Password)
                        .set("ValidateCode", ValidateCode)
                        .build())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableCall<LoginData>(this) {
                            @Override
                            public boolean isRefresh() {
                                return true;
                            }

                            @Override
                            public boolean isEmpty(LoginData value) {
                                return false;
                            }

                            @Override
                            public void onSuc(LoginData data) {
                                getView().loginSuc(data);

                            }

                            @Override
                            public void onErr(Throwable e) {
                                if (e instanceof ResultException) {
                                    ResultException resultException = ((ResultException) e);
                                    if (resultException.getCode() == 103) {
                                        GetLoginValidateCode();
                                        AppUtils.self().showToast("验证码错误");
                                    } else if (resultException.getCode() == 11) {
                                        AppUtils.self().showToast("登录失败，请稍后重试");
                                    } else {
                                        AppUtils.self().showToast(resultException.getMessage());
                                    }
                                } else {
                                    AppUtils.self().showToast("登录失败，请稍后重试");
                                }
//                        LogUtils.e(TAG + ":" + e.getMessage());
                            }
                        })
        );*/
    }

    /**
     * 获取登录图片验证码
     */
    public void GetLoginValidateCode() {

        /*addDisposable(ServiceHelper.getApi().GetLoginValidateCode(getRequest("GetLoginValidateCode")
                        .setCommand("AndroidUserSignService/GetLoginValidateCode")
                        .build()).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableCall<LoginCodeData>(this) {
                            @Override
                            public boolean isRefresh() {
                                return false;
                            }

                            @Override
                            public boolean isEmpty(LoginCodeData value) {
                                return false;
                            }

                            @Override
                            public void onSuc(LoginCodeData data) {
                                getView().validateCodeSuc(data.getValidateCodeImage());

                            }

                            @Override
                            public void onErr(Throwable e) {
                                AppUtils.self().showToast(e.getMessage());
//                        LogUtils.e(TAG + ":" + e.getMessage());
                            }
                        })
        );*/

    }
}
