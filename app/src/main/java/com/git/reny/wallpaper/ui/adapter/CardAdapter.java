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
import com.git.reny.wallpaper.utils.CommonUtils;
import com.git.reny.wallpaper.utils.glide.GlideHelper;
import com.zyctd.mvplib.utils.ResUtils;

import java.util.List;

public class CardAdapter extends BaseQuickAdapter<CookBean, BaseViewHolder> {

    private int radius;

    public CardAdapter(@Nullable List<CookBean> data) {
        super(R.layout.item_card, data);
        radius = ResUtils.getDimenPx(R.dimen.x20);
    }

    @Override
    protected void convert(BaseViewHolder helper, CookBean item) {
        if(!CommonUtils.isEmpty(item.getImgs())) {
            helper.getView(R.id.iv_img).setTag(item.getImgs().get(0));
            GlideHelper.disPlayRound(helper.getView(R.id.iv_img), BitmapUtils.getImgUrl(item.getImgs().get(0)), radius, true);
        }
        helper.setText(R.id.tv_title, item.getTitle());
        helper.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, CookActivity.class);
            intent.putExtra(CookBean.class.getSimpleName(), item);
            intent.putExtra(IdBean.class.getSimpleName(), item.get_id());
            mContext.startActivity(intent);
        });
    }
}
