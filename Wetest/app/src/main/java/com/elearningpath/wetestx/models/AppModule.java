package com.elearningpath.wetestx.models;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

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
    @Singleton
    Context providesApplication(){
        return mapplicationContext;
    }
}
