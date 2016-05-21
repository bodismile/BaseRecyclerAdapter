package com.smile.recyclerviewdemo.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/9.
 */
public class Message implements Serializable {

    private String type;
    private String content;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
