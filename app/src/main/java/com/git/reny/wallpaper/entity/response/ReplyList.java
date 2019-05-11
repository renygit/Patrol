package com.git.reny.wallpaper.entity.response;

import java.util.List;

public class ReplyList {

    private List<ReplyBean> listData;

    public ReplyList(List<ReplyBean> listData) {
        this.listData = listData;
    }

    public List<ReplyBean> getListData() {
        return listData;
    }

    public void setListData(List<ReplyBean> listData) {
        this.listData = listData;
    }
}
