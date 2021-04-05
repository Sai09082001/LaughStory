package com.example.laughstory.entity;

public class Topic {
    public final String idName;
    public final String title;

    public Topic(String idName,String title) {
        this.idName=idName;
        this.title=title;
    }

    public String getIdName() {
        return idName;
    }

    public String getTitle() {
        return title;
    }
}
