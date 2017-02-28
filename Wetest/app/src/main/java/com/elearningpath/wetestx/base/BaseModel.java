package com.elearningpath.wetestx.base;

/*
 *Created by zhangty on 2017/2/7.
 *所有和试图相关Model的父类，要抽取共性
 */

public class BaseModel {
    //假设都需要title这个东西，实际开发中例如每个页面Toolbar上方的文字
    protected String title;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
