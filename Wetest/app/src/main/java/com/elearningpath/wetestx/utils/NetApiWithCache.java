package com.elearningpath.wetestx.utils;

import android.content.Context;
import android.util.Log;

import com.elearningpath.wetestx.MainApplication;
import com.elearningpath.wetestx.beans.ApiResponse;
import com.elearningpath.wetestx.beans.ArticleTypeBean;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.internal.cache.CacheInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/3/16
 * 版本号：1.0.0
 * 描述：暂时弃用
 */
public class NetApiWithCache {
    private NetWorkRequests netWorkRequests;
    private Retrofit retrofit;
    private static NetApiWithCache singleton = null;    //私有的、类型为Singleton自身的静态成员变量

    //构造方法被设为私有，防止外部使用new来创建对象，破坏单例

    /**
     * addNetworkInterceptor添加的是网络拦截器，他会在在request和resposne是分别被调用一次
     * ，addinterceptor添加的是aplication(应用拦截器)拦截器，他只会在response被调用一次
     */
    private NetApiWithCache() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        File cacheFile=new File(Constant.CACAHEDIR,"cacheData");
        Cache cache=new Cache(cacheFile,Constant.CACHEMAXSIZE);
        httpClientBuilder
                .connectTimeout(8, TimeUnit.SECONDS)
                .readTimeout(8, TimeUnit.SECONDS)
                .writeTimeout(8, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .addInterceptor(new RequestInterceptorAppWithCache())
                .addNetworkInterceptor(new RequestInterceptorNetWithCache())
                .cache(cache);
        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constant.BASEURL)
                .build();
        netWorkRequests = retrofit.create(NetWorkRequests.class);
    }

    //公有的静态方法，供外部调用来获取单例对象
    public static NetApiWithCache getInstance() {
        if (singleton == null) {    //第一次调用该方法时，创建对象。
            singleton = new NetApiWithCache();
            Log.e("here", "getInstance: "+"初始化" );
        }
        return singleton;
    }

    public static void setSingleton() {
        NetApiWithCache.singleton = null;
    }
/*    private final Interceptor REWRITE_RESPONSE_INTERCEPTOR = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            okhttp3.Response originalResponse = chain.proceed(chain.request());
            String cacheControl = originalResponse.header("Cache-Control");
            if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
                    cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")) {
                Log.e( "intercept: ","hhhhh" );
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, max-age=" + 5000)
                        .build();
            } else {
                Log.e( "intercept: ","ggggg" );
                return originalResponse;
            }
        }
    };

    private final Interceptor REWRITE_RESPONSE_INTERCEPTOR_OFFLINE = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!MainApplication.getNetStatus()) {
                Log.e( "intercept: ","fffffff" );
                request = request.newBuilder()
                        .header("Cache-Control", "public, only-if-cached")
                        .build();
            }
            return chain.proceed(request);
        }
    };*/

    /*
    * 具体调用的方法，如果在Request里面提供了一个抽象方法，这里面就要进行具体实现
    * 以下代码可以作为模范
    * 注意他的参数均为调用者传入
    * */


/*    public void getLoginResult(Subscriber<ApiResponse<LogInBean>> subscriber, String name, String password) {
        netWorkRequests.getLoginResult(name, password).compose(schedulersTransformer()).subscribe(subscriber);
    }*/
 public Observable<ApiResponse<ArticleTypeBean>> getArticleResult() {
        return netWorkRequests.getArticleResult();
    }
}
