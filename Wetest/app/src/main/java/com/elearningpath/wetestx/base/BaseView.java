package com.elearningpath.wetestx.base;

import android.support.annotation.Nullable;

/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/3/16
 * 版本号：1.0.0
 * 描述：
 */
public interface BaseView {
    void showProgress();
    void hideProgress();
    void showData(@Nullable BaseModel model);
}
