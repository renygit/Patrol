package com.git.reny.wallpaper.widget.webview;

import com.renygit.x5webviewlib.RWebViewClient;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.WebView;

/**
 * Created by admin on 2017/7/28.
 */

public class MyWebViewClient extends RWebViewClient {

    @Override
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        //super.onReceivedSslError(webView, sslErrorHandler, sslError);
        //LogUtils.e("sslError:"+sslError.toString());
        if(sslError.getPrimaryError() == android.net.http.SslError.SSL_INVALID ){// 校验过程遇到了bug
            sslErrorHandler.proceed();
        }else{
            sslErrorHandler.cancel();
        }
    }

}
