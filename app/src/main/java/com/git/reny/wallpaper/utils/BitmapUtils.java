package com.git.reny.wallpaper.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;

/**
 * Created by admin on 2017/7/5.
 */

public class BitmapUtils {

    public static Bitmap base64ToBitmap(String imgString) {
        if(TextUtils.isEmpty(imgString)) return null;
        // 将base64格式字符串还原成byte数组
        byte[] data = Base64.decode(imgString.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }

}
