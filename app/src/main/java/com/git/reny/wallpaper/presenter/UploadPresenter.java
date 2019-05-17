package com.git.reny.wallpaper.presenter;

import com.git.reny.wallpaper.core.BasePresenter;
import com.git.reny.wallpaper.core.DisCall;
import com.git.reny.wallpaper.core.ServiceHelper;
import com.git.reny.wallpaper.entity.other.QiniuImgs;
import com.git.reny.wallpaper.entity.response.QiniuToken;
import com.git.reny.wallpaper.entity.response.UserData;
import com.git.reny.wallpaper.ui.mvp.UploadView;
import com.git.reny.wallpaper.utils.FileUtils;
import com.git.reny.wallpaper.utils.PicUtils;
import com.git.reny.wallpaper.utils.QiNiuUtils;
import com.luck.picture.lib.entity.LocalMedia;
import com.zyctd.mvplib.utils.AppUtils;
import com.zyctd.mvplib.utils.LogUtils;
import com.zyctd.mvplib.utils.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import top.zibin.luban.Luban;

/**
 * 上传
 */
public class UploadPresenter extends BasePresenter<UploadView> {

    public UploadPresenter(UploadView view) {
        super(view);
    }

    public void publish(String title, String content, List<LocalMedia> imgSelectList) {
        List<File> files = new ArrayList<>(imgSelectList.size());
        for (LocalMedia media : imgSelectList) {
            if(null != media) {
                try {
                    files.add(new File(PicUtils.getPath(media)));
                } catch (Exception e) {
                    LogUtils.e(e.getMessage());
                }
            }
        }
        compressImgList(title, content, files);
    }

    private void compressImgList(String title, String content, List<File> imgList){
        AppUtils.self().showLoading(getActivity(), false);
        addDisposable(Flowable.just(imgList)
                .observeOn(Schedulers.io())
                .map(list -> Luban.with(getActivity()).setTargetDir(FileUtils.getDownLoadPath(null)).load(list).get())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> {
                    AppUtils.self().hideLoading();
                    showTipMsg(true, null, "图片压缩出错，是否重新压缩？", "否", "是", (dialog, which) -> {
                        uploadFiles(title, content, imgList);
                    }, (dialog, which) -> {
                        compressImgList(title, content, imgList);
                    });
                })
                .onErrorResumeNext(Flowable.empty())
                .subscribe(list -> {
                    //LogUtils.e("图片压缩成功并上传");
                    uploadFiles(title, content, list);
                }));
    }

    private void uploadFiles(String title, String content, List<File> imgList){
        /*String[] imgBase64Arr = new String[imgList.size()];
        for (int i = 0; i < imgList.size(); i++) {
            imgBase64Arr[i] = BitmapUtils.bitmapToBase64(BitmapFactory.decodeFile(imgList.get(i).getAbsolutePath()));
        }*/
        AppUtils.self().showLoading(getActivity(), false);
        addDisposable(ServiceHelper.getApi().getToken(imgList.size())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisCall<QiniuToken>() {
                    @Override
                    public void onSuc(QiniuToken data) {
                        AppUtils.self().showLoading(getActivity(), false);
                        List<QiniuImgs> qiniuImgs = new ArrayList<>(imgList.size());
                        for (int i = 0; i < imgList.size(); i++) {
                            qiniuImgs.add(new QiniuImgs(imgList.get(i).getAbsolutePath(), data.getKeys().get(i), data.getTokens().get(i)));
                        }

                        QiNiuUtils.putImgs(qiniuImgs, new QiNiuUtils.QiNiuCallback() {
                            @Override
                            public void onSuccess(List<String> picUrls) {
                                //LogUtils.e(GsonSingleton.gson.toJson(picUrls));
                                saveData(title, content, picUrls);
                            }

                            @Override
                            public void onError(String msg) {
                                AppUtils.self().hideLoading();
                                ToastUtils.showLong(msg);
                            }
                        });
                    }

                    @Override
                    public void onErr(Throwable e) {
                        ToastUtils.showLong(e.getMessage());
                    }
                })
        );
    }

    private void saveData(String title, String content, List<String> picUrls){
        addDisposable(ServiceHelper.getApi().upload(UserData.getUserId(), title, content, picUrls)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisCall<Object>() {
                    @Override
                    public void onSuc(Object data) {
                        ToastUtils.showLong("上传成功");
                        finish();
                    }

                    @Override
                    public void onErr(Throwable e) {
                        ToastUtils.showLong(e.getMessage());
                    }
                })
        );
    }

}
