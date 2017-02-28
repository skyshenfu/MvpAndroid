package com.elearningpath.wetestx.beans;

/**
 * 创建者：韦小宝
 * 创建日期：16/10/31
 * 版本号：1.0.0
 * 功能说明：
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
