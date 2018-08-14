package com.zyctd.mvplib.base;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by reny on 2017/6/5.
 */

public interface IRBaseView {
    Activity getActivity();

    void startActivity(Intent intent);

    void finish();

    boolean useEventBus();

    /***
     * 加载数据后调用，更新界面状态
     * @param isRefresh 是否是通过刷新加载的新数据
     * @param isEmpty 加载的新数据是否为空
     * @param isError 加载新数据时是否出错
     */
    void finishRefresh(boolean isRefresh, boolean isEmpty, boolean isError);
}
