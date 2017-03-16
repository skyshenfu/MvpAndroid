package com.elearningpath.wetestx.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.elearningpath.wetestx.beans.ApiResponse;
import com.elearningpath.wetestx.beans.DataInterface;

/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/3/16
 * 版本号：1.0.0
 * 描述：
 */

public abstract class NetWorkResponseUtil<T extends DataInterface>{
    private ApiResponse<T> response;
    private Context context;
    private boolean isRequiredToken=false;
    public static final String RELOGINTAG="请重新登录";

    /**
     *
     * @param response ApiResponse类型的返回结果
     * @param context
     * @param isRequiredToken
     */
    public NetWorkResponseUtil(ApiResponse<T> response, Context context, boolean isRequiredToken) {
        this.response = response;
        this.context = context;
        this.isRequiredToken = isRequiredToken;
    }

    public NetWorkResponseUtil(ApiResponse<T> response, Context context) {
        this.response = response;
        this.context = context;
    }

    public  void dealWithResult(){
        if (response.getApiError()==0){
            if (response.getData().getError()==0){
                doInRightResponse();
            }else if (response.getData().getError()==403&&isRequiredToken){
                Toast.makeText(context, RELOGINTAG, Toast.LENGTH_SHORT).show();
            }else if (response.getData().getError()==520){
                Toast.makeText(context, RELOGINTAG, Toast.LENGTH_SHORT).show();
            }else {
                errorDo();
                Toast.makeText(context, response.getData().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }else {
            apiErrorDo();
            Toast.makeText(context,response.getMsg(), Toast.LENGTH_SHORT).show();
        }
    };
    public abstract void doInRightResponse();
    public void errorDo(){

    }
    public void apiErrorDo(){

    }
}
