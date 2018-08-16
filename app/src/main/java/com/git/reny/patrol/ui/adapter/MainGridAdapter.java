package com.git.reny.patrol.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.git.reny.patrol.R;
import com.git.reny.patrol.entity.other.MainGridBean;
import com.git.reny.patrol.utils.ResUtils;

import java.util.List;

/**
 * Created by reny on 2018/8/13.
 */

public class MainGridAdapter extends BaseQuickAdapter<MainGridBean, BaseViewHolder> {

    private int[] bgColors = {
            R.drawable.item_grid_1,
            R.drawable.item_grid_2,
            R.drawable.item_grid_3,
            R.drawable.item_grid_4,
            R.drawable.item_grid_5,
            R.drawable.item_grid_6,
            R.drawable.item_grid_7,
            R.drawable.item_grid_8,
            R.drawable.item_grid_9
    };

    public MainGridAdapter(@Nullable List<MainGridBean> data) {
        super(R.layout.item_main_grid, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainGridBean item) {
        helper.setBackgroundRes(R.id.rl_bg, bgColors[helper.getAdapterPosition()]);
        helper.setImageResource(R.id.iv_img, item.getMipmapId());
        helper.setText(R.id.tv_title, item.getTitle());
    }
}
