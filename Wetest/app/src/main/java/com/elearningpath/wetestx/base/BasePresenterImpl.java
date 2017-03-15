package com.elearningpath.wetestx.base;

import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Created by zhangty on 2017/2/7.
 * 抽象类,封装了解绑View和Presenter之间的具体逻辑
 */

    public abstract class BasePresenterImpl<T extends BaseView> implements BasePresenter{
    protected T view;
    public BasePresenterImpl(T view) {
        this.view = view;
    }

    @Override
    public void detachMvpView() {
        if (view !=null){
            Log.e("TAG", "detachMvpView: 1");
            view=null;
        }else {
            Log.e("TAG", "detachMvpView: 2");
        }
    }

}
