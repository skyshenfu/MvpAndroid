package com.elearningpath.wetestx.models;

import com.elearningpath.wetestx.base.BaseModel;

import javax.inject.Inject;

/**
 * Created by zhangty on 2017/2/6.
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
