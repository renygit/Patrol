package com.git.reny.wallpaper.entity.response;

import java.util.List;

/**
 * Created by reny on 2018/9/5.
 */

public class ImgListData {

    private List<ListDataBean> listData;

    public List<ListDataBean> getListData() {
        return listData;
    }

    public void setListData(List<ListDataBean> listData) {
        this.listData = listData;
    }

    public static class ListDataBean {
        /**
         * category : 风景
         * imgUrl : http://pic1.win4000.com/mobile/2018-06-04/5b14db2c0c848.jpg
         * title : 静谧山水风景图片高清手机壁纸
         */

        private String category;
        private String imgUrl;
        private String title;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
