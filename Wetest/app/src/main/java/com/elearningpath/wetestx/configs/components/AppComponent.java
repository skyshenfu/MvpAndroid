package com.elearningpath.wetestx.configs.components;

import android.content.Context;

import com.elearningpath.wetestx.MainApplication;
import com.elearningpath.wetestx.configs.modules.AppModule;
import dagger.Component;

import com.elearningpath.wetestx.configs.scope.ApplicationScope;

/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/3/16
 * 版本号：1.0.0
 * 描述：
 */
@ApplicationScope
@Component(modules = AppModule.class)
public interface AppComponent {
   Context getAppContext();
   void inject(MainApplication application);
}
