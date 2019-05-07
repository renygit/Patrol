package com.git.reny.wallpaper.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.entity.response.HomeList;
import com.git.reny.wallpaper.ui.activity.WebActivity;
import com.git.reny.wallpaper.utils.glide.GlideHelper;

import java.util.List;

public class HomeListAdapter extends BaseQuickAdapter<HomeList.ListDataBean, BaseViewHolder> {

    public HomeListAdapter(@Nullable List<HomeList.ListDataBean> data) {
        super(R.layout.item_home, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeList.ListDataBean item) {
        GlideHelper.display(helper.getView(R.id.iv_img), item.getImgUrl());
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_subTitle, item.getTime() + "   " + item.getWay());
        helper.itemView.setOnClickListener(v -> {
            WebActivity.open(mContext, item.getLinkUrl(), item.getTitle(), null == item.get_id() ? null : item.get_id().get$oid());
        });
    }
}
