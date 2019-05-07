package com.git.reny.wallpaper.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.git.reny.wallpaper.R;
import com.git.reny.wallpaper.core.BaseActivity;
import com.git.reny.wallpaper.entity.response.CollectData;
import com.git.reny.wallpaper.presenter.WebPresenter;
import com.git.reny.wallpaper.ui.mvp.Web_View;
import com.git.reny.wallpaper.utils.CommonUtils;
import com.git.reny.wallpaper.widget.webview.MyWebViewClient;
import com.renygit.x5webviewlib.X5WebView;

import butterknife.BindView;

/**
 * Created by admin on 2017/6/28.
 * 通用网页
 */

public class WebActivity extends BaseActivity<WebPresenter> implements Web_View {

    @BindView(R.id.fl_cancel)
    FrameLayout flCancel;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.webView)
    X5WebView webView;
    @BindView(R.id.cb_collect)
    CheckBox cbCollect;

    private String cookId;

    public static void open(Context context, String url, String title, String cookId) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        intent.putExtra("cookId", cookId);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void init(Bundle bundle) {
        String url = "about:blank";
        String title = "查看详情";

        if (getIntent() != null && getIntent().getExtras() != null) {
            Bundle bd = getIntent().getExtras();
            url = bd.getString("url");
            title = TextUtils.isEmpty(bd.getString("title")) ? "查看详情" : bd.getString("title");
            tvTitle.setText(title);
            cookId = bd.getString("cookId");
        }

        webView.setWebClient(new MyWebViewClient());
        webView.loadUrl(url);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            if (webView.canGoBack()) {
                webView.goBack();// 返回前一个页面
            } else {
                finish();
            }
        });

        flCancel.setOnClickListener(v -> finish());


        String finalTitle = title;
        String finalUrl = url;
        ivShare.setOnClickListener(v -> {
            CommonUtils.shareMsg(getActivity(), TAG, finalTitle, finalUrl, null);
        });

        cbCollect.setOnClickListener(v -> {
            cbCollect.setChecked(!cbCollect.isChecked());
            presenter.collect(cookId);
        });

        if (!TextUtils.isEmpty(cookId)) {
            cbCollect.setVisibility(View.VISIBLE);
            presenter.isCollect(cookId);
        }else {
            cbCollect.setVisibility(View.GONE);
        }

    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();// 返回前一个页面
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onDestroy() {
        if (webView != null) {
            webView.setVisibility(View.GONE);//解决偶尔退出崩溃的问题
            webView.destroy();
        }
        super.onDestroy();
    }

    @Override
    protected WebPresenter obtainPresenter() {
        return new WebPresenter(this);
    }


    @Override
    public void setCollectData(CollectData data) {
        cbCollect.setChecked(data.isCollect());
    }
}