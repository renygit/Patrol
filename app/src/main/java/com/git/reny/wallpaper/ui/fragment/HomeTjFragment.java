package com.git.reny.wallpaper.ui.fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.core.BaseFragment;
import com.git.reny.wallpaper.entity.response.HomeRecommend;
import com.git.reny.wallpaper.entity.response.HomeRecommendList;
import com.git.reny.wallpaper.presenter.HomeTjPresenter;
import com.git.reny.wallpaper.ui.activity.WebActivity;
import com.git.reny.wallpaper.ui.adapter.HomeRecommendListAdapter;
import com.git.reny.wallpaper.ui.adapter.HomeRollVideoAdapter;
import com.git.reny.wallpaper.ui.adapter.ImgsLoopAdapter;
import com.git.reny.wallpaper.ui.mvp.HomeTjView;
import com.git.reny.wallpaper.utils.CommonUtils;
import com.zyctd.mvplib.utils.ResUtils;
import com.git.reny.wallpaper.widget.divider.HDivider25Padding;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.renygit.multistateview.MultiStateView;
import com.renygit.scrolltoplib.AutoScrollBackLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zyctd.mvplib.utils.AppUtils;
import com.zyctd.mvplib.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayerStandard;

import static android.support.v7.widget.RecyclerView.HORIZONTAL;

public class HomeTjFragment extends BaseFragment<HomeTjPresenter> implements HomeTjView {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.rollView)
    RollPagerView rollView;
    @BindView(R.id.scroll_layout)
    AutoScrollBackLayout scrollLayout;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    @BindView(R.id.msv)
    MultiStateView msv;
    @BindView(R.id.tv_recommend_tip)
    TextView tvRecommendTip;
    @BindView(R.id.rv_recommend)
    RecyclerView rv_recommend;

    private HomeRecommendListAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_tj;
    }

    @Override
    protected HomeTjPresenter obtainPresenter() {
        return new HomeTjPresenter(this);
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
        presenter.loadData(true);
    }

    @Override
    public void setData(boolean isRefresh, HomeRecommend data) {
        List<HomeRecommend.BannerlistBean> bannerlist = data.getBannerlist();
        if (!CommonUtils.isEmpty(bannerlist)) {
            rollView.setVisibility(View.VISIBLE);
            rollView.setHintView(new ColorPointHintView(getActivity(), ResUtils.getColor(R.color.roll_point_hint_p), ResUtils.getColor(R.color.roll_point_hint_n)));
            List<String> imgUrls = new ArrayList<>(bannerlist.size());
            for (int i = 0; i < bannerlist.size(); i++) {
                imgUrls.add(bannerlist.get(i).getBanner_picture());
            }
            rollView.setAdapter(new ImgsLoopAdapter(rollView, imgUrls));
            rollView.setOnItemClickListener(position -> {
                WebActivity.open(getActivity(), bannerlist.get(position).getBanner_link(), bannerlist.get(position).getBanner_title(), null);
                //ToastUtils.showLong(bannerlist.get(position).getBanner_link());
            });
        } else {
            rollView.setVisibility(View.GONE);
        }

        if(!TextUtils.isEmpty(data.getRecommend_text())) {
            if (data.getRecommend_text().startsWith("“") && !data.getRecommend_text().endsWith("”")) {
                data.setRecommend_text(data.getRecommend_text() + "”");
            }
            tvRecommendTip.setText(data.getRecommend_text());
            tvRecommendTip.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            tvRecommendTip.setSingleLine(true);
            tvRecommendTip.setSelected(true);
            tvRecommendTip.setFocusable(true);
            tvRecommendTip.setFocusableInTouchMode(true);
            tvRecommendTip.setVisibility(View.VISIBLE);
        }else {
            tvRecommendTip.setVisibility(View.GONE);
        }

        List<HomeRecommend.VideoInfoBean> video_info = data.getVideo_info();
        if(!CommonUtils.isEmpty(video_info)) {
            rv_recommend.setVisibility(View.VISIBLE);
            CommonUtils.initRecyclerView(rv_recommend, new LinearLayoutManager(getActivity(), HORIZONTAL, false));
            HomeRollVideoAdapter adapter = new HomeRollVideoAdapter(video_info);
            adapter.setOnItemClickListener((adapter1, view, position) -> {
                HomeRecommend.VideoInfoBean item = video_info.get(position);
                JZVideoPlayerStandard.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                JZVideoPlayerStandard.startFullscreen(getActivity(), JZVideoPlayerStandard.class, item.getVideo().getVendor_video(), item.getTitle());
                //JzvdStd.startFullscreen(getActivity(), JzvdStd.class, item.getVideo().getVendor_video(), item.getTitle());
            });
            rv_recommend.setAdapter(adapter);
        }else {
            rv_recommend.setVisibility(View.GONE);
        }
    }

    @Override
    public void setListData(boolean isRefresh, HomeRecommendList data) {
        if(null == adapter){
            adapter = new HomeRecommendListAdapter(data.getListData());
            CommonUtils.initRecyclerView(rv, new LinearLayoutManager(getActivity()));
            rv.addItemDecoration(new HDivider25Padding());
            rv.setAdapter(adapter);
        }else {
            if(isRefresh) {
                adapter.setNewData(data.getListData());
            }else {
                adapter.addData(data.getListData());
                adapter.notifyDataSetChanged();
            }
        }
    }

}
