package com.git.reny.wallpaper.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.entity.response.HomeRecommendDetails;

import java.util.List;

public class MaterialListAdapter extends BaseQuickAdapter<HomeRecommendDetails.MaterialListBean, BaseViewHolder> {

    public MaterialListAdapter(@Nullable List<HomeRecommendDetails.MaterialListBean> data) {
        super(R.layout.item_material, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeRecommendDetails.MaterialListBean item) {
        helper.setText(R.id.tv_material, item.getName());
        helper.setText(R.id.tv_num, item.getDosage());
    }
}
