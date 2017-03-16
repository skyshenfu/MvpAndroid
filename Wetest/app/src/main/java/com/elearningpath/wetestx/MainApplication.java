package com.elearningpath.wetestx;

import android.app.Application;
import android.util.Log;

import com.elearningpath.wetestx.configs.components.DaggerAppComponent;
import com.elearningpath.wetestx.configs.modules.AppModule;
import com.elearningpath.wetestx.utils.Constant;
import com.elearningpath.wetestx.utils.NetApi;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.qiniu.pili.droid.streaming.StreamingEnv;
import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;


/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/3/16
 * 版本号：1.0.0
 * 描述：
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
     DaggerAppComponent.builder()
                     .appModule(new AppModule(this))
                     .build().inject(this);
        initThirdPartyFrameWork();
    }

    /**
     * 第三方框架的的初始化方法
     */
    private void initThirdPartyFrameWork() {
        //LeakCanary一个脱了低级趣味的若引用框架
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        //Logger一个简练帅气的Logger组件
        if (Constant.ISDEBUGMODE){
            Logger.init(Constant.LOGGERTAG);
        }else {
            Logger.init().logLevel(LogLevel.NONE);
        }
        //七牛云视频&没有更坏的地，只有累死的牛
        StreamingEnv.init(getApplicationContext());

    }

}
