package com.git.reny.wallpaper.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.core.BaseFragment;
import com.git.reny.wallpaper.entity.response.HomeList;
import com.git.reny.wallpaper.entity.response.UserData;
import com.git.reny.wallpaper.presenter.MinePresenter;
import com.git.reny.wallpaper.ui.activity.LoginActivity;
import com.git.reny.wallpaper.ui.adapter.HomeListAdapter;
import com.git.reny.wallpaper.ui.mvp.MineView;
import com.git.reny.wallpaper.utils.CommonUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.renygit.multistateview.MultiStateView;
import com.renygit.scrolltoplib.AutoScrollBackLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class MineFragment extends BaseFragment<MinePresenter> implements MineView {

    @BindView(R.id.iv_head)
    RoundedImageView ivHead;
    @BindView(R.id.tv_nickName)
    TextView tvNickName;
    @BindView(R.id.tv_subTitle)
    TextView tvSubTitle;
    @BindView(R.id.card)
    CardView card;
    @BindView(R.id.ll_noLogin)
    LinearLayout llNoLogin;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    @BindView(R.id.scroll_layout)
    AutoScrollBackLayout scrollLayout;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    @BindView(R.id.msv)
    MultiStateView msv;
    @BindView(R.id.rv)
    RecyclerView rv;

    private HomeListAdapter adapter;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(UserData event) {
        if (UserData.isLogin()) {
            llNoLogin.setVisibility(View.GONE);
            llLogin.setVisibility(View.VISIBLE);
            tvNickName.setText(event.getName());
            tvSubTitle.setText("欢迎加入我们");

            presenter.loadData(true);
        } else {
            llNoLogin.setVisibility(View.VISIBLE);
            llLogin.setVisibility(View.GONE);
            tvNickName.setText("登录/注册");
            tvSubTitle.setText("登录后乐享更多功能");
        }
    }

    @Override
    public boolean useEventBus() {
        return true;
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
    protected MinePresenter obtainPresenter() {
        return new MinePresenter(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        card.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        });
        scrollLayout.bindScrollBack();
    }

    @Override
    protected void onResumeLazy() {
        super.onResumeLazy();
        EventBus.getDefault().post(UserData.isLogin() ? UserData.self : new UserData());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void setData(boolean isRefresh, HomeList data) {
        if (null == adapter) {
            adapter = new HomeListAdapter(data.getListData());
            CommonUtils.initRecyclerView(rv, new GridLayoutManager(getActivity(), 2));
            rv.setAdapter(adapter);
        } else {
            if (isRefresh) {
                adapter.replaceData(data.getListData());
                //adapter.setNewData(data.getListData());
            } else {
                adapter.addData(data.getListData());
                adapter.notifyDataSetChanged();
            }
        }
    }
}
