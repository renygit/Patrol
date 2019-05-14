package com.git.reny.wallpaper.entity.response;

import java.util.List;

public class HomeRecommendDetails {

    /**
     * authorid : 255748306
     * authorimageid : http://pic.angelboy.cn/web/255856948.jpg
     * authorname : 群安堂Summer
     * description : 淮山鱼胶汤，女人就要“胶”养
     * exclusive : 0
     * gettime : 2019年
     * hasVideo : true
     * id : 263202896
     * imageid : http://pic.angelboy.cn/web/263202873.jpg
     * materialList : [{"contentid":"263202896","dosage":"1只","id":"6217802","mwikipediaid":"","name":" 30头北海鱼胶","ordernum":1,"version":0},{"contentid":"263202896","dosage":"3-4片","id":"6217803","mwikipediaid":"","name":"淮山药","ordernum":2,"version":0},{"contentid":"263202896","dosage":"适量","id":"6217804","mwikipediaid":"","name":"5年陈皮","ordernum":3,"version":0},{"contentid":"263202896","dosage":"适量","id":"6217805","mwikipediaid":"","name":"排骨","ordernum":4,"version":0},{"contentid":"263202896","dosage":"适量","id":"6217806","mwikipediaid":"","name":"红枣","ordernum":5,"version":0},{"contentid":"263202896","dosage":"适量","id":"6217807","mwikipediaid":"","name":"姜片","ordernum":6,"version":0},{"contentid":"263202896","dosage":"适量","id":"6217808","mwikipediaid":"","name":"盐","ordernum":7,"version":0}]
     * name : 【群安堂】淮山鱼胶汤，女人就要“胶”养
     * stepList : [{"contentid":"263202896","details":"将所有材料备齐；","id":"8103535","imageid":"http://pic.angelboy.cn/web/263202900.jpg","ordernum":1,"time":0,"version":0},{"contentid":"263202896","details":"鱼胶清洗干净后，剪成块状放入炖盅内；","id":"8103536","imageid":"http://pic.angelboy.cn/web/263202902.jpg","ordernum":2,"time":0,"version":0},{"contentid":"263202896","details":"其他材料洗净，猪骨飞水备用，依次将所有材料放入炖盅；","id":"8103537","imageid":"http://pic.angelboy.cn/web/263202903.jpg","ordernum":3,"time":0,"version":0},{"contentid":"263202896","details":"加水至没过，隔水炖3小时；","id":"8103538","imageid":"http://pic.angelboy.cn/web/263202905.jpg","ordernum":4,"time":180,"version":0},{"contentid":"263202896","details":"加入适量盐；","id":"8103539","imageid":"http://pic.angelboy.cn/web/263202907.jpg","ordernum":5,"time":0,"version":0},{"contentid":"263202896","details":"乘盘享用即可。","id":"8103540","imageid":"http://pic.angelboy.cn/web/263202908.jpg","ordernum":6,"time":0,"version":0}]
     * tags :
     * tipList : [{"contentid":"263202896","details":"【关注Summer私V：qunantang12】群安堂坚持给大家带来更多的养生美食~~\n","id":"770232","ordernum":0,"version":0}]
     * url : null
     * version : 1
     * video : {"duration":0,"height":0,"size":53824803,"url":"https://video-ecook.oss-cn-hangzhou.aliyuncs.com/8695424158a946328477583a9245bcbe.mp4","width":0}
     */

    private String authorid;
    private String authorimageid;
    private String authorname;
    private String description;
    private int exclusive;
    private String gettime;
    private boolean hasVideo;
    private String id;
    private String imageid;
    private String name;
    private String tags;
    private Object url;
    private String version;
    private VideoBean video;
    private List<MaterialListBean> materialList;
    private List<StepListBean> stepList;
    private List<TipListBean> tipList;

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

    public String getGettime() {
        return gettime;
    }

    public void setGettime(String gettime) {
        this.gettime = gettime;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public VideoBean getVideo() {
        return video;
    }

    public void setVideo(VideoBean video) {
        this.video = video;
    }

    public List<MaterialListBean> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<MaterialListBean> materialList) {
        this.materialList = materialList;
    }

    public List<StepListBean> getStepList() {
        return stepList;
    }

    public void setStepList(List<StepListBean> stepList) {
        this.stepList = stepList;
    }

    public List<TipListBean> getTipList() {
        return tipList;
    }

    public void setTipList(List<TipListBean> tipList) {
        this.tipList = tipList;
    }

    public static class VideoBean {
        /**
         * duration : 0
         * height : 0
         * size : 53824803
         * url : https://video-ecook.oss-cn-hangzhou.aliyuncs.com/8695424158a946328477583a9245bcbe.mp4
         * width : 0
         */

        private int duration;
        private int height;
        private int size;
        private String url;
        private int width;

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }

    public static class MaterialListBean {
        /**
         * contentid : 263202896
         * dosage : 1只
         * id : 6217802
         * mwikipediaid :
         * name :  30头北海鱼胶
         * ordernum : 1
         * version : 0
         */

        private String contentid;
        private String dosage;
        private String id;
        private String mwikipediaid;
        private String name;
        private int ordernum;
        private int version;

        public String getContentid() {
            return contentid;
        }

        public void setContentid(String contentid) {
            this.contentid = contentid;
        }

        public String getDosage() {
            return dosage;
        }

        public void setDosage(String dosage) {
            this.dosage = dosage;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMwikipediaid() {
            return mwikipediaid;
        }

        public void setMwikipediaid(String mwikipediaid) {
            this.mwikipediaid = mwikipediaid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOrdernum() {
            return ordernum;
        }

        public void setOrdernum(int ordernum) {
            this.ordernum = ordernum;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }

    public static class StepListBean {
        /**
         * contentid : 263202896
         * details : 将所有材料备齐；
         * id : 8103535
         * imageid : http://pic.angelboy.cn/web/263202900.jpg
         * ordernum : 1
         * time : 0
         * version : 0
         */

        private String contentid;
        private String details;
        private String id;
        private String imageid;
        private int ordernum;
        private int time;
        private int version;

        public String getContentid() {
            return contentid;
        }

        public void setContentid(String contentid) {
            this.contentid = contentid;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
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

        public int getOrdernum() {
            return ordernum;
        }

        public void setOrdernum(int ordernum) {
            this.ordernum = ordernum;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }

    public static class TipListBean {
        /**
         * contentid : 263202896
         * details : 【关注Summer私V：qunantang12】群安堂坚持给大家带来更多的养生美食~~

         * id : 770232
         * ordernum : 0
         * version : 0
         */

        private String contentid;
        private String details;
        private String id;
        private int ordernum;
        private int version;

        public String getContentid() {
            return contentid;
        }

        public void setContentid(String contentid) {
            this.contentid = contentid;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getOrdernum() {
            return ordernum;
        }

        public void setOrdernum(int ordernum) {
            this.ordernum = ordernum;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }
}
