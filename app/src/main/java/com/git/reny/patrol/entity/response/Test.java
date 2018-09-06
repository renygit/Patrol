package com.git.reny.patrol.entity.response;

import java.util.List;

/**
 * Created by reny on 2018/9/5.
 */

public class Test {

    private List<ListDataBean> listData;

    public List<ListDataBean> getListData() {
        return listData;
    }

    public void setListData(List<ListDataBean> listData) {
        this.listData = listData;
    }

    public static class ListDataBean {
        /**
         * description : Milk, Cheese, Pizza, Fruit, Tylenol
         * done : false
         * id : 1
         * title : Buy groceries
         */

        private String description;
        private boolean done;
        private int id;
        private String title;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean isDone() {
            return done;
        }

        public void setDone(boolean done) {
            this.done = done;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
