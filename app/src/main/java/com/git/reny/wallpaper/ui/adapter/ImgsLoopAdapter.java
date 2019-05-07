package com.git.reny.wallpaper.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.git.reny.wallpaper.utils.glide.GlideHelper;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

import java.util.List;

/**
 * Created by reny on 2016/7/6.
 */

public class ImgsLoopAdapter extends LoopPagerAdapter {


    private List<String> imgUrls;

    public ImgsLoopAdapter(RollPagerView viewPager, List<String> imgUrls) {
        super(viewPager);
        this.imgUrls = imgUrls;
    }

    public void setImgUrls(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        GlideHelper.disPlayRound(view, imgUrls.get(position), 20, true);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }

    @Override
    public int getRealCount() {
        return imgUrls.size();
    }

}
