package com.git.reny.wallpaper.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by reny on 2018/2/6.
 */

public class UpdateResult implements Parcelable{

    /***
     * {
     "version": 27,
     "versionName":"4.2.0",
     "appUrl": "http://android.zyctd.net/app/%s/%s/zyctd.apk",
     "patchUrl": "",
     "describe": "更新提示\n1、测试更新\n2、新增企业优先级会员",
     "appMD5": "",
     "patchMD5": "",
     "constraint": true
     }
     */

    private Long version;
    private String versionName;
    private String appUrl;
    private String patchUrl;
    private String describe;
    private String appMD5;
    private String patchMD5;
    private boolean constraint;

    protected UpdateResult(Parcel in) {
        if (in.readByte() == 0) {
            version = null;
        } else {
            version = in.readLong();
        }
        versionName = in.readString();
        appUrl = in.readString();
        patchUrl = in.readString();
        describe = in.readString();
        appMD5 = in.readString();
        patchMD5 = in.readString();
        constraint = in.readByte() != 0;
    }

    public static final Creator<UpdateResult> CREATOR = new Creator<UpdateResult>() {
        @Override
        public UpdateResult createFromParcel(Parcel in) {
            return new UpdateResult(in);
        }

        @Override
        public UpdateResult[] newArray(int size) {
            return new UpdateResult[size];
        }
    };

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getPatchUrl() {
        return patchUrl;
    }

    public void setPatchUrl(String patchUrl) {
        this.patchUrl = patchUrl;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getAppMD5() {
        return appMD5;
    }

    public void setAppMD5(String appMD5) {
        this.appMD5 = appMD5;
    }

    public String getPatchMD5() {
        return patchMD5;
    }

    public void setPatchMD5(String patchMD5) {
        this.patchMD5 = patchMD5;
    }

    public boolean isConstraint() {
        return constraint;
    }

    public void setConstraint(boolean constraint) {
        this.constraint = constraint;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (version == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(version);
        }
        parcel.writeString(versionName);
        parcel.writeString(appUrl);
        parcel.writeString(patchUrl);
        parcel.writeString(describe);
        parcel.writeString(appMD5);
        parcel.writeString(patchMD5);
        parcel.writeByte((byte) (constraint ? 1 : 0));
    }
}
