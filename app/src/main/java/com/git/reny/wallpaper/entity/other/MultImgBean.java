package com.git.reny.wallpaper.entity.other;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class MultImgBean implements MultiItemEntity {

    public static final int ADD = 1;
    public static final int IMG = 2;

    private Object img;

    public MultImgBean(Object img) {
        this.img = img;
    }

    public Object getImg() {
        return img;
    }

    @Override
    public int getItemType() {
        return null == img ? ADD : IMG;
    }
}
