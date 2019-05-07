package com.renygit.x5webviewlib;

import android.net.Uri;

import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;

/**
 * Created by admin on 2017/7/28.
 */

public class RWebChromeClient extends WebChromeClient {

    private OpenFileChooserCallBack mOpenFileChooserCallBack;
    private ProgressCallBack progressCallBack;
    private WebTitleCallBack webTitleCallBack;

    public void setOpenFileChooserCallBack(OpenFileChooserCallBack mOpenFileChooserCallBack) {
        this.mOpenFileChooserCallBack = mOpenFileChooserCallBack;
    }

    public void setProgressCallBack(ProgressCallBack progressCallBack) {
        this.progressCallBack = progressCallBack;
    }

    public void setWebTitleCallBack(WebTitleCallBack webTitleCallBack) {
        this.webTitleCallBack = webTitleCallBack;
    }

    //For Android 3.0+
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
        if(null != mOpenFileChooserCallBack) {
            mOpenFileChooserCallBack.openFileChooserCallBack(uploadMsg, acceptType);
        }
    }

    // For Android < 3.0
    public void openFileChooser(ValueCallback<Uri> uploadMsg) {
        openFileChooser(uploadMsg, "");
    }


    // For Android  > 4.1.1
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
        openFileChooser(uploadMsg, acceptType);
    }

    // For Android 5.0+
    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
        if(null != mOpenFileChooserCallBack) {
            mOpenFileChooserCallBack.showFileChooserCallBack(filePathCallback);
        }
        return true;
    }

    @Override
    public void onProgressChanged(WebView webView, int newProgress) {
        if(null != progressCallBack){
            progressCallBack.onProgressChanged(webView, newProgress);
        }
    }

    @Override
    public void onReceivedTitle(WebView webView, String title) {
        if(null != webTitleCallBack){
            webTitleCallBack.onReceivedTitle(webView, title);
        }
    }

    public interface OpenFileChooserCallBack {
        void openFileChooserCallBack(ValueCallback<Uri> uploadMsg, String acceptType);
        void showFileChooserCallBack(ValueCallback<Uri[]> filePathCallback);
    }

    public interface ProgressCallBack{
        void onProgressChanged(WebView webView, int newProgress);
    }

    public interface WebTitleCallBack{
        void onReceivedTitle(WebView webView, String title);
    }

}