package com.elearningpath.wetestx.utils;

import com.elearningpath.wetestx.MainApplication;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/4/6
 * 版本号：1.0.0
 * 描述：一个拦截器的类，主要用来进行拦截器及缓存处理Retrofit依赖于okhttp而且本身不提供缓存机制
 * 所以缓存处理要使用okhttp
 * 暂时弃用
 */
public class RequestInterceptorNetWithCache implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!MainApplication.getNetStatus()) {
            Logger.e("this");
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response response=chain.proceed(request);
        return response;
    }
}