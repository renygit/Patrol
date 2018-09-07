package com.git.reny.wallpaper.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by reny on 2018/1/18.
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private List<? extends Fragment> fragmentList;
    private String[] titles;

    public TabPagerAdapter(FragmentManager fm, List<? extends Fragment> fragmentList, String[] titles) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titles = titles;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }
}
