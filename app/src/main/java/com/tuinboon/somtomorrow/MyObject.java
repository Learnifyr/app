package com.tuinboon.somtomorrow;

public class MyObject {

    DataClass data;

    public DataClass getData() {
        return data;
    }

    class DataClass {
        private int id;
        private String subject;
        private String dothis;
        private String mark;

        private String newest;

        public String getNews() {
            return newest;
        }
        public String getSubject() {
            return subject;
        }
        public String getDo() {
            return dothis;
        }

        public String getMark() {
            return mark;
        }
    }
}
