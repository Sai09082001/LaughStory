package com.example.laughstory.entity;

import java.io.Serializable;

public class Story implements Serializable {
    private final String name;
    private final String content;
    private boolean isSelected;

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public Story(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

