package com.git.reny.wallpaper.core;

import android.support.annotation.NonNull;

import com.zyctd.mvplib.base.RBasePresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by reny on 2017/11/16.
 */

public abstract class BasePresenter<V extends IBaseView> extends RBasePresenter<V> implements IBaseView{

    private Map<String, RequestBuild> requestMap;

    public BasePresenter(V view) {
        super(view);
    }

    @Override
    public void finishRefresh(boolean isRefresh, boolean isEmpty, boolean isError) {

    }

    protected RequestBuild getRequest(@NonNull String key){
        if(null == requestMap){
            requestMap = new HashMap<>();
        }
        if(!requestMap.containsKey(key) || null == requestMap.get(key)){
            RequestBuild request = new RequestBuild();
            requestMap.put(key, request);
            //LogUtils.e("创建了一个RequestBuild...");
            return request;
        }
        //LogUtils.e("复用了一个RequestBuild");
        return requestMap.get(key);
    }
}
