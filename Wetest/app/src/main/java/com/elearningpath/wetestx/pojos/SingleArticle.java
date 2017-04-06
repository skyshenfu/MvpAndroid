package com.elearningpath.wetestx.pojos;

import java.io.Serializable;

/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/4/5
 * 版本：1.0.0
 * 描述：
 */

public class SingleArticle implements Serializable{
    private long classid;
    private int displayorder;
    private String name;

    public SingleArticle() {
    }

    public long getClassid() {
        return classid;
    }

    public void setClassid(long classid) {
        this.classid = classid;
    }

    public int getDisplayorder() {
        return displayorder;
    }

    public void setDisplayorder(int displayorder) {
        this.displayorder = displayorder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
