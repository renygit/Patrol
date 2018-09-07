package com.git.reny.wallpaper.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.entity.other.CategoryBean;
import com.git.reny.wallpaper.utils.ResUtils;
import com.git.reny.wallpaper.utils.glide.GlideHelper;

import java.util.List;

public class CategoryGridAdapter extends BaseQuickAdapter<CategoryBean, BaseViewHolder> {

    private int radius;

    public CategoryGridAdapter(@Nullable List<CategoryBean> data) {
        super(R.layout.item_grid_category, data);
        radius = ResUtils.getDimenPx(R.dimen.x20);
    }

    @Override
    protected void convert(BaseViewHolder helper, CategoryBean item) {
        GlideHelper.disPlayRound((ImageView)helper.getView(R.id.iv_img), item.getMipmapId(), radius, true);
        helper.setText(R.id.tv_category, item.getTitle());
    }
}
