package com.git.reny.wallpaper.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.entity.response.ImgListData;
import com.git.reny.wallpaper.utils.glide.GlideHelper;

import java.util.List;

public class HomeListAdapter extends BaseQuickAdapter<ImgListData.ListDataBean, BaseViewHolder> {

    public HomeListAdapter(@Nullable List<ImgListData.ListDataBean> data) {
        super(R.layout.item_home, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ImgListData.ListDataBean item) {
        GlideHelper.display((ImageView)helper.getView(R.id.iv_img), item.getImgUrl());
    }
}
