package com.git.reny.wallpaper.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.core.BaseFragment;
import com.git.reny.wallpaper.entity.response.ImgListData;
import com.git.reny.wallpaper.presenter.HomePresenter;
import com.git.reny.wallpaper.ui.adapter.HomeListAdapter;
import com.git.reny.wallpaper.ui.mvp.HomeView;
import com.git.reny.wallpaper.utils.CommonUtils;
import com.renygit.multistateview.MultiStateView;
import com.renygit.scrolltoplib.AutoScrollBackLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeView {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.scroll_layout)
    AutoScrollBackLayout scrollLayout;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    @BindView(R.id.msv)
    MultiStateView msv;

    private HomeListAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter obtainPresenter() {
        return new HomePresenter(this);
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
    protected void init(Bundle bundle) {
        scrollLayout.bindScrollBack();
        CommonUtils.initRecyclerView(rv, new GridLayoutManager(getActivity(), 3));
    }


    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        presenter.loadData(true);
    }

    @Override
    public void setData(boolean isRefresh, ImgListData data) {
        if(null == adapter){
            adapter = new HomeListAdapter(data.getListData());
            rv.setAdapter(adapter);
        }else {
            if(isRefresh) {
                adapter.setNewData(data.getListData());
                rv.scrollToPosition(0);
            }else {
                adapter.addData(data.getListData());
            }
        }
    }
}
