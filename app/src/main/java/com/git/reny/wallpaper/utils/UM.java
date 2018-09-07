package com.git.reny.wallpaper.utils;

import android.content.Context;

import com.git.reny.wallpaper.core.BaseActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;

/**
 * 友盟事件
 */

public class UM {

    public static void c(Context context, String eventId){
        try {
            eventId = String.format("%s_%s", ((BaseActivity) context).TAG, eventId);
        }catch (Exception e){e.printStackTrace();}
        MobclickAgent.onEvent(context, eventId);
    }

    public static void c(Context context, String TAG, String eventId){
        eventId = String.format("%s_%s", TAG, eventId);
        MobclickAgent.onEvent(context, eventId);
    }

    public static void c(Context context, String eventId, HashMap<String, String> map){
        try {
            eventId = String.format("%s_%s", ((BaseActivity) context).TAG, eventId);
        }catch (Exception e){e.printStackTrace();}
        MobclickAgent.onEvent(context, eventId, map);
    }

}
