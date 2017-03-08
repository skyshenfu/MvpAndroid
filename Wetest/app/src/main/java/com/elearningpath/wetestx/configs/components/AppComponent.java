package com.elearningpath.wetestx.configs.components;

import android.content.Context;

import com.elearningpath.wetestx.MainApplication;
import com.elearningpath.wetestx.configs.modules.AppModule;
import dagger.Component;

import com.elearningpath.wetestx.configs.modules.NetMoudle;
import com.elearningpath.wetestx.configs.scope.ApplicationScope;
import com.elearningpath.wetestx.utils.NetApi;


/**
 * Created by zhangty on 2017/3/8.
 */
@ApplicationScope
@Component(modules = {AppModule.class, NetMoudle.class})
public interface AppComponent {
   Context getAppContext();
   NetApi getNetApi();
   void inject(MainApplication application);
}
