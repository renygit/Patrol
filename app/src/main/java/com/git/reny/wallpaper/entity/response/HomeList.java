package com.git.reny.wallpaper.entity.response;

import java.util.List;

public class HomeList {

    private List<ListDataBean> listData;

    public List<ListDataBean> getListData() {
        return listData;
    }

    public void setListData(List<ListDataBean> listData) {
        this.listData = listData;
    }

    public static class ListDataBean {
        /**
         * author : 美食小编
         * category : 家常菜
         * imgUrl : https://s1.st.meishij.net/r/41/203/113291/a113291_155082504950946.jpg
         * linkUrl : https://m.meishij.net/zuofa/kejianiangdoufu_30.html
         * mark : 8 评论 432 人气
         * tag : 补钙
         * time : 4步 / 大概30分钟
         * title : 客家酿豆腐
         * way : 烧 / 家常味
         */

        private IdBean _id;
        private String author;
        private String category;
        private String imgUrl;
        private String linkUrl;
        private String mark;
        private String tag;
        private String time;
        private String title;
        private String way;

        public IdBean get_id() {
            return _id;
        }

        public void set_id(IdBean _id) {
            this._id = _id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getLinkUrl() {
            return linkUrl;
        }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getWay() {
            return way;
        }

        public void setWay(String way) {
            this.way = way;
        }
    }
}
