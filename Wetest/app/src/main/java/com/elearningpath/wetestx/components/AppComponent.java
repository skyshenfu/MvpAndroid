package com.elearningpath.wetestx.components;

import android.content.Context;

import com.elearningpath.wetestx.models.AppModule;

import dagger.Component;

/**
 * Created by zhangty on 2017/3/8.
 */
@Component(modules = AppModule.class)
public interface AppComponent {
    Context getContexet();
}
