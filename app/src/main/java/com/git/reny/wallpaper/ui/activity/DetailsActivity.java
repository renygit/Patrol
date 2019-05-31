package com.git.reny.wallpaper.ui.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.core.BaseActivity;
import com.git.reny.wallpaper.entity.response.HomeRecommendDetails;
import com.git.reny.wallpaper.presenter.DetailsPresenter;
import com.git.reny.wallpaper.ui.adapter.MaterialListAdapter;
import com.git.reny.wallpaper.ui.adapter.StepListAdapter;
import com.git.reny.wallpaper.ui.mvp.DetailsView;
import com.git.reny.wallpaper.utils.CommonUtils;
import com.git.reny.wallpaper.utils.glide.GlideHelper;
import com.git.reny.wallpaper.widget.RatioImageView;
import com.git.reny.wallpaper.widget.divider.HDividerFull;
import com.renygit.multistateview.MultiStateView;
import com.renygit.scrolltoplib.AutoScrollBackLayout;
import com.zyctd.mvplib.utils.ToastUtils;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class DetailsActivity extends BaseActivity<DetailsPresenter> implements DetailsView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.scroll_layout)
    AutoScrollBackLayout scrollLayout;
    @BindView(R.id.msv)
    MultiStateView msv;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_author)
    TextView tvAuthor;
    @BindView(R.id.iv_img)
    RatioImageView ivImg;
    @BindView(R.id.iv_has_video)
    ImageView ivHasVideo;
    @BindView(R.id.fl_img)
    FrameLayout flImg;
    @BindView(R.id.rv_material)
    RecyclerView rvMaterial;
    @BindView(R.id.rv_step)
    RecyclerView rvStep;
    @BindView(R.id.tv_tips)
    TextView tvTips;

    private String ids;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    protected MultiStateView getMultiStateView() {
        return msv;
    }

    @Override
    protected DetailsPresenter obtainPresenter() {
        return new DetailsPresenter(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        if (null != getIntent() && getIntent().hasExtra("ids")) {
            ids = getIntent().getStringExtra("ids");
        }
        if (TextUtils.isEmpty(ids)) {
            ToastUtils.showLong("参数传递出错");
            finish();
            return;
        }
        scrollLayout.bindScrollBack();

        presenter.setIds(ids);
        presenter.loadData(true);
    }

    @Override
    public void setData(boolean isRefresh, HomeRecommendDetails data) {
        tvTitle.setText(data.getName());
        tvTime.setText(data.getGettime());
        tvAuthor.setText(String.format("来源：%s", data.getAuthorname()));
        GlideHelper.disPlayRound(ivImg, data.getImageid(), 15, true);
        ivHasVideo.setVisibility(data.isHasVideo() ? View.VISIBLE : View.GONE);
        flImg.setOnClickListener(v -> {
            if(data.isHasVideo() && null != data.getVideo() && !TextUtils.isEmpty(data.getVideo().getUrl())){
                JZVideoPlayerStandard.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                JZVideoPlayerStandard.startFullscreen(getActivity(), JZVideoPlayerStandard.class, data.getVideo().getUrl(), data.getDescription());
            }
        });

        MaterialListAdapter materialListAdapter = new MaterialListAdapter(data.getMaterialList());
        CommonUtils.initRecyclerView(rvMaterial);
        rvMaterial.addItemDecoration(new HDividerFull());
        rvMaterial.setAdapter(materialListAdapter);

        StepListAdapter stepListAdapter = new StepListAdapter(data.getStepList());
        CommonUtils.initRecyclerView(rvStep);
        rvStep.setAdapter(stepListAdapter);

        if(!CommonUtils.isEmpty(data.getTipList())) {
            tvTips.setText(data.getTipList().get(0).getDetails());
        }
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }
}
