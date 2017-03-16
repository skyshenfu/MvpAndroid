package com.elearningpath.wetestx.configs.modules;

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
    private final BaseView mView;

    public MainModule(BaseView mView) {
        this.mView = mView;
    }
    @Provides
    @ActivityScope
    BaseView provideMainView(){
        return mView;
    }
}
