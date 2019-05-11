package com.git.reny.wallpaper.ui.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.entity.response.CookBean;
import com.git.reny.wallpaper.entity.response.IdBean;
import com.git.reny.wallpaper.ui.activity.CookActivity;
import com.git.reny.wallpaper.utils.BitmapUtils;
import com.git.reny.wallpaper.utils.FuzzyDateTimeFormatter;
import com.git.reny.wallpaper.utils.glide.GlideHelper;

import java.util.List;

public class CookListAdapter extends BaseQuickAdapter<CookBean, BaseViewHolder> {

    public CookListAdapter(@Nullable List<CookBean> data) {
        super(R.layout.item_cook, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CookBean item) {
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_subTitle, FuzzyDateTimeFormatter.getTimeAgo(mContext, item.getDate().get$date()));
        helper.setVisible(R.id.iv_img, item.getImgs().size() > 0);
        if (item.getImgs().size() > 0) {
            helper.getView(R.id.iv_img).setTag(item.getImgs().get(0));
            GlideHelper.disPlayRound(helper.getView(R.id.iv_img), BitmapUtils.base64ToBitmap(item.getImgs().get(0)), 20, true);
        }
        helper.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, CookActivity.class);
            intent.putExtra(CookBean.class.getSimpleName(), item);
            intent.putExtra(IdBean.class.getSimpleName(), item.get_id());
            mContext.startActivity(intent);
        });
    }
}
