package com.elearningpath.wetestx.configs.modules;

import android.content.Context;

import com.elearningpath.wetestx.base.BaseView;
import com.elearningpath.wetestx.configs.scope.ActivityScope;
import com.elearningpath.wetestx.views.MainView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/3/16
 * 版本号：1.0.0
 * 描述：
 */
@Module
public class MainModule {
    private  BaseView mView;
    private  Context context;

    public MainModule(BaseView mView,Context context) {
        this.mView = mView;
        this.context=context;
    }
    @Provides
    @ActivityScope
    BaseView provideMainView(){
        return mView;
    }
    @Provides
    @ActivityScope
    Context provideContext(){
        return  context;
    }
}
