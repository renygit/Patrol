package com.git.reny.patrol.presenter;

import com.git.reny.patrol.core.BasePresenter;
import com.git.reny.patrol.core.DisposableCall;
import com.git.reny.patrol.core.ServiceHelper;
import com.git.reny.patrol.entity.response.Test;
import com.git.reny.patrol.ui.mvp.LoginView;
import com.git.reny.patrol.ui.mvp.UploadView;
import com.zyctd.mvplib.utils.AppUtils;
import com.zyctd.mvplib.utils.LogUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 登录
 */
public class UploadPresenter extends BasePresenter<UploadView> {
    public UploadPresenter(UploadView view) {
        super(view);
    }

    public void test() {
        AppUtils.self().showLoading(getActivity(), false);
        addDisposable(ServiceHelper.getApi().getBooks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCall<Test>(this) {
                    @Override
                    public boolean isRefresh() {
                        return false;
                    }

                    @Override
                    public boolean isEmpty(Test value) {
                        return false;
                    }

                    @Override
                    public void onSuc(Test data) {
                        AppUtils.self().showToast(data.getListData().get(0).getDescription());
                    }

                    @Override
                    public void onErr(Throwable e) {
                        AppUtils.self().showToast(e.getMessage());
                        LogUtils.e(TAG + ":" + e.getMessage());
                    }
                })
        );
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
