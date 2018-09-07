package com.git.reny.wallpaper.entity.event;

/**
 * Created by reny on 2018/2/7.
 */

public class UpdateProgress {

    private int soFarBytes;//下载了多少
    private int totalBytes;//总共多少
    private String downLoadPath;//下载路径

    public UpdateProgress(int soFarBytes, int totalBytes, String downLoadPath) {
        this.soFarBytes = soFarBytes;
        this.totalBytes = totalBytes;
        this.downLoadPath = downLoadPath;
    }

    public int getSoFarBytes() {
        return soFarBytes;
    }

    public int getTotalBytes() {
        return totalBytes;
    }

    public String getDownLoadPath() {
        return downLoadPath;
    }
}
