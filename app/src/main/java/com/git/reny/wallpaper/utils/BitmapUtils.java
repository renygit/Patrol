package com.git.reny.wallpaper.utils;

import com.git.reny.wallpaper.api.APIConfig;

/**
 * Created by admin on 2017/7/5.
 */

public class BitmapUtils {

    public static String getImgUrl(String fileName){
        return APIConfig.QINIU_HOST_IMG + fileName;
    }

    /*public static String bitmapToBase64(Bitmap bitmap) {
        // 将Bitmap转换成Base64字符串
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bStream);
        int options = 100;
        while ( bStream.toByteArray().length / 1024>100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            bStream.reset();//重置baos即清空baos
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, bStream);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        byte[] bytes = bStream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    public static Bitmap base64ToBitmap(String imgString) {
        if(TextUtils.isEmpty(imgString)) return null;
        // 将base64格式字符串还原成byte数组
        byte[] data = Base64.decode(imgString.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }*/

}
