package com.git.reny.wallpaper.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;

import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.core.BaseActivity;
import com.git.reny.wallpaper.entity.response.HomeList;
import com.git.reny.wallpaper.presenter.SearchPresenter;
import com.git.reny.wallpaper.ui.adapter.HomeListAdapter;
import com.git.reny.wallpaper.ui.mvp.SearchView;
import com.git.reny.wallpaper.utils.CommonUtils;
import com.renygit.multistateview.MultiStateView;
import com.renygit.scrolltoplib.AutoScrollBackLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.xw.repo.XEditText;
import com.zyctd.mvplib.utils.ToastUtils;

import butterknife.BindView;

/**
 * Created by reny on 2019/5/11.
 */

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchView {

    @BindView(R.id.et_key_word)
    XEditText etKeyWord;
    @BindView(R.id.iv_search)
    ImageView iv_search;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
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
        return R.layout.activity_search;
    }

    @Override
    protected Toolbar getToolbar() {
        return toolbar;
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
    protected SearchPresenter obtainPresenter() {
        return new SearchPresenter(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        scrollLayout.bindScrollBack();

        iv_search.setOnClickListener(v -> clickSearch());
        etKeyWord.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                clickSearch();
            }
            return false;
        });

    }

    @Override
    public void onBackPressed() {
        CommonUtils.hideSoftInput(etKeyWord);
        super.onBackPressed();
    }


    private void clickSearch() {
        String keyWord = etKeyWord.getText().toString().trim();
        if(TextUtils.isEmpty(keyWord)){
            ToastUtils.showLong("搜索内容不能为空");
            return;
        }
        msv.setVisibility(View.VISIBLE);
        setFirstLoadData(true);
        presenter.setKeyWord(keyWord);
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
