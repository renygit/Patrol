package com.git.reny.wallpaper.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.core.BaseFragment;
import com.git.reny.wallpaper.entity.response.HomeList;
import com.git.reny.wallpaper.presenter.HomeOtherPresenter;
import com.git.reny.wallpaper.ui.adapter.HomeListAdapter;
import com.git.reny.wallpaper.ui.mvp.HomeOtherView;
import com.git.reny.wallpaper.utils.CommonUtils;
import com.renygit.multistateview.MultiStateView;
import com.renygit.scrolltoplib.AutoScrollBackLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import butterknife.BindView;

public class HomeOtherFragment extends BaseFragment<HomeOtherPresenter> implements HomeOtherView {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.scroll_layout)
    AutoScrollBackLayout scrollLayout;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    @BindView(R.id.msv)
    MultiStateView msv;

    private String category;
    private HomeListAdapter adapter;

    public static HomeOtherFragment newInstance() {
        return new HomeOtherFragment();
    }

    public HomeOtherFragment setCategory(String category) {
        this.category = category;
        return this;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_other;
    }

    @Override
    protected HomeOtherPresenter obtainPresenter() {
        return new HomeOtherPresenter(this);
    }

    @Override
    protected MultiStateView getMultiStateView() {
        return msv;
    }

    @Override
    protected RefreshLayout getRefreshLayout() {
        return srl;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        scrollLayout.bindScrollBack();
    }

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        presenter.setCategory(category);
        presenter.loadData(true);
    }

    @Override
    public void setData(boolean isRefresh, HomeList data) {
        if (null == adapter) {
            adapter = new HomeListAdapter(data.getListData());
            CommonUtils.initRecyclerView(rv, new GridLayoutManager(getActivity(), 2));
            rv.setAdapter(adapter);
        } else {
            if (isRefresh) {
                adapter.setNewData(data.getListData());
            } else {
                adapter.addData(data.getListData());
                adapter.notifyDataSetChanged();
            }
        }
    }
}
