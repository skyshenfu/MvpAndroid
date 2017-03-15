package com.elearningpath.wetestx.base;

/**
 * Created by zhangty on 2017/2/7.
 * 所有Presenter的基类
 */

public interface BasePresenter {
    //此方法为activity初始化时需要进行的业务操作的方法
    void initMvpView();
    //此方法为activity解绑View和Presenter的关系
    void detachMvpView();
}
