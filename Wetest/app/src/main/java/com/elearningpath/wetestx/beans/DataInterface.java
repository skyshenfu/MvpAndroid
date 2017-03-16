package com.elearningpath.wetestx.beans;

/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/3/16
 * 版本号：1.0.0
 * 描述：
 */

public abstract class DataInterface {
    protected int error;
    protected String message;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataInterface() {
    }

    public DataInterface(int error, String message) {
        this.error = error;
        this.message = message;
    }
}
