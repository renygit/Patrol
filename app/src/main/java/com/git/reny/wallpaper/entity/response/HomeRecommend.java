package com.git.reny.wallpaper.entity.response;

import java.util.List;

public class HomeRecommend {

    /**
     * bannerlist : [{"banner_link":"http://h5.izhangchu.com/course/view.html?&type=1&series_id=230&food_course_id=7805","banner_picture":"http://img.szzhangchu.com/1544666809772_9007944840.jpg","banner_title":"#一日渔#青椒炒素肉","is_link":"1"},{"banner_link":"http://h5.izhangchu.com/course/view.html?&type=1&series_id=169&food_course_id=7760","banner_picture":"http://img.szzhangchu.com/1542278299598_7607008888.jpg","banner_title":"#顿顿香面食#鸡蛋灌饼","is_link":"1"},{"banner_link":"http://h5.izhangchu.com/course/view.html?&type=1&series_id=225&food_course_id=7792","banner_picture":"http://img.szzhangchu.com/1543906350473_8536329249.jpg","banner_title":"#喵小白#腐乳蒸鸡","is_link":"1"}]
     * recommend_text : “你们去征服世界，我只想征服一个人的胃和心
     * video_info : [{"author":{"avatar_url":"https://s1.st.meishij.net/user/41/203/ns113291_86576.jpg","id":"113291","nickname":"美食小编"},"date":"04.21","id":"1925187","recommend_title":"今日精选","title":"炸鱼薯条","video":{"height":368,"img":"https://s1.st.meishij.net/p2/20190415/20190415134808_534.jpg","img_test":"https://s1.st.meishij.net/r/41/203/113291/s113291_155487667473839.jpg?imageMogr2/blur/50x50","vendor_video":"http://gslb.miaopai.com/stream/Ve-ijpUlegwE9UZmaujaaNTj5YlytiTF8DXbRg__.mp4?vend=miaopai&","width":240}},{"author":{"avatar_url":"https://s1.st.meishij.net/user/41/203/ns113291_86576.jpg","id":"113291","nickname":"美食小编"},"date":"04.21","id":"1925297","recommend_title":"今日精选","title":"蒸肉丸","video":{"height":368,"img":"https://s1.st.meishij.net/p2/20190415/20190415134813_743.jpg","img_test":"https://s1.st.meishij.net/r/41/203/113291/s113291_155495440996479.jpg?imageMogr2/blur/50x50","vendor_video":"http://gslb.miaopai.com/stream/Dfg8gY5kBWJ3iKkDdOnYkO3YPmcbPBDmJxll4A__.mp4?vend=miaopai&","width":240}},{"author":{"avatar_url":"https://s1.st.meishij.net/user/41/203/ns113291_86576.jpg","id":"113291","nickname":"美食小编"},"date":"04.21","id":"1925448","recommend_title":"今日精选","title":"泡菜汤","video":{"height":368,"img":"https://s1.st.meishij.net/p2/20190415/20190415134818_798.jpg","img_test":"https://s1.st.meishij.net/r/41/203/113291/s113291_155506035719756.jpg?imageMogr2/blur/50x50","vendor_video":"http://gslb.miaopai.com/stream/klCHhWaiaJDyHRIX4ZWJS4e1FQyUNN0POWa7cQ__.mp4?vend=miaopai&","width":240}}]
     */

    private String recommend_text;
    private List<BannerlistBean> bannerlist;
    private List<VideoInfoBean> video_info;

    public String getRecommend_text() {
        return recommend_text;
    }

    public void setRecommend_text(String recommend_text) {
        this.recommend_text = recommend_text;
    }

    public List<BannerlistBean> getBannerlist() {
        return bannerlist;
    }

    public void setBannerlist(List<BannerlistBean> bannerlist) {
        this.bannerlist = bannerlist;
    }

    public List<VideoInfoBean> getVideo_info() {
        return video_info;
    }

    public void setVideo_info(List<VideoInfoBean> video_info) {
        this.video_info = video_info;
    }

    public static class BannerlistBean {
        /**
         * banner_link : http://h5.izhangchu.com/course/view.html?&type=1&series_id=230&food_course_id=7805
         * banner_picture : http://img.szzhangchu.com/1544666809772_9007944840.jpg
         * banner_title : #一日渔#青椒炒素肉
         * is_link : 1
         */

        private String banner_link;
        private String banner_picture;
        private String banner_title;
        private String is_link;

        public String getBanner_link() {
            return banner_link;
        }

        public void setBanner_link(String banner_link) {
            this.banner_link = banner_link;
        }

        public String getBanner_picture() {
            return banner_picture;
        }

        public void setBanner_picture(String banner_picture) {
            this.banner_picture = banner_picture;
        }

        public String getBanner_title() {
            return banner_title;
        }

        public void setBanner_title(String banner_title) {
            this.banner_title = banner_title;
        }

        public String getIs_link() {
            return is_link;
        }

        public void setIs_link(String is_link) {
            this.is_link = is_link;
        }
    }

    public static class VideoInfoBean {
        /**
         * author : {"avatar_url":"https://s1.st.meishij.net/user/41/203/ns113291_86576.jpg","id":"113291","nickname":"美食小编"}
         * date : 04.21
         * id : 1925187
         * recommend_title : 今日精选
         * title : 炸鱼薯条
         * video : {"height":368,"img":"https://s1.st.meishij.net/p2/20190415/20190415134808_534.jpg","img_test":"https://s1.st.meishij.net/r/41/203/113291/s113291_155487667473839.jpg?imageMogr2/blur/50x50","vendor_video":"http://gslb.miaopai.com/stream/Ve-ijpUlegwE9UZmaujaaNTj5YlytiTF8DXbRg__.mp4?vend=miaopai&","width":240}
         */

        private AuthorBean author;
        private String date;
        private String id;
        private String recommend_title;
        private String title;
        private VideoBean video;

        public AuthorBean getAuthor() {
            return author;
        }

        public void setAuthor(AuthorBean author) {
            this.author = author;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRecommend_title() {
            return recommend_title;
        }

        public void setRecommend_title(String recommend_title) {
            this.recommend_title = recommend_title;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public VideoBean getVideo() {
            return video;
        }

        public void setVideo(VideoBean video) {
            this.video = video;
        }

        public static class AuthorBean {
            /**
             * avatar_url : https://s1.st.meishij.net/user/41/203/ns113291_86576.jpg
             * id : 113291
             * nickname : 美食小编
             */

            private String avatar_url;
            private String id;
            private String nickname;

            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }
        }

        public static class VideoBean {
            /**
             * height : 368
             * img : https://s1.st.meishij.net/p2/20190415/20190415134808_534.jpg
             * img_test : https://s1.st.meishij.net/r/41/203/113291/s113291_155487667473839.jpg?imageMogr2/blur/50x50
             * vendor_video : http://gslb.miaopai.com/stream/Ve-ijpUlegwE9UZmaujaaNTj5YlytiTF8DXbRg__.mp4?vend=miaopai&
             * width : 240
             */

            private int height;
            private String img;
            private String img_test;
            private String vendor_video;
            private int width;

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getImg_test() {
                return img_test;
            }

            public void setImg_test(String img_test) {
                this.img_test = img_test;
            }

            public String getVendor_video() {
                return vendor_video;
            }

            public void setVendor_video(String vendor_video) {
                this.vendor_video = vendor_video;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }
        }
    }
}
