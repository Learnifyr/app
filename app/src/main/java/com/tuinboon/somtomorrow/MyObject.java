package com.tuinboon.somtomorrow;

public class MyObject {

    DataClass data;
    private int id;
    private String subject;
    private String dothis;
    private String mark;

    public String getSubject() {
        return subject;
    }
    public String getDo() {
        return dothis;
    }

    public String getMark() {
        return mark;
    }
    public DataClass getData() {
        return data;
    }
    class DataClass{
        String first_name, last_name, id, email, avatar;

        public String getFirst_name() {
            return first_name;
        }
    }
}

