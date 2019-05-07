package com.renygit.x5webviewlib;

import android.graphics.Bitmap;

import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * Created by reny on 2018/4/12.
 */

public class RWebViewClient extends WebViewClient {

    private PageOpenCallBack pageOpenCallBack;

    public void setPageOpenCallBack(PageOpenCallBack pageOpenCallBack) {
        this.pageOpenCallBack = pageOpenCallBack;
    }

    @Override
    public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
        super.onPageStarted(webView, s, bitmap);
        if(null != pageOpenCallBack){
            pageOpenCallBack.onPageStarted(webView, s, bitmap);
        }
    }

    @Override
    public void onPageFinished(WebView webView, String s) {
        super.onPageFinished(webView, s);
        if(null != pageOpenCallBack){
            pageOpenCallBack.onPageFinished(webView, s);
        }
    }

    public interface PageOpenCallBack{
        void onPageStarted(WebView webView, String s, Bitmap bitmap);
        void onPageFinished(WebView webView, String s);
    }

}
