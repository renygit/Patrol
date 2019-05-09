package com.git.reny.wallpaper.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.core.BaseFragment;
import com.git.reny.wallpaper.entity.response.CookBean;
import com.git.reny.wallpaper.entity.response.UserData;
import com.git.reny.wallpaper.presenter.ReplyPresenter;
import com.git.reny.wallpaper.ui.activity.UploadActivity;
import com.git.reny.wallpaper.ui.adapter.CardAdapter;
import com.git.reny.wallpaper.ui.adapter.ReplyListAdapter;
import com.git.reny.wallpaper.ui.mvp.ReplyView;
import com.renygit.multistateview.MultiStateView;
import com.renygit.scrolltoplib.AutoScrollBackLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import recycler.coverflow.RecyclerCoverFlow;

//首页-互动
public class ReplyFragment extends BaseFragment<ReplyPresenter> implements ReplyView {

    @BindView(R.id.rv_recommend)
    RecyclerCoverFlow rvRecommend;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.scroll_layout)
    AutoScrollBackLayout scrollLayout;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    @BindView(R.id.msv)
    MultiStateView msv;
    @BindView(R.id.btn_upload)
    FloatingActionButton btnUpload;

    private CardAdapter adapter;
    private ReplyListAdapter listAdapter;

    private int recommandNum = 5;
    private List<CookBean> listDatas;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_reply;
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
    protected ReplyPresenter obtainPresenter() {
        return new ReplyPresenter(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        scrollLayout.bindScrollBack();
        btnUpload.setOnClickListener(v -> {
            if (UserData.isLogin(getActivity())) {
                startActivity(new Intent(getActivity(), UploadActivity.class));
            }
        });
    }

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        presenter.loadData(true);
    }

    @Override
    public void setData(boolean isRefresh, List<CookBean> datas) {
        if (null == listAdapter) {
            listDatas = new ArrayList<>();
            listAdapter = new ReplyListAdapter(listDatas);
            rvList.setHasFixedSize(true);
            rvList.setNestedScrollingEnabled(false);
            rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvList.setAdapter(listAdapter);
        }

        if (isRefresh) {
            if (null == adapter) {
                adapter = new CardAdapter(datas.size() > recommandNum ? datas.subList(0, recommandNum) : datas);
                rvRecommend.setAdapter(adapter);
            }else {
                adapter.replaceData(datas.size() > recommandNum ? datas.subList(0, recommandNum) : datas);
            }

            if(datas.size() > recommandNum){
                rvList.setVisibility(View.VISIBLE);
                listDatas.clear();
                listDatas.addAll(datas.subList(recommandNum, datas.size()));
                listAdapter.notifyDataSetChanged();
            }else {
                rvList.setVisibility(View.GONE);
            }
        }else {
            listDatas.addAll(datas);
            listAdapter.notifyDataSetChanged();
        }
    }
}
