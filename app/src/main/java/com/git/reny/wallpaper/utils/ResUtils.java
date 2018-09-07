package com.git.reny.wallpaper.utils;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;

import com.zyctd.mvplib.utils.AppUtils;

/**
 * Created by reny on 2017/5/8.
 * 用到了再添加其它方法
 */

public class ResUtils {

    public static int getColor(@ColorRes int colorId){
        return ContextCompat.getColor(AppUtils.self().getContext(), colorId);
    }

    public static String getString(@StringRes int strId){
        return AppUtils.self().getContext().getString(strId);
    }

    public static int getInteger(@IntegerRes int integerId){
        return AppUtils.self().getContext().getResources().getInteger(integerId);
    }

    public static String[] getArrStr(int arrId){
        return AppUtils.self().getContext().getResources().getStringArray(arrId);
    }

    public static int[] getArrInt(int arrId){
        return AppUtils.self().getContext().getResources().getIntArray(arrId);
    }

    public static Drawable getDrawable(@DrawableRes int imgId){
        return ContextCompat.getDrawable(AppUtils.self().getContext(), imgId);
    }

    public static float getDimen(@DimenRes int dimenId){
        return AppUtils.self().getContext().getResources().getDimension(dimenId);
    }

    public static int getDimenPx(@DimenRes int dimenId){
        return AppUtils.self().getContext().getResources().getDimensionPixelSize(dimenId);
    }
}
