package com.git.reny.wallpaper.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.core.BaseFragment;
import com.git.reny.wallpaper.entity.response.HomeList;
import com.git.reny.wallpaper.presenter.ReplyAllPresenter;
import com.git.reny.wallpaper.ui.mvp.ReplyAllView;
import com.renygit.multistateview.MultiStateView;
import com.renygit.scrolltoplib.AutoScrollBackLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import butterknife.BindView;

//首页-互动-全部
public class ReplyAllFragment extends BaseFragment<ReplyAllPresenter> implements ReplyAllView {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.scroll_layout)
    AutoScrollBackLayout scrollLayout;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    @BindView(R.id.msv)
    MultiStateView msv;

    @Override
    protected MultiStateView getMultiStateView() {
        return msv;
    }

    @Override
    protected RefreshLayout getRefreshLayout() {
        return srl;
    }

    @Override
    protected ReplyAllPresenter obtainPresenter() {
        return new ReplyAllPresenter(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        scrollLayout.bindScrollBack();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_reply_all;
    }

    @Override
    public void setData(boolean isRefresh, HomeList data) {
        /*if (null == adapter) {
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
        }*/
    }
}
