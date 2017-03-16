package com.elearningpath.wetestx.beans;

/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/3/16
 * 版本号：1.0.0
 * 描述：
 */

public class ApiResponse<T extends DataInterface> {
    public int apiError;
    public String msg;
    public T data;

    public ApiResponse(int apiError, String msg, T data) {
        this.apiError = apiError;
        this.msg = msg;
        this.data = data;
    }

    public ApiResponse() {
    }
    public int getApiError() {
        return apiError;
    }

    public void setApiError(int apiError) {
        this.apiError = apiError;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
