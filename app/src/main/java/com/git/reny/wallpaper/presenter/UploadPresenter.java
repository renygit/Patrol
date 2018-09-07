package com.git.reny.wallpaper.presenter;

import com.git.reny.wallpaper.core.BasePresenter;
import com.git.reny.wallpaper.ui.mvp.UploadView;

/**
 * 登录
 */
public class UploadPresenter extends BasePresenter<UploadView> {
    public UploadPresenter(UploadView view) {
        super(view);
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
