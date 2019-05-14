package com.git.reny.wallpaper.ui.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.entity.response.HomeRecommendList;
import com.git.reny.wallpaper.ui.activity.DetailsActivity;
import com.git.reny.wallpaper.utils.glide.GlideHelper;

import java.util.List;

public class HomeRecommendListAdapter extends BaseQuickAdapter<HomeRecommendList.ListDataBean, BaseViewHolder> {

    public HomeRecommendListAdapter(@Nullable List<HomeRecommendList.ListDataBean> data) {
        super(R.layout.item_recommend, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeRecommendList.ListDataBean item) {
        helper.setText(R.id.tv_title, item.getName());
        GlideHelper.display(helper.getView(R.id.iv_head), item.getAuthorimageid());
        helper.setText(R.id.tv_subTitle, item.getAuthorname() + "   " + item.getLikeCount() + "人喜欢" + "   " + item.getCollectCount() + "人收藏");
        GlideHelper.disPlayRound(helper.getView(R.id.iv_img), item.getImageid(), 20, true);
        helper.setVisible(R.id.iv_isVideo, item.isHasVideo());
        helper.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, DetailsActivity.class);
            intent.putExtra("ids", item.getId());
            mContext.startActivity(intent);
        });
    }
}
