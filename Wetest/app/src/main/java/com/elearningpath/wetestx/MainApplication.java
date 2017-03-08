package com.elearningpath.wetestx;

import android.app.Application;

import com.elearningpath.wetestx.configs.components.AppComponent;
import com.elearningpath.wetestx.configs.components.DaggerAppComponent;
import com.elearningpath.wetestx.configs.modules.AppModule;
import com.elearningpath.wetestx.configs.modules.NetMoudle;
import com.elearningpath.wetestx.utils.NetApi;


/**
 * Created by zhangty on 2017/3/6.
 */

public class MainApplication extends Application {
    private NetApi api;
    @Override
    public void onCreate() {
        super.onCreate();
     DaggerAppComponent.builder()
                     .appModule(new AppModule(this))
                     .netMoudle(new NetMoudle())
                     .build().inject(this);
    }

}
