package com.zyctd.mvplib.utils;

import android.support.annotation.StringRes;
import android.widget.Toast;


public class ToastUtils {

    //private static Toast toast = null;

    private ToastUtils() {
    }

    public static void showShort(@StringRes int resId) {
        Toasty.normal(AppUtils.self().getContext(), ResUtils.getString(resId), Toast.LENGTH_SHORT).show();
    }


    public static void showShort(String message) {
        Toasty.normal(AppUtils.self().getContext(), message, Toast.LENGTH_SHORT).show();
    }


    public static void showLong(@StringRes int resId) {
        Toasty.normal(AppUtils.self().getContext(), ResUtils.getString(resId), Toast.LENGTH_LONG).show();
    }


    public static void showLong(String message) {
        Toasty.normal(AppUtils.self().getContext(), message, Toast.LENGTH_LONG).show();
    }
}
