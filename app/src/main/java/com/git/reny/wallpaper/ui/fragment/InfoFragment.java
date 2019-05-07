package com.git.reny.wallpaper.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.core.BaseFragment;
import com.git.reny.wallpaper.entity.response.ListResults;
import com.git.reny.wallpaper.presenter.InfoPresenter;
import com.git.reny.wallpaper.ui.adapter.TabPagerAdapter;
import com.git.reny.wallpaper.ui.mvp.InfoView;
import com.renygit.multistateview.MultiStateView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class InfoFragment extends BaseFragment<InfoPresenter> implements InfoView {

    @BindView(R.id.stl)
    SlidingTabLayout stl;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.msv)
    MultiStateView msv;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_info;
    }

    public MultiStateView getMultiStateView() {
        return msv;
    }

    @Override
    protected InfoPresenter obtainPresenter() {
        return new InfoPresenter(this);
    }

    @Override
    protected void init(Bundle bundle) {}

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        presenter.loadData(true);
    }

    @Override
    public void setData(boolean isRefresh, ListResults data) {
        List<String> titles = data.getListData();
        List<Fragment> fragmentList = new ArrayList<>(titles.size());
        for (int i = 0; i < titles.size(); i++) {
            fragmentList.add(InfoOtherFragment.newInstance().setCategory(titles.get(i)));
        }

        vp.setAdapter(new TabPagerAdapter(getChildFragmentManager(), fragmentList, titles.toArray(new String[0])));
        vp.setCurrentItem(0);
        vp.setOffscreenPageLimit(titles.size());
        stl.setViewPager(vp);
        stl.onPageSelected(0);
    }
}
