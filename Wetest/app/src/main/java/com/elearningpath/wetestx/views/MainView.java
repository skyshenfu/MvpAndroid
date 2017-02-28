package com.elearningpath.wetestx.views;

import com.elearningpath.wetestx.base.BaseView;
import com.elearningpath.wetestx.models.MainModel;

/**
 * Created by zhangty on 2017/2/6.
 * 列举了一个具体的view假设有三个视图相关的方法
 */
public interface MainView extends BaseView {
    void showProgress();
    void hideProgress();
    void showData(MainModel mainModel);
}
