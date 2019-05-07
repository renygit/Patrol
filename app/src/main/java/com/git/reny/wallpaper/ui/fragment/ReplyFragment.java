package com.git.reny.wallpaper.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

import com.flyco.tablayout.SlidingTabLayout;
import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.core.BaseFragment;
import com.git.reny.wallpaper.entity.response.UserData;
import com.git.reny.wallpaper.ui.activity.UploadActivity;
import com.git.reny.wallpaper.ui.adapter.TabPagerAdapter;
import com.git.reny.wallpaper.widget.SViewPager;
import com.zyctd.mvplib.base.RBasePresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

//首页-互动
public class ReplyFragment extends BaseFragment {

    @BindView(R.id.stl)
    SlidingTabLayout stl;
    @BindView(R.id.fl_add)
    FrameLayout flAdd;
    @BindView(R.id.vp)
    SViewPager vp;

    @Override
    protected RBasePresenter obtainPresenter() {
        return null;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        String[] titleArr = {"推荐", "全部"};
        List<String> titles = Arrays.asList(titleArr);
        List<Fragment> fragmentList = new ArrayList<>(titles.size());
        fragmentList.add(new ReplyRecommandFragment());
        fragmentList.add(new ReplyAllFragment());

        vp.setAdapter(new TabPagerAdapter(getChildFragmentManager(), fragmentList, titleArr));
        vp.setCurrentItem(0);
        vp.setCanScroll(false);
        vp.setOffscreenPageLimit(titles.size());
        stl.setViewPager(vp);
        stl.onPageSelected(0);

        flAdd.setOnClickListener(v -> {
            if(UserData.isLogin(getActivity())) {
                startActivity(new Intent(getActivity(), UploadActivity.class));
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_reply;
    }
}
