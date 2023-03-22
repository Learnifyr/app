package com.tuinboon.somtomorrow;

public class MyObject {

    DataClass data;

    String subject, dothis, mark, token, response;
    public DataClass getData() {
        return data;
    }

    class DataClass {
        private int id;
        String subject, dothis, mark, newest;

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
