package com.elearningpath.wetestx.beans;

/**
 * 创建者：韦小宝
 * 创建日期：16/10/31
 * 版本号：1.0.0
 * 功能说明：这实际上是个空空的接口，只用来标志反省类型
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
