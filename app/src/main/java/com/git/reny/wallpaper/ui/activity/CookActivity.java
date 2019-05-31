package com.git.reny.wallpaper.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.roundview.RoundLinearLayout;
import com.flyco.roundview.RoundTextView;
import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.core.BaseActivity;
import com.git.reny.wallpaper.entity.other.ImgsInfo;
import com.git.reny.wallpaper.entity.response.CookBean;
import com.git.reny.wallpaper.entity.response.IdBean;
import com.git.reny.wallpaper.entity.response.ReplyList;
import com.git.reny.wallpaper.entity.response.UserData;
import com.git.reny.wallpaper.presenter.CookPresenter;
import com.git.reny.wallpaper.ui.adapter.ImgsLoopAdapter;
import com.git.reny.wallpaper.ui.adapter.ReplyListAdapter;
import com.git.reny.wallpaper.ui.mvp.CookView;
import com.git.reny.wallpaper.utils.BitmapUtils;
import com.git.reny.wallpaper.utils.CommonUtils;
import com.git.reny.wallpaper.widget.ReplyBottomSheetDialog;
import com.git.reny.wallpaper.widget.divider.HDivider25Padding;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.renygit.multistateview.MultiStateView;
import com.renygit.scrolltoplib.AutoScrollBackLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zyctd.mvplib.utils.ResUtils;
import com.zyctd.mvplib.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CookActivity extends BaseActivity<CookPresenter> implements CookView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rollView)
    RollPagerView rollView;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.msv)
    MultiStateView msv;
    @BindView(R.id.scroll_layout)
    AutoScrollBackLayout scrollLayout;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    @BindView(R.id.ll_report)
    RoundLinearLayout llReport;
    @BindView(R.id.cb_praise)
    CheckBox cbPraise;
    @BindView(R.id.tv_publish_reply)
    RoundTextView tvPublishReply;
    @BindView(R.id.ll_toolbar)
    LinearLayout llToolbar;

    private ReplyListAdapter adapter;

    private ReplyBottomSheetDialog dialogReply;

    private CookBean cookBean;
    private IdBean idBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cook;
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    protected RefreshLayout getRefreshLayout() {
        return srl;
    }

    @Override
    protected MultiStateView getMultiStateView() {
        return msv;
    }

    @Override
    protected CookPresenter obtainPresenter() {
        return new CookPresenter(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        if (null != getIntent() && getIntent().hasExtra(CookBean.class.getSimpleName())) {
            cookBean = getIntent().getParcelableExtra(CookBean.class.getSimpleName());
            idBean = getIntent().getParcelableExtra(IdBean.class.getSimpleName());
        }
        if (null == cookBean || null == idBean) {
            ToastUtils.showLong("参数传递出错");
            finish();
            return;
        }

        scrollLayout.bindScrollBack();
        tvTitle.setText(cookBean.getTitle());
        tvContent.setText(cookBean.getContent());

        if (!CommonUtils.isEmpty(cookBean.getImgs())) {
            rollView.setVisibility(View.VISIBLE);

            List<String> imgUrls = new ArrayList<>(cookBean.getImgs().size());
            for (int i = 0; i < cookBean.getImgs().size(); i++) {
                imgUrls.add(BitmapUtils.getImgUrl(cookBean.getImgs().get(i)));
            }
            rollView.setAdapter(new ImgsLoopAdapter(rollView, imgUrls));
            rollView.setOnItemClickListener(position -> {
                Intent intent = new Intent(getActivity(), ImagesActivity.class);
                intent.putExtra(ImgsInfo.KEY, new ImgsInfo(imgUrls, position));
                startActivity(intent);
            });

            if (cookBean.getImgs().size() == 1) {
                rollView.setHintView(null);//隐藏指示器
                if (rollView.isPlaying()) rollView.pause();
            } else {
                rollView.setHintView(new ColorPointHintView(getActivity(), ResUtils.getColor(R.color.roll_point_hint_p), ResUtils.getColor(R.color.roll_point_hint_n)));
            }
        } else {
            rollView.setVisibility(View.GONE);
        }


        tvPublishReply.setOnClickListener(v -> {
            if(UserData.isLogin(getActivity())) {
                showReplyDialog();
            }
        });

        presenter.setCookId(idBean.get$oid());
        presenter.loadData(true);

    }

    @Override
    public void setData(boolean isRefresh, ReplyList data) {
        if (null == adapter) {
            if(!CommonUtils.isEmpty(data.getListData())) {
                adapter = new ReplyListAdapter(data.getListData());
                CommonUtils.initRecyclerView(rv, new LinearLayoutManager(getActivity()));
                rv.addItemDecoration(new HDivider25Padding());
                rv.setAdapter(adapter);
            }
        } else {
            if (isRefresh) {
                adapter.setNewData(data.getListData());
            } else {
                adapter.addData(data.getListData());
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void showReplyDialog(){
        if(null == dialogReply) {
            dialogReply = new ReplyBottomSheetDialog(getActivity(), idBean.get$oid());
            dialogReply.setCallBack((content, replyId) -> {
                //不用判断为空 内部已做判断
                presenter.reply(replyId, content);
            });
        }
        dialogReply.show();
    }
}
