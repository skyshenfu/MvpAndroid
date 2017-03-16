package com.elearningpath.wetestx.views;

import com.elearningpath.wetestx.base.BaseView;
import com.elearningpath.wetestx.models.MainModel;

/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/3/16
 * 版本号：1.0.0
 * 描述：
 */
public interface MainView extends BaseView {
    void showProgress();
    void hideProgress();
    void showData(MainModel mainModel);
}
