package com.git.reny.wallpaper.entity.response;

import java.util.List;

public class ReplyBean {

    /**
     * _id : {"$oid":"5cd67acc5460de02504f6fbd"}
     * cookId : 5cd42cc05460de0a20809acf
     * date : {"$date":1557560012657}
     * remarkContent : 回复测试，求成功
     * userInfo : [{"_id":{"$oid":"5cd0210e5460de07a8c6699c"},"name":"ry"}]
     */

    private IdBean _id;
    private String cookId;
    private DateBean date;
    private String remarkContent;
    private List<UserData> userInfo;

    public IdBean get_id() {
        return _id;
    }

    public void set_id(IdBean _id) {
        this._id = _id;
    }

    public String getCookId() {
        return cookId;
    }

    public void setCookId(String cookId) {
        this.cookId = cookId;
    }

    public DateBean getDate() {
        return date;
    }

    public void setDate(DateBean date) {
        this.date = date;
    }

    public String getRemarkContent() {
        return remarkContent;
    }

    public void setRemarkContent(String remarkContent) {
        this.remarkContent = remarkContent;
    }

    public List<UserData> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(List<UserData> userInfo) {
        this.userInfo = userInfo;
    }

}
