package com.elearningpath.wetestx;

import android.app.Application;
import android.util.Log;

import com.elearningpath.wetestx.configs.components.DaggerAppComponent;
import com.elearningpath.wetestx.configs.modules.AppModule;
import com.elearningpath.wetestx.utils.NetApi;
import javax.inject.Inject;


/**
 * Created by zhangty on 2017/3/6.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
     DaggerAppComponent.builder()
                     .appModule(new AppModule(this))
                     .build().inject(this);
    }

}
