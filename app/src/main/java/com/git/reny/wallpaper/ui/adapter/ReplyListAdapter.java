package com.git.reny.wallpaper.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.entity.response.ReplyBean;
import com.git.reny.wallpaper.utils.FuzzyDateTimeFormatter;

import java.util.List;

public class ReplyListAdapter extends BaseQuickAdapter<ReplyBean, BaseViewHolder> {

    public ReplyListAdapter(@Nullable List<ReplyBean> data) {
        super(R.layout.item_reply, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReplyBean item) {
        helper.setText(R.id.tv_nickName, item.getUserInfo().get(0).getName());
        helper.setText(R.id.tv_time, FuzzyDateTimeFormatter.getTimeAgo(mContext, item.getDate().get$date()));
        helper.setText(R.id.tv_content, item.getRemarkContent());
    }
}
