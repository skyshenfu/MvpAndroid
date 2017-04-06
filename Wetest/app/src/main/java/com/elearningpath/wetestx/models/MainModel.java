package com.elearningpath.wetestx.models;

import com.elearningpath.wetestx.base.BaseModel;
import com.elearningpath.wetestx.beans.ApiResponse;
import com.elearningpath.wetestx.beans.ArticleTypeBean;
import com.elearningpath.wetestx.utils.NetApi;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/3/16
 * 版本号：1.0.0
 * 描述：
 */

public class MainModel extends BaseModel {
    @Inject
    public MainModel() {
        System.out.println("老子初始化了");
    }

    private String numberStr;
    public String getNumberStr() {
        return numberStr;
    }

    public void setNumberStr(String numberStr) {
        this.numberStr = numberStr;
    }

}
