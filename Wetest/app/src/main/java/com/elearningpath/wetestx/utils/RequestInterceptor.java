package com.elearningpath.wetestx.utils;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

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