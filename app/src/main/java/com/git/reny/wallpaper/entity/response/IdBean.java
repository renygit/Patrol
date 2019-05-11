package com.git.reny.wallpaper.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

public class IdBean implements Parcelable {
    /**
     * $oid : 5cd0210e5460de07a8c6699c
     */

    private String $oid;

    protected IdBean(Parcel in) {
        $oid = in.readString();
    }

    public static final Creator<IdBean> CREATOR = new Creator<IdBean>() {
        @Override
        public IdBean createFromParcel(Parcel in) {
            return new IdBean(in);
        }

        @Override
        public IdBean[] newArray(int size) {
            return new IdBean[size];
        }
    };

    public String get$oid() {
        return $oid;
    }

    public void set$oid(String $oid) {
        this.$oid = $oid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString($oid);
    }
}
