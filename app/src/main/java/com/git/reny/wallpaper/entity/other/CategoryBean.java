package com.git.reny.wallpaper.entity.other;

/**
 * Created by reny on 2018/8/13.
 */

public class CategoryBean {

    private int mipmapId;
    private String title;

    public CategoryBean(int mipmapId, String title) {
        this.mipmapId = mipmapId;
        this.title = title;
    }

    public int getMipmapId() {
        return mipmapId;
    }

    public void setMipmapId(int mipmapId) {
        this.mipmapId = mipmapId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
