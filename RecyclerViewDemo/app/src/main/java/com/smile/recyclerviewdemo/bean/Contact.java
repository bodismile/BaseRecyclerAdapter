package com.smile.recyclerviewdemo.bean;

import java.io.Serializable;

/**联系人
 * @author smile
 * @date 2016-04-26
 */
public class Contact implements Serializable {

    private String avator;
    private String username;

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
