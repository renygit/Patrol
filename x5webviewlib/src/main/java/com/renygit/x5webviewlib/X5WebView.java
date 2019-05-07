package com.renygit.x5webviewlib;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;

import com.coolindicator.sdk.CoolIndicator;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * Created by reny on 2018/4/11.
 */

public class X5WebView extends WebView{

    private CoolIndicator indicator;

    public void setChomeClient(RWebChromeClient chomeClient) {
        if(null != chomeClient) {
            chomeClient.setProgressCallBack(new RWebChromeClient.ProgressCallBack() {
                @Override
                public void onProgressChanged(WebView webView, int newProgress) {
                    if(newProgress >= 80){
                        indicator.complete();
                    }
                    Log.e("onProgressChanged", "newProgress:"+newProgress);
                }
            });
            this.setWebChromeClient(chomeClient);
        }
    }

    public void setWebClient(RWebViewClient webClient) {
        if(null != webClient) {
            webClient.setPageOpenCallBack(new RWebViewClient.PageOpenCallBack() {
                @Override
                public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                    indicator.start();
                }
                @Override
                public void onPageFinished(WebView webView, String s) {
                    indicator.complete();
                }
            });
            this.setWebViewClient(webClient);
        }
    }

    @Deprecated
    @Override
    public void setWebViewClient(WebViewClient webViewClient) {
        super.setWebViewClient(webViewClient);
    }

    @Deprecated
    @Override
    public void setWebChromeClient(WebChromeClient webChromeClient) {
        super.setWebChromeClient(webChromeClient);
    }

    public X5WebView(Context context) {
        this(context, null);
    }

    public X5WebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        indicator = (CoolIndicator) LayoutInflater.from(context).inflate(R.layout.progress_layout, null);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, dp2px(context, 2));
        lp.gravity = Gravity.TOP;
        this.addView(indicator, lp);

        initWebViewSettings();
        this.getView().setClickable(true);
    }


    private void initWebViewSettings() {
        WebSettings webSetting = this.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);

        // this.getSettingsExtension().setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);//extension
        // settings 的设计
    }

    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

}
