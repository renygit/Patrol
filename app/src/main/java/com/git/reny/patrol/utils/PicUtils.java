package com.git.reny.patrol.utils;

import android.app.Activity;
import android.content.Intent;

import com.git.reny.patrol.R;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

/**
 * Created by reny on 2018/7/26.
 */

public class PicUtils {

    private static final int cropWidth  = 800;
    private static final int cropHeight = 800;

    //选择图片 详情： https://github.com/LuckSiege/PictureSelector
    private static void selectImg(Activity activity, boolean enableCrop, boolean isCircle, int maxCount, int ratio_x, int ratio_y, int cropWidth, int cropHeight,  int requestCode, List<LocalMedia> listMedia){
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(maxCount > 1 ? R.style.picture_multi_style : R.style.picture_single_style)//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                .maxSelectNum(maxCount)// 最大图片选择数量 int
                .imageSpanCount(3)// 每行显示个数 int
                .selectionMode(maxCount > 1 ? PictureConfig.MULTIPLE : PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .sizeMultiplier(0.9f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .setOutputCameraPath("/Picture")// 自定义拍照保存路径,可不填
                .enableCrop(enableCrop)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                .withAspectRatio(ratio_x, ratio_y)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                //.freeStyleCropEnabled(ratio_x != ratio_y)
                .circleDimmedLayer(isCircle)// 是否圆形裁剪 true or false
                .showCropFrame(!isCircle)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .selectionMedia(listMedia)
                .cropWH(cropWidth, cropHeight)// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
                .isDragFrame(false)// 是否可拖动裁剪框(固定) 可改变比例
                .forResult(requestCode);//结果回调onActivityResult code
    }

    //单选： 圆形裁剪 选头像
    public static void selectHeaderImg(Activity activity, int CODE_REQUEST){
        selectImg(activity, true, true, 1, 1, 1, 400, 400, CODE_REQUEST, null);
    }

    //单选： 正方形裁剪
    public static void selectSingleImg(Activity activity){
        selectImg(activity, true, false, 1, 1, 1, cropWidth, cropHeight, PictureConfig.CHOOSE_REQUEST, null);
    }

    //单选： 正方形裁剪
    public static void selectSingleImg(Activity activity, int requestCode){
        selectImg(activity, true, false, 1, 1, 1, cropWidth, cropHeight, requestCode, null);
    }

    //单选： 正方形裁剪
    public static void selectSingleImg(Activity activity, boolean enableCrop){
        selectImg(activity, enableCrop, false, 1, 1, 1, cropWidth, cropHeight, PictureConfig.CHOOSE_REQUEST, null);
    }

    //单选： 矩形裁剪
    public static void selectSingleImg(Activity activity, int ratio_x, int ratio_y){
        int mCropWidth = cropWidth;
        int mCropHeight = cropHeight;

        if(ratio_x != 0 && ratio_y != 0) {
            if (ratio_x > ratio_y) {
                //ratio_x : ratio_y = ? : cropHeight
                mCropWidth = (int) (cropHeight * (((float) ratio_x) / ratio_y));
            } else {
                //ratio_x : ratio_y = cropWidth : ?
                mCropHeight = (int) (cropWidth * (((float) ratio_y) / ratio_x));
            }
        }

        selectImg(activity, true, false, 1, ratio_x, ratio_y, mCropWidth, mCropHeight, PictureConfig.CHOOSE_REQUEST, null);
    }


    //多选： 不可裁剪
    public static void selectMultiImg(Activity activity, int maxCount, List<LocalMedia> list){
        selectImg(activity, false, false, maxCount, 1, 1, cropWidth, cropHeight, PictureConfig.CHOOSE_REQUEST, list);
    }

    //多选： 可裁剪
    public static void selectMultiImg(Activity activity, int ratio_x, int ratio_y, int maxCount, List<LocalMedia> list){
        selectImg(activity, true, false, maxCount, ratio_x, ratio_y, cropWidth, cropHeight, PictureConfig.CHOOSE_REQUEST, list);
    }

    //预览
    public static void preLook(Activity activity, List<LocalMedia> selectList, int position, int maxCount){
        PictureSelector.create(activity).themeStyle(maxCount > 1 ? R.style.picture_multi_style : R.style.picture_single_style).openExternalPreview(position, selectList);
    }


    public static String getPath(Intent data){
        List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);

        if(null != selectList && selectList.size() > 0) {
            LocalMedia media = selectList.get(0);
            return getPath(media);
        }
        return "";
    }

    public static String getPath(LocalMedia media){
        if(null != media) {
            String path = media.getPath();
            if(media.isCompressed()){
                path = media.getCompressPath();
            }else if(media.isCut()){
                path = media.getCutPath();
            }
            return path;
        }
        return "";
    }

}
