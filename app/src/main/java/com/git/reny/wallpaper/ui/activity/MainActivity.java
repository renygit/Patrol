package com.git.reny.wallpaper.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.core.BaseActivity;
import com.git.reny.wallpaper.ui.adapter.TabPagerAdapter;
import com.git.reny.wallpaper.ui.fragment.CategoryFragment;
import com.git.reny.wallpaper.ui.fragment.HomeFragment;
import com.git.reny.wallpaper.widget.SViewPager;
import com.zyctd.mvplib.base.RBasePresenter;
import com.zyctd.mvplib.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.segment)
    SegmentTabLayout segment;
    @BindView(R.id.view_pager)
    SViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private String[] Titles = {"精选", "分类"};

    private boolean showMore = false;
    private Menu menu;

    private CategoryFragment categoryFragment;

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
        segment.setTabData(Titles);
        segment.setCurrentTab(0);
        segment.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        List<Fragment> fragmentList = new ArrayList<>(Titles.length);
        fragmentList.add(new HomeFragment());

        categoryFragment = new CategoryFragment();
        fragmentList.add(categoryFragment);

        viewPager.setCanScroll(false);
        viewPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager(), fragmentList, Titles));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                segment.setCurrentTab(position);
                showMore = position == 1;
                showOrHideMenu();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> {
            startActivity(new Intent(getActivity(), SearchActivity.class));
        });
        toolbar.setOnMenuItemClickListener(item -> {
            if(null != categoryFragment){
                categoryFragment.setLayoutManager(item.getItemId());
            }
            return true;
        });
    }

    @Override
    protected boolean isEnableSwipeBack() {
        return false;
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);//false 必需是根Activity才有效 true任意Activity都有效
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        showOrHideMenu();
        return true;
    }

    private void showOrHideMenu(){
        menu.findItem(R.id.menu_card).setVisible(showMore);
        menu.findItem(R.id.menu_list).setVisible(showMore);
        menu.findItem(R.id.menu_grid).setVisible(showMore);
    }

}
