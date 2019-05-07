package com.git.reny.wallpaper.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.core.BaseActivity;
import com.git.reny.wallpaper.entity.other.TabEntity;
import com.git.reny.wallpaper.ui.adapter.TabPagerAdapter;
import com.git.reny.wallpaper.ui.fragment.HomeFragment;
import com.git.reny.wallpaper.ui.fragment.InfoFragment;
import com.git.reny.wallpaper.ui.fragment.MineFragment;
import com.git.reny.wallpaper.ui.fragment.ReplyFragment;
import com.git.reny.wallpaper.widget.SViewPager;
import com.zyctd.mvplib.base.RBasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;

public class MainActivity extends BaseActivity {

    @BindView(R.id.vp)
    SViewPager vp;
    @BindView(R.id.ctl)
    CommonTabLayout ctl;

    @BindArray(R.array.tabTitles)
    String[] tabTitles;

    private int[] mIconSelectIds = {R.mipmap.ic_tab_home_p, R.mipmap.ic_tab_info_p, R.mipmap.ic_tab_active_p, R.mipmap.ic_tab_mine_p};
    private int[] mIconUnselectIds = {R.mipmap.ic_tab_home_n, R.mipmap.ic_tab_info_n, R.mipmap.ic_tab_active_n, R.mipmap.ic_tab_mine_n};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected RBasePresenter obtainPresenter() {
        return null;
    }

    @Override
    protected void init(Bundle bundle) {
        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        for (int i = 0; i < tabTitles.length; i++) {
            mTabEntities.add(new TabEntity(tabTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        ctl.setTabData(mTabEntities);

        List<Fragment> fragmentList = new ArrayList<>(tabTitles.length);
        fragmentList.add(new HomeFragment());
        fragmentList.add(new InfoFragment());
        fragmentList.add(new ReplyFragment());
        fragmentList.add(new MineFragment());

        vp.setCanScroll(false);
        vp.setAdapter(new TabPagerAdapter(getSupportFragmentManager(), fragmentList, tabTitles));
        vp.setCurrentItem(0);
        vp.setOffscreenPageLimit(tabTitles.length);

        ctl.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vp.setCurrentItem(position, false);
            }
            @Override
            public void onTabReselect(int position) {}
        });
    }

    @Override
    protected boolean isEnableSwipeBack() {
        return false;
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        moveTaskToBack(true);//false 必需是根Activity才有效 true任意Activity都有效
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }
}
