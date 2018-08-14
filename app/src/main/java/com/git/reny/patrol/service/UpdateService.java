package com.git.reny.patrol.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;

import com.git.reny.patrol.MyApp;
import com.git.reny.patrol.R;
import com.git.reny.patrol.entity.event.UpdateProgress;
import com.git.reny.patrol.entity.response.UpdateResult;
import com.git.reny.patrol.ui.activity.MainActivity;
import com.git.reny.patrol.utils.Constans;
import com.git.reny.patrol.utils.FileUtils;
import com.git.reny.patrol.utils.ResUtils;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.liulishuo.filedownloader.notification.BaseNotificationItem;
import com.liulishuo.filedownloader.notification.FileDownloadNotificationHelper;
import com.liulishuo.filedownloader.notification.FileDownloadNotificationListener;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import com.mcxiaoke.packer.helper.PackerNg;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

/**
 * Created by reny on 2018/2/6.
 */

public class UpdateService extends Service {

    private UpdateResult updateResult;
    private static String downLoadPath = FileUtils.getDownLoadPath("apk");

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.hasExtra(UpdateResult.class.getSimpleName())) {
            updateResult = intent.getParcelableExtra(UpdateResult.class.getSimpleName());
            if(null != updateResult) {
                startDownload();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void startDownload() {
        FileUtils.deleteFile(new File(downLoadPath));
        downLoadPath = FileUtils.getDownLoadPath("apk") + File.separator + "patrol_v" + updateResult.getVersionName()+".apk";
        FileDownloadNotificationHelper<NotificationItem> notificationHelper = new FileDownloadNotificationHelper<>();
        NotificationListener listener = new NotificationListener(notificationHelper);
        FileDownloader.getImpl().create(updateResult.getAppUrl())
                .setPath(downLoadPath)
                .setListener(listener)
                .start();
    }



    private static class NotificationListener extends FileDownloadNotificationListener {
        public NotificationListener(FileDownloadNotificationHelper notificationHelper) {
            super(notificationHelper);
        }

        @Override
        protected BaseNotificationItem create(BaseDownloadTask task) {
            return new NotificationItem(task.getId(), ResUtils.getString(R.string.app_name), "");
        }

        @Override
        public void addNotificationItem(BaseDownloadTask task) {
            super.addNotificationItem(task);
        }

        @Override
        public void destroyNotification(BaseDownloadTask task) {
            super.destroyNotification(task);
        }

        @Override
        protected boolean interceptCancel(BaseDownloadTask task, BaseNotificationItem n) {
            // in this demo, I don't want to cancel the notification, just show for the test
            // so return true
            return true;
        }

        @Override
        protected boolean disableNotification(BaseDownloadTask task) {
            return super.disableNotification(task);
        }

        //等待中
        @Override
        protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            super.pending(task, soFarBytes, totalBytes);
        }

        //进度
        @Override
        protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            super.progress(task, soFarBytes, totalBytes);
            EventBus.getDefault().post(new UpdateProgress(soFarBytes, totalBytes, downLoadPath));
        }

        @Override
        protected void completed(BaseDownloadTask task) {
            super.completed(task);
            //LogUtils.e("pathName:"+downLoadPath);
            EventBus.getDefault().post(new UpdateProgress(task.getSmallFileTotalBytes(), task.getSmallFileTotalBytes(), downLoadPath));
            installApk(new File(downLoadPath));
        }
    }

    public static class NotificationItem extends BaseNotificationItem {

        PendingIntent pendingIntent;
        NotificationCompat.Builder builder;

        private NotificationItem(int id, String title, String desc) {
            super(id, title, desc);
            Intent[] intents = new Intent[1];
            intents[0] = Intent.makeMainActivity(new ComponentName(MyApp.instance, MainActivity.class));

            this.pendingIntent = PendingIntent.getActivities(MyApp.instance, 0, intents, PendingIntent.FLAG_UPDATE_CURRENT);

            String CHANNEL_ID = MyApp.instance.getPackageName() + PackerNg.getChannel(MyApp.instance);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence CHANNEL_NAME = getTitle();
                String Description = "";//新版本下载
                NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
                mChannel.setSound(null, null);
                mChannel.setDescription(Description);
                mChannel.enableLights(false);
                mChannel.enableVibration(false);
                mChannel.setShowBadge(false);
                getManager().createNotificationChannel(mChannel);
            }

            builder = new NotificationCompat.Builder(FileDownloadHelper.getAppContext(), CHANNEL_ID);

            builder.setDefaults(Notification.DEFAULT_LIGHTS)
                    .setOngoing(true)
                    .setVibrate(new long[]{0})
                    .setSound(null)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setContentTitle(getTitle())
                    .setContentText(desc)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.mipmap.ic_launcher);

        }

        @Override
        public void show(boolean statusChanged, int status, boolean isShowProgress) {
            String desc = "";
            switch (status) {
                case FileDownloadStatus.pending:
                    //desc += " pending";
                    break;
                case FileDownloadStatus.started:
                    //desc += " started";
                    break;
                case FileDownloadStatus.progress:
                    desc = "新版本下载中 " + (int)((float)getSofar()/getTotal()*100)+"%";
                    break;
                case FileDownloadStatus.retry:
                    desc = "重新下载";
                    break;
                case FileDownloadStatus.error:
                    desc = "下载出错";
                    break;
                case FileDownloadStatus.paused:
                    desc = "下载暂停";
                    break;
                case FileDownloadStatus.completed:
                    desc = "新版本下载完成";
                    break;
                case FileDownloadStatus.warn:
                    //desc = "";
                    break;
            }

            builder.setContentTitle(getTitle()).setContentText(desc);

            if (statusChanged) {
                builder.setTicker(desc);
            }

            builder.setProgress(getTotal(), getSofar(), !isShowProgress);
            getManager().notify(getId(), builder.build());

            if(status == FileDownloadStatus.error || status == FileDownloadStatus.completed){
                try {
                    getManager().cancel(getId());
                }catch (NullPointerException e){}
            }
        }

    }


    public static void installApk(File apkFile) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(MyApp.instance, Constans.FileProviderPath, apkFile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (MyApp.instance.getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
            MyApp.instance.startActivity(intent);
        }
    }


}
