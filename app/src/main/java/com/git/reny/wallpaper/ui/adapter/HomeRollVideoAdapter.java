package com.git.reny.wallpaper.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.entity.response.HomeRecommend;
import com.git.reny.wallpaper.utils.glide.GlideHelper;

import java.util.List;

public class HomeRollVideoAdapter extends BaseQuickAdapter<HomeRecommend.VideoInfoBean, BaseViewHolder> {

    public HomeRollVideoAdapter(@Nullable List<HomeRecommend.VideoInfoBean> data) {
        super(R.layout.item_roll_video, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeRecommend.VideoInfoBean item) {
        GlideHelper.disPlayRound(helper.getView(R.id.iv_img), item.getVideo().getImg(), 20, true);
        /*helper.itemView.setOnClickListener(v -> {
            JzvdStd.startFullscreen(mContext, JzvdStd.class, item.getVideo().getVendor_video(), item.getTitle());
        });*/
    }
}
