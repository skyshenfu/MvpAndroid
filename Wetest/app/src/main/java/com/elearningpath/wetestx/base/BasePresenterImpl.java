package com.elearningpath.wetestx.base;

import android.content.Context;
import android.util.Log;
import java.lang.ref.WeakReference;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/3/16
 * 版本号：1.0.0
 * 描述：
 */

    public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter{
    protected WeakReference<V> viewRefs;
    protected WeakReference<Context> contextRefs;
    @Override
    public void detachMvpView() {
        if (viewRefs!=null){
            viewRefs.clear();
            Log.e("123", "getView: 回收");
            viewRefs=null;
        }
        if (contextRefs!=null){
            contextRefs.clear();
            Log.e("123", "getContextView: 回收");
            contextRefs=null;
        }
    }
    protected Observable.Transformer schedulersTransformer() {
        return new Observable.Transformer() {

            @Override
            public Object call(Object observable) {
                return ((Observable)  observable).subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
    public void attachMvpView(V view,Context context) {
        viewRefs=new WeakReference<V>(view);
        contextRefs=new WeakReference<Context>(context);
    }
    public V getView(){
        if (viewRefs!=null){
            return viewRefs.get();
        }else {
            return null;
        }
    }
    public Context getContext(){
        if (viewRefs!=null){
            return contextRefs.get();
        }else {
            return null;
        }
    }
}
