package com.git.reny.wallpaper.entity.response;

import java.util.List;

public class HomeRecommendList {


    private List<ListDataBean> listData;

    public List<ListDataBean> getListData() {
        return listData;
    }

    public void setListData(List<ListDataBean> listData) {
        this.listData = listData;
    }

    public static class ListDataBean {
        /**
         * authorid : 242249274
         * authorimageid : http://pic.angelboy.cn/web/242249312.jpg
         * authorname : 小圆食记
         * collectCount : 78
         * commentCount : 0
         * description : 今天教大家做的就是咸蛋黄鸡翅，
         经过炒制的蛋黄香味更加的浓郁，
         吃起来太让人上瘾了，
         下面我们就赶紧来亲手做一下吧。
         * exclusive : 0
         * hasVideo : true
         * id : 263052077
         * imageid : http://pic.angelboy.cn/web/263052004.jpg
         * likeCount : 30
         * name : 咸蛋黄鸡翅，咸香酥脆，宝宝最爱吃，宝妈们试一下吧！
         * type :
         */

        private String authorid;
        private String authorimageid;
        private String authorname;
        private String collectCount;
        private String commentCount;
        private String description;
        private int exclusive;
        private boolean hasVideo;
        private String id;
        private String imageid;
        private String likeCount;
        private String name;
        private String type;

        public String getAuthorid() {
            return authorid;
        }

        public void setAuthorid(String authorid) {
            this.authorid = authorid;
        }

        public String getAuthorimageid() {
            return authorimageid;
        }

        public void setAuthorimageid(String authorimageid) {
            this.authorimageid = authorimageid;
        }

        public String getAuthorname() {
            return authorname;
        }

        public void setAuthorname(String authorname) {
            this.authorname = authorname;
        }

        public String getCollectCount() {
            return collectCount;
        }

        public void setCollectCount(String collectCount) {
            this.collectCount = collectCount;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getExclusive() {
            return exclusive;
        }

        public void setExclusive(int exclusive) {
            this.exclusive = exclusive;
        }

        public boolean isHasVideo() {
            return hasVideo;
        }

        public void setHasVideo(boolean hasVideo) {
            this.hasVideo = hasVideo;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImageid() {
            return imageid;
        }

        public void setImageid(String imageid) {
            this.imageid = imageid;
        }

        public String getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(String likeCount) {
            this.likeCount = likeCount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
