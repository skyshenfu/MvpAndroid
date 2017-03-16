package com.elearningpath.wetestx.base;

import android.content.Context;
import android.util.Log;

/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/3/16
 * 版本号：1.0.0
 * 描述：
 */

    public abstract class BasePresenterImpl<T extends BaseView> implements BasePresenter{
    protected T view;
    protected Context context;
    public BasePresenterImpl(T view,Context context) {
        this.view = view;
        this.context=context;
    }

    @Override
    public void detachMvpView() {
        if (view !=null){
            Log.e("TAG", "detachMvpView: 1");
            view=null;
        }
        if (context !=null){
            Log.e("TAG", "detachMvpView: 1");
            context=null;
        }
    }

}
