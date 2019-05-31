package com.git.reny.wallpaper;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.text.TextUtils;

import com.bumptech.glide.request.target.ViewTarget;
import com.git.reny.wallpaper.entity.response.UserData;
import com.git.reny.wallpaper.utils.ScreenInfoUtils;
import com.git.reny.wallpaper.utils.ThreadUtils;
import com.liulishuo.filedownloader.FileDownloader;
import com.mcxiaoke.packer.helper.PackerNg;
import com.renygit.multistateview.MultiStateConfig;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.tencent.smtt.sdk.QbSdk;
import com.umeng.analytics.MobclickAgent;
import com.wanjian.cockroach.Cockroach;
import com.zyctd.mvplib.utils.AppUtils;
import com.zyctd.mvplib.utils.LogUtils;

/**
 * Created by reny on 2017/11/16.
 */

public class MyApp extends Application {

    public static MyApp instance;

    static {
        //设置全局的多状态配置 局部支持xml设置 可以设置不同状态的图片提示
        MultiStateConfig.getInstance().setConfig(
                new MultiStateConfig.Build()
                        .setTipEmpty("没有相关数据，点击重试")
                        .setTipError("加载失败，点击重试")
                        .setTipNoNetwork("没有网络，点击重试")
                        .setIndicatorName("BallSpinFadeLoaderIndicator")
                        .setIndicatorColor(R.color.colorAccent)
                        .setImgError(R.mipmap.ic_err_tip)
                        .setImgEmpty(R.mipmap.ic_err_tip)
                        .setImgNoNetwork(R.mipmap.ic_err_tip)
        );

        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater((context, layout) -> {
            //指定Header
            return new MaterialHeader(context).setColorSchemeColors(0xff000000);
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater((context, layout) -> {
            //指定Footer
            return new ClassicsFooter(context);
        });
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        AppUtils.self().setApplication(instance);

        init();
    }

    private void init() {
        LogUtils.isPrintResponseData = true;
        LogUtils.init(BuildConfig.DEBUG);

        //LoginData.self().readData();

        ScreenInfoUtils.init(this);

        ViewTarget.setTagId(R.id.glide_tag);//解决Glide使用报错

        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                if(BuildConfig.DEBUG) {
                    LogUtils.e("x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核:" + arg0);
                }
            }
            @Override
            public void onCoreInitFinished() {
                if(BuildConfig.DEBUG){
                    LogUtils.e("QQ X5内核加载成功");
                }
            }
        };
        QbSdk.initX5Environment(getApplicationContext(), cb);


        UserData.readData();
        FileDownloader.setupOnApplicationOnCreate(this);

        MobclickAgent.setDebugMode(BuildConfig.DEBUG);
        MobclickAgent.openActivityDurationTrack(false);
        String channel = PackerNg.getChannel(this);//渠道名
        if(BuildConfig.DEBUG) {
            channel = TextUtils.isEmpty(channel) ? "reny" : channel;//DEBUG版本可能没有渠道名 默认设置一个
        }
        MobclickAgent.startWithConfigure(new MobclickAgent.UMAnalyticsConfig(this, "5a7cf2beb27b0a6b4800003d", channel));


        /*if(!BuildConfig.DEBUG) {
            ThreadUtils.self().add(() -> Cockroach.install((thread, throwable) -> {
                //这里捕获全局异常 需要主动上传到友盟错误统计   在友盟错误列表的tab“自定义错误”中才能查看
                MobclickAgent.reportError(getContext(), throwable);
            }));
        }*/
        ThreadUtils.self().add(() -> Cockroach.install((thread, throwable) -> {
            //这里捕获全局异常 需要主动上传到友盟错误统计   在友盟错误列表的tab“自定义错误”中才能查看
            MobclickAgent.reportError(getContext(), throwable);
        }));


        //Jzvd.setMediaInterface(new JZMediaIjkplayer());
    }

    public static Context getContext(){
        return instance.getApplicationContext();
    }
}
