package com.git.reny.wallpaper.utils;

import android.annotation.SuppressLint;

import com.git.reny.wallpaper.entity.other.QiniuImgs;
import com.qiniu.android.storage.UploadManager;
import com.zyctd.mvplib.utils.LogUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class QiNiuUtils {
    /**
     * 七牛云SDK
     */
    private static UploadManager uploadManager = new UploadManager();

    /**
     * 回调接口
     */
    public interface QiNiuCallback {
        /**
         * 上传完成
         */
        void onSuccess(List<String> picUrls);
        /**
         * 上传失败
         */
        void onError(String msg);
    }

    /**
     * 上传图片到七牛
     *
     * @param qiniuImgs     图片相关
     * @param qiNiuCallback 回调接口
     */
    @SuppressLint("CheckResult")
    public static void putImgs(List<QiniuImgs> qiniuImgs, QiNiuCallback qiNiuCallback) {
        // 七牛返回的文件名
        ArrayList<String> resultImagePath = new ArrayList<>();
        Observable
                // 依次发送list中的数据
                .fromIterable(qiniuImgs)
                // 变换，在这里上传图片
                .flatMap((Function<QiniuImgs, ObservableSource<String>>)
                        qiniuImg -> Observable.create(emitter -> {
                            LogUtils.e("path:"+qiniuImg.getImgPath());
                            //String key = UUID.randomUUID().toString() + "." + path.split("//.")[1];
                            uploadManager.put(qiniuImg.getImgPath(), qiniuImg.getKey(), qiniuImg.getToken(),
                                    (key1, info, res) -> {
                                        //res包含hash、key等信息，具体字段取决于上传策略的设置
                                        if (info.isOK()) {
                                            // 上传成功，发送这张图片的文件名
                                            LogUtils.e("key1:"+key1);
                                            emitter.onNext(key1);
                                        } else {
                                            // 上传失败
                                            LogUtils.e("info.error:"+info.error);
                                            emitter.onError(new IOException(info.error));
                                        }
                                    }, null);
                        })
                )
                // 线程切换
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    resultImagePath.add(response);
                    // 如果全部完成，调用成功接口
                    if (resultImagePath.size() == qiniuImgs.size()) {
                        qiNiuCallback.onSuccess(resultImagePath);
                    }
                }, throwable -> {
                    LogUtils.e(throwable.getMessage());
                    qiNiuCallback.onError(throwable.getMessage());
                });

    }

}
