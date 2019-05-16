package com.git.reny.wallpaper.ui.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.core.BaseActivity;
import com.git.reny.wallpaper.entity.other.ImgsInfo;
import com.git.reny.wallpaper.utils.glide.GlideHelper;
import com.git.reny.wallpaper.widget.BounceBackViewPager;
import com.luck.picture.lib.photoview.PhotoView;
import com.rd.PageIndicatorView;
import com.zyctd.mvplib.base.RBasePresenter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by admin on 2017/6/13.
 */

public class ImagesActivity extends BaseActivity {

    @BindView(R.id.vp_img)
    BounceBackViewPager vpImg;
    @BindView(R.id.pageIndicatorView)
    PageIndicatorView pageIndicatorView;
    @BindView(R.id.fl_root)
    FrameLayout flRoot;

    @Override
    protected boolean isEnableSwipeBack() {
        return false;
    }

    @Override
    protected boolean isTranslucentStatusBar() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_images;
    }

    @Override
    protected RBasePresenter obtainPresenter() {
        return null;
    }

    @Override
    protected void init(Bundle bundle) {
        flRoot.setOnClickListener(view -> finish());

        if (null != getIntent()) {
            ImgsInfo imgsInfo = (ImgsInfo) getIntent().getSerializableExtra(ImgsInfo.KEY);
            if (null == imgsInfo) return;
            pageIndicatorView.setCount(imgsInfo.getImgsList().size()); // specify total count of indicators
            pageIndicatorView.setSelection(imgsInfo.getCurPos());
            vpImg.setPageMargin((int) (getResources().getDisplayMetrics().density * 15));
            DraweePagerAdapter draweePagerAdapter = new DraweePagerAdapter(imgsInfo.getImgsList());
            vpImg.setAdapter(draweePagerAdapter);
            vpImg.setCurrentItem(imgsInfo.getCurPos());
            vpImg.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    pageIndicatorView.setSelection(position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
        }
    }

    private class DraweePagerAdapter extends PagerAdapter {

        private List<?> imgsUrl;

        public DraweePagerAdapter(List<?> imgsUrl) {
            this.imgsUrl = imgsUrl;
        }

        @Override
        public int getCount() {
            return imgsUrl.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup viewGroup, final int position) {
            PhotoView photoView = new PhotoView(viewGroup.getContext());
            GlideHelper.display(photoView, imgsUrl.get(position));
            photoView.setOnPhotoTapListener((view, x, y) -> finish());
            photoView.setOnOutsidePhotoTapListener(imageView -> finish());
            //photoView.setOnViewTapListener((view, x, y) -> finish());
            try {
                viewGroup.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return photoView;
        }
    }
}
