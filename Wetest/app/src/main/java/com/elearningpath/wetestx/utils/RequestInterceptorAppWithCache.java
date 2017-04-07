package com.elearningpath.wetestx.utils;

import android.util.Log;

import com.elearningpath.wetestx.MainApplication;

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
public class RequestInterceptorAppWithCache implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();//获取请求
        //这里就是说判读我们的网络条件，要是有网络的话我么就直接获取网络上面的数据，要是没有网络的话我么就去缓存里面取数据
        if(!MainApplication.getNetStatus()){
            String cacheControl = request.cacheControl().toString();
            request = request.newBuilder()
                    //这个的话内容有点多啊，大家记住这么写就是只从缓存取，想要了解这个东西我等下在
                    // 给大家写连接吧。大家可以去看下，获取大家去找拦截器资料的时候就可以看到这个方面的东西反正也就是缓存策略。
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            Log.d("CacheInterceptor","no network");
        }
        Response originalResponse = chain.proceed(request);
        boolean a=MainApplication.getNetStatus();
        if(a){
            //这里大家看点开源码看看.header .removeHeader做了什么操作很简答，就是的加字段和减字段的。
            String cacheControl = request.cacheControl().toString();
            Response response=originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    //这里设置的为0就是说不进行缓存，我们也可以设置缓存时间
                    .addHeader("Cache-Control", "public, max-age=" + 60*5)
                    .addHeader("X-TOKEN","")
                    .build();
            Log.e("aaa", "intercept: ");
            return response;
        }else{
            int maxTime = 4*24*60*60;
            Response response=originalResponse.newBuilder()
                    //这里的设置的是我们的没有网络的缓存时间，想设置多少就是多少。
                    .removeHeader("Pragma")
                    .addHeader("Cache-Control", "public, only-if-cached, max-stale="+maxTime)
                    .addHeader("X-TOKEN","")
                    .build();
            Log.e("bbb", "intercept: ");
            return response;
        }
    }

}