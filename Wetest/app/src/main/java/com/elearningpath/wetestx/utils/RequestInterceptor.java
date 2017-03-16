package com.elearningpath.wetestx.utils;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/3/16
 * 版本号：1.0.0
 * 描述：
 */
public class RequestInterceptor implements Interceptor {
    private Context context;
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
            .header("X-TOKEN", "")
            .method(original.method(), original.body())
            .build();
        return chain.proceed(request);
    }
}