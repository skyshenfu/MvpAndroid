package com.elearningpath.wetestx.configs.modules;

import android.content.Context;

import com.elearningpath.wetestx.configs.scope.ActivityScope;

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
public class ActivityModule {
    Context context;

    public ActivityModule(Context context) {
        this.context = context;
    }
    @Provides
    @ActivityScope
    Context provideActivityContext(Context context){
        return context;
    }
}
