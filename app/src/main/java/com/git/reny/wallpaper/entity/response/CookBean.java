package com.git.reny.wallpaper.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class CookBean implements Parcelable {

    private IdBean _id;
    private String userId;
    private String title;
    private String content;
    private List<String> imgs;
    private DateBean date;

    public CookBean(IdBean _id, String userId, String title, String content, List<String> imgs, DateBean date) {
        this._id = _id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.imgs = imgs;
        this.date = date;
    }

    protected CookBean(Parcel in) {
        userId = in.readString();
        title = in.readString();
        content = in.readString();
        imgs = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeStringList(imgs);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CookBean> CREATOR = new Creator<CookBean>() {
        @Override
        public CookBean createFromParcel(Parcel in) {
            return new CookBean(in);
        }

        @Override
        public CookBean[] newArray(int size) {
            return new CookBean[size];
        }
    };

    public IdBean get_id() {
        return _id;
    }

    public void set_id(IdBean _id) {
        this._id = _id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public DateBean getDate() {
        return date;
    }

    public void setDate(DateBean date) {
        this.date = date;
    }
}
