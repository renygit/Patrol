package com.git.reny.wallpaper.ui.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.entity.other.ImgsInfo;
import com.git.reny.wallpaper.entity.response.HomeRecommendDetails;
import com.git.reny.wallpaper.ui.activity.ImagesActivity;
import com.git.reny.wallpaper.utils.glide.GlideHelper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StepListAdapter extends BaseQuickAdapter<HomeRecommendDetails.StepListBean, BaseViewHolder> {

    public StepListAdapter(@Nullable List<HomeRecommendDetails.StepListBean> data) {
        super(R.layout.item_step, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeRecommendDetails.StepListBean item) {
        helper.setText(R.id.tv_title, item.getDetails());
        helper.setText(R.id.tv_step, "第"+item.getOrdernum()+"步");
        helper.setVisible(R.id.iv_img, !TextUtils.isEmpty(item.getImageid()));
        GlideHelper.display(helper.getView(R.id.iv_img), item.getImageid());
        helper.getView(R.id.iv_img).setOnClickListener(v -> {
            Intent intent = new Intent(mContext, ImagesActivity.class);
            intent.putExtra(ImgsInfo.KEY, new ImgsInfo(Collections.singletonList(item.getImageid()), 0));
            mContext.startActivity(intent);
        });
    }
}
