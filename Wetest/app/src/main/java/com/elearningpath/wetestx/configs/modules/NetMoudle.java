package com.elearningpath.wetestx.configs.modules;

import com.elearningpath.wetestx.configs.scope.ApplicationScope;
import com.elearningpath.wetestx.utils.NetApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhangty on 2017/3/8.
 */

@Module
public class NetMoudle {

    @Provides
    @ApplicationScope
    public NetApi provideNetApi(){
        return NetApi.getInstance();
    };
}
