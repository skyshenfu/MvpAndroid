package com.elearningpath.wetestx.configs.modules;

import android.content.Context;

import com.elearningpath.wetestx.configs.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhangty on 2017/3/8.
 */
@Module
public class AppModule {
    Context mapplicationContext;

    public AppModule(Context mapplicationContext) {
        this.mapplicationContext = mapplicationContext;
    }

    @Provides
    @ApplicationScope
    Context providesApplication(){
        return mapplicationContext;
    }
}
