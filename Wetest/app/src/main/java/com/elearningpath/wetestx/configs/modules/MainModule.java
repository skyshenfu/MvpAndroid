package com.elearningpath.wetestx.configs.modules;

import com.elearningpath.wetestx.configs.scope.ActivityScope;
import com.elearningpath.wetestx.views.MainView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhangty on 2017/3/9.
 */
@Module
public class MainModule {
    private final MainView mView;

    public MainModule(MainView mView) {
        this.mView = mView;
    }
    @Provides
    @ActivityScope
    MainView provideMainView(){
        return mView;
    }
}
