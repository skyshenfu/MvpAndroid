package com.elearningpath.wetestx.base;

/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/3/16
 * 版本号：1.0.0
 * 描述：
 */
public interface BasePresenter {
    //此方法为activity初始化时需要进行的业务操作的方法
    void initMvpView();
    //此方法为activity解绑View和Presenter的关系
    void detachMvpView();
}
