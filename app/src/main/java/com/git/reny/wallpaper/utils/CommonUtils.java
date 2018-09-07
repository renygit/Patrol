package com.git.reny.wallpaper.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.git.reny.wallpaper.R;
import com.zyctd.mvplib.base.RBasePresenter;
import com.zyctd.mvplib.utils.AppUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by reny on 2017/6/14.
 */

public class CommonUtils {

    /***
     * 延迟事件
     * @param presenter 用于管理 Rx 的生命周期
     * @param listener 延迟后的事件
     * @param delay 延迟毫秒
     */
    public static void delayRun(RBasePresenter presenter, long delay, RunListener listener){
        if(null != presenter) {
            Observable<Long> observable = Observable.timer(delay, TimeUnit.MILLISECONDS);
            presenter.addDisposable(observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableObserver<Long>() {
                        @Override
                        public void onNext(Long value) {
                            if (null != listener) {
                                listener.run();
                            }
                            if (!this.isDisposed()) {
                                this.dispose();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onComplete() {
                        }
                    }));
        }
    }

    /***
     *
     * @param presenter
     * @param total 总共时间 秒
     * @param interval 间隔时间 秒
     * @param listener
     */
    public static void delayRun(RBasePresenter presenter, long total, long interval, RunListener listener){
        Observable<Long> observable = Observable.interval(interval, TimeUnit.SECONDS);
        presenter.addDisposable(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Long>(){
                    @Override
                    public void onNext(Long aLong) {
                        if (null != listener) {
                            listener.run();
                        }
                        if (aLong >= total) {
                            if (!this.isDisposed()) {
                                this.dispose();
                            }
                        }
                    }
                    @Override
                    public void onError(Throwable e) {}
                    @Override
                    public void onComplete() {}
                }));
    }

    public interface RunListener{
        void run();
    }

    public static boolean isEmpty(Object datas) {
        boolean isEmpty = (null == datas);
        if (null != datas && datas instanceof List) {
            isEmpty = (((List) datas).size() == 0);
        }
        return isEmpty;
    }

    public static void hideSoftInput(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if(null != imm) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
        }
    }


    public static void call(Context context, String tel){
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }catch (Exception e){
            AppUtils.self().showToastLong("调用拨打电话出错，请重试");
        }
    }

    /**
     * 获取版本号
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        // 获取软件版本号
        int versionCode = 0;
        PackageInfo packageInfo = getVersionInfo(context);
        if(null != packageInfo){
            versionCode = packageInfo.versionCode;
        }
        return versionCode;
    }

    public static PackageInfo getVersionInfo(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void initRecyclerView(RecyclerView rv, RecyclerView.LayoutManager... layoutManager) {
        if (layoutManager.length == 0) {
            rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        } else {
            rv.setLayoutManager(layoutManager[0]);
        }
        rv.setNestedScrollingEnabled(false);
        rv.setHasFixedSize(true);
    }

    /*public static BottomSheetDialog getBottomSheetDialog(Context context, RecyclerView.Adapter adapter) {
        RecyclerView rv = (RecyclerView) LayoutInflater.from(context).inflate(R.layout.dialog_bottom_sheet, null);
        initRecyclerView(rv);
        rv.addItemDecoration(new HDividerFull());
        rv.setAdapter(adapter);

        BottomSheetDialog dialog = new BottomSheetDialog(context);
        dialog.setContentView(rv);

        return dialog;
    }*/

    public static void setStatusHeight(final View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                //父类不是ViewGroup类型的会报错
                ViewGroup.LayoutParams lp = view.getLayoutParams();
                lp.height = ResUtils.getDimenPx(R.dimen.status_bar_height);

                int resourceId = view.getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
                if (resourceId > 0) {
                    //根据资源ID获取响应的尺寸值
                    lp.height = view.getContext().getResources().getDimensionPixelSize(resourceId);
                }

                view.setLayoutParams(lp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String format2decimal(Object d) {
        if (null == d) {
            return "--";
        } else {
            return String.format(ResUtils.getString(R.string.format2decimal), d);
        }
    }

    public static String getTipNum99(int tipNum) {
        return tipNum > 99 ? "99+" : String.valueOf(tipNum);
    }

    /**
     * 判断非空字符串
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return (str == null || str.trim().length() == 0);
    }
}
