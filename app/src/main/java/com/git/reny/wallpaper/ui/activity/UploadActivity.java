package com.git.reny.wallpaper.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.flyco.roundview.RoundTextView;
import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.core.BaseActivity;
import com.git.reny.wallpaper.entity.other.MultImgBean;
import com.git.reny.wallpaper.presenter.UploadPresenter;
import com.git.reny.wallpaper.ui.adapter.ImgSelectAdapter;
import com.git.reny.wallpaper.ui.mvp.UploadView;
import com.git.reny.wallpaper.utils.CommonUtils;
import com.git.reny.wallpaper.utils.PicUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.zyctd.mvplib.utils.LogUtils;
import com.zyctd.mvplib.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;

public class UploadActivity extends BaseActivity<UploadPresenter> implements UploadView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title_tip)
    TextView tvTitleTip;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.tv_content_tip)
    TextView tvContentTip;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.rv_imgs)
    RecyclerView rvImgs;
    @BindView(R.id.tv_publish)
    RoundTextView tvPublish;

    @BindInt(R.integer.publish_title_len)
    int title_len;
    @BindInt(R.integer.publish_content_len)
    int content_len;
    @BindInt(R.integer.publish_img_max_count)
    int img_max_count;

    private ImgSelectAdapter imgSelectAdapter;
    private List<MultImgBean> imgList;
    private List<LocalMedia> imgSelectList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_upload;
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    protected UploadPresenter obtainPresenter() {
        return new UploadPresenter(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvTitleTip.setText(String.format("0/%s", title_len));
        etTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                tvTitleTip.setText(String.format("%s/%s", s.length(), title_len));
            }
        });

        tvContentTip.setText(String.format("0/%s", content_len));
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                tvContentTip.setText(String.format("%s/%s", s.length(), content_len));
            }
        });

        rvImgs.setHasFixedSize(true);
        rvImgs.setNestedScrollingEnabled(false);
        rvImgs.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        imgList = new ArrayList<>(1);
        imgList.add(new MultImgBean(null));
        imgSelectAdapter = new ImgSelectAdapter(imgList, img_max_count);
        rvImgs.setAdapter(imgSelectAdapter);
        imgSelectAdapter.setCallBack(new ImgSelectAdapter.ImgManageCallBack() {
            @Override
            public void deleteImg(int pos) {
                //ToastUtils.showLong("imgList.size() - 1:"+(imgList.size() - 1)+"   pos:"+pos);
                if(imgList.size() > pos){
                    imgList.remove(pos);
                    if(imgList.size() < img_max_count && null != imgList.get(imgList.size() - 1).getImg()) {
                        imgList.add(new MultImgBean(null));//添加图片项
                    }
                    imgSelectAdapter.notifyItemRemoved(pos);
                    imgSelectAdapter.notifyItemRangeChanged(pos, imgList.size() - pos);
                    resetImgSelectList();
                }
            }
            @Override
            public void clickAddImgs() {
                if(null == imgSelectList){
                    imgSelectList = new ArrayList<>();
                }
                PicUtils.selectMultiImg(getActivity(), 3, 2, img_max_count, imgSelectList);
            }
        });

        tvPublish.setOnClickListener(v -> {
            String title = etTitle.getText().toString().trim();
            String content = etContent.getText().toString().trim();
            if(TextUtils.isEmpty(title)){
                ToastUtils.showLong("标题不能为空哦");
                return;
            }
            if(TextUtils.isEmpty(content)){
                ToastUtils.showLong("短文不能为空哦");
                return;
            }
            if(CommonUtils.isEmpty(imgSelectList)){
                ToastUtils.showLong("图片至少上传一张吧");
                return;
            }
            presenter.publish(title, content, imgSelectList);
        });
    }

    private void resetImgSelectList(){
        if(null == imgSelectList) {
            imgSelectList = new ArrayList<>();
        }
        imgSelectList.clear();
        for(MultImgBean img : imgList){
            if(null != img.getImg() && img.getImg() instanceof LocalMedia){
                imgSelectList.add((LocalMedia)img.getImg());
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(null == data) return;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    imgList.clear();
                    if(null == imgSelectList){
                        imgSelectList = new ArrayList<>();
                    }
                    imgSelectList.clear();
                    imgSelectList.addAll(PictureSelector.obtainMultipleResult(data));
                    for(LocalMedia img : imgSelectList){
                        imgList.add(new MultImgBean(img));
                    }
                    if(imgList.size() < img_max_count) {
                        imgList.add(new MultImgBean(null));//添加图片项
                    }
                    imgSelectAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    @Override
    public void onDestroy() {
        //上传完成就应该执行   清除裁剪、压缩的缓存文件
        PicUtils.clearCache(getActivity());
        super.onDestroy();
    }

}
