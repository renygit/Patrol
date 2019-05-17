package com.git.reny.wallpaper.entity.other;

public class QiniuImgs {

    private String imgPath;
    private String key;
    private String token;

    public QiniuImgs(String imgPath, String key, String token) {
        this.imgPath = imgPath;
        this.key = key;
        this.token = token;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
