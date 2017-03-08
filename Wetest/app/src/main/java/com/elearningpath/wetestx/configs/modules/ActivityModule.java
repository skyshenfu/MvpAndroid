package com.elearningpath.wetestx.configs.modules;

import android.content.Context;

import com.elearningpath.wetestx.configs.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhangty on 2017/3/8.
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
