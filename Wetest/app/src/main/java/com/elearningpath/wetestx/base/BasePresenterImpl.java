package com.elearningpath.wetestx.base;

import java.lang.ref.WeakReference;

/**
 * Created by zhangty on 2017/2/7.
 * 抽象类,封装了解绑View和Presenter之间的具体逻辑
 */

    public abstract class BasePresenterImpl<T extends BaseView> implements BasePresenter{
    protected WeakReference<T> viewRef;
    protected T view;
    public BasePresenterImpl(T view) {
        this.view = view;
    }

    @Override
    public void attachMvpView() {
        viewRef = new WeakReference<>(view);
    }

    @Override
    public void detachMvpView() {
        if (viewRef !=null){
            viewRef.clear();
            view=null;
        }
    }

}
