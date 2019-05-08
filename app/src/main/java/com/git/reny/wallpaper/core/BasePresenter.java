package com.git.reny.wallpaper.core;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.zyctd.mvplib.base.RBasePresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by reny on 2017/11/16.
 */

public abstract class BasePresenter<V extends IBaseView> extends RBasePresenter<V> implements IBaseView{

    private Map<String, RequestBuild> requestMap;
    private AlertDialog.Builder tipBuilder;

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

    public void showTipMsg(Boolean cancelable, String title, String msg, String leftText, String rightText, DialogInterface.OnClickListener leftClick, DialogInterface.OnClickListener rightClick){
        if(null == tipBuilder){
            tipBuilder = new AlertDialog.Builder(getActivity());
        }
        if(!TextUtils.isEmpty(title)){
            tipBuilder.setTitle(title);
            tipBuilder.setMessage(msg + "\n");
        }else {
            tipBuilder.setMessage("\n" + msg + "\n");
        }
        tipBuilder.setCancelable(null == cancelable ? true : cancelable);
        tipBuilder.setNegativeButton(null == leftText ? "取消" : leftText, leftClick);
        tipBuilder.setPositiveButton(null == rightText ? "确定" : rightText, rightClick);
        tipBuilder.show();
    }
}
