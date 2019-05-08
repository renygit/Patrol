package com.git.reny.wallpaper.entity.response;

import java.util.List;

public class CookBean {

    private IdBean _id;
    private String userId;
    private String title;
    private String content;
    private List<String> imgs;

    public CookBean(IdBean _id, String userId, String title, String content, List<String> imgs) {
        this._id = _id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.imgs = imgs;
    }

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
}
