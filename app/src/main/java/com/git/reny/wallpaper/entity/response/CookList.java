package com.git.reny.wallpaper.entity.response;

import java.util.List;

public class CookList {

    private List<CookBean> listData;

    public CookList(List<CookBean> listData) {
        this.listData = listData;
    }

    public List<CookBean> getListData() {
        return listData;
    }

    public void setListData(List<CookBean> listData) {
        this.listData = listData;
    }
}
