package com.git.reny.wallpaper.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.core.BaseFragment;
import com.git.reny.wallpaper.entity.response.CookBean;
import com.git.reny.wallpaper.presenter.ReplyRecommandPresenter;
import com.git.reny.wallpaper.ui.mvp.ReplyRecommandView;
import com.renygit.multistateview.MultiStateView;

import java.util.List;

import butterknife.BindView;

//首页-互动
public class ReplyRecommandFragment extends BaseFragment<ReplyRecommandPresenter> implements ReplyRecommandView {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.msv)
    MultiStateView msv;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_reply_recommand;
    }

    @Override
    protected MultiStateView getMultiStateView() {
        return msv;
    }

    @Override
    protected ReplyRecommandPresenter obtainPresenter() {
        return new ReplyRecommandPresenter(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        presenter.loadData(true);
    }

    @Override
    public void setData(boolean isRefresh, List<CookBean> datas) {
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
