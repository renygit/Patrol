package com.git.reny.patrol.core;

import android.support.annotation.Nullable;

import com.git.reny.patrol.api.APIConfig;
import com.zyctd.mvplib.http.SimpleServiceFactory;

import okhttp3.OkHttpClient;

/**
 * Created by reny on 2017/2/9.
 */

public class CommonServiceFactory<S> extends SimpleServiceFactory<S> {

    private final long DEFAULT_TIMEOUT = 60;//超时时间 60秒

    private CommonServiceFactory(){}

    private static class SingletonHolder {
        private static final CommonServiceFactory INSTANCE = new CommonServiceFactory();
    }

    public static CommonServiceFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }


    @Nullable
    @Override
    public Long getDefaultTimeOut() {
        return DEFAULT_TIMEOUT;
    }

    @Override
    public void setClientBuilder(OkHttpClient.Builder clientBuilder) {

    }

    @Nullable
    @Override
    protected String getDefaultBaseUrl() {
        return APIConfig.BASE_URL_DEFAULT;
    }
}
