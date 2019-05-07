package com.zyctd.mvplib.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * Created by reny on 2017/11/15.
 */

public class AppUtils {

    private Application mApplication;
    private KProgressHUD progressHUD;

    private AppUtils(){
    }

    private static class SingletonHolder {
        private static final AppUtils INSTANCE = new AppUtils();
    }

    public static AppUtils self() {
        return SingletonHolder.INSTANCE;
    }


    public void setApplication(Application mApplication) {
        this.mApplication = mApplication;
    }


    public Context getContext(){
        return mApplication;
    }


    public boolean isConnected() {
        NetworkInfo net = ((ConnectivityManager)(getContext().getSystemService(Context.CONNECTIVITY_SERVICE))).getActiveNetworkInfo();
        return net != null && net.isConnected();
    }


    public void showSnackbar(Activity activity, String message) {
        if (null == activity) {
            LogUtils.e("mActivity == null");
            return;
        }
        View view = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }

    public void showSnackbarLong(Activity activity, String message) {
        if (null == activity) {
            LogUtils.e("mActivity == null");
            return;
        }
        View view = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    public void showLoading(Activity activity, boolean isCancel, String... tips) {
        if(null != activity) {
            if (null == progressHUD) {
                progressHUD = KProgressHUD.create(activity)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f);
            }
            progressHUD.setCancellable(isCancel);
            if (tips.length > 0) {
                progressHUD.setLabel(tips[0]);
                if (tips.length > 1) {
                    progressHUD.setDetailsLabel(tips[1]);
                }
            }
            progressHUD.show();
        }
    }

    public void hideLoading() {
        if(null != progressHUD){
            if(progressHUD.isShowing()){
                progressHUD.dismiss();
            }
        }
    }

    public void release(){
        this.progressHUD = null;
    }

}
