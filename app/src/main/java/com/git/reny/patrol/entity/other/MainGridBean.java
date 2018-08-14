package com.git.reny.patrol.entity.other;

/**
 * Created by reny on 2018/8/13.
 */

public class MainGridBean {

    private int mipmapId;
    private String title;

    public MainGridBean(int mipmapId, String title) {
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
