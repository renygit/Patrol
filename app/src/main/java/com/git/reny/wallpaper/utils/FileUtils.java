package com.git.reny.wallpaper.utils;

import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;

import com.zyctd.mvplib.utils.AppUtils;

import java.io.File;

public class FileUtils {

	public static final String apkDownLoadFileName = "apk";

	/**
	 * 检查设备是否存在SDCard的工具方法
	 */
	public static boolean hasSdcard() {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			// 有存储的SDCard
			return true;
		} else {
			return false;
		}
	}

	//获取一个要下载的path
	public static String getDownLoadPath(String fileName) {
		if(TextUtils.isEmpty(fileName)){
			fileName = "cache";
		}
		String cachePath = null;
		if (hasSdcard()) {
			cachePath = Environment.getExternalStorageDirectory().getPath() +
					File.separator + AppUtils.self().getContext().getPackageName() +
					File.separator + fileName;
		} else {
			cachePath = Environment.getDataDirectory().getPath() +
					File.separator + AppUtils.self().getContext().getPackageName() +
					File.separator + fileName;
		}
		File file = new File(cachePath);
		if (!file.exists())
			file.mkdirs();// 创建文件夹
		return cachePath;
	}

	
	/***
	 * 根据路径 删除文件(夹)
	 * @param file
	 */
	public static boolean deleteFile(File file)
	{
		if (!file.exists()) { 
            return false;
        } else {  
            if (file.isFile()) {  
                file.delete();  
                return true;  
            }  
            if (file.isDirectory()) {  
                File[] childFile = file.listFiles();
                if (childFile == null || childFile.length == 0) {  
                    file.delete();  
                    return true;  
                }  
                for (File f : childFile) {
                    deleteFile(f);
                }  
                file.delete();
            }
            return true;
        }  
	}

	//刷新图库等文件系统  用来保存图片后调用
	public static void notifyFileSystemChanged(String path) {
		if (TextUtils.isEmpty(path))
			return;
		try {
			final File f = new File(path);
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { //添加此判断，判断SDK版本是不是4.4或者高于4.4
				String[] paths = new String[]{path};
				MediaScannerConnection.scanFile(AppUtils.self().getContext(), paths, null, null);
			} else {
				final Intent intent;
				if (f.isDirectory()) {
					intent = new Intent(Intent.ACTION_MEDIA_MOUNTED);
					intent.setClassName("com.android.providers.media", "com.android.providers.media.MediaScannerReceiver");
					intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
					//Log.v(LOG_TAG, "directory changed, send broadcast:" + intent.toString());
				} else {
					intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
					intent.setData(Uri.fromFile(f));
					//Log.v(LOG_TAG, "file changed, send broadcast:" + intent.toString());
				}
				AppUtils.self().getContext().sendBroadcast(intent);
			}
		}catch (Exception e){}
	}


}
