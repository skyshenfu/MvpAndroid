package com.elearningpath.wetestx.utils;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 创建者：韦小宝
 * 创建日期：16/10/27
 * 版本号：1.0.0
 * 功能说明：提供网络方法的外部调用，调用者提供在主线程的显示操作
 */

public class NetApi {
    private NetWorkRequests netWorkRequests;
    private Retrofit retrofit;
    private static NetApi singleton = null;    //私有的、类型为Singleton自身的静态成员变量

    //构造方法被设为私有，防止外部使用new来创建对象，破坏单例
    private NetApi() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.addInterceptor(new RequestInterceptor());
        httpClientBuilder.connectTimeout(10, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(10, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(10, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://wwww.baidu.com")
                .build();
        netWorkRequests = retrofit.create(NetWorkRequests.class);
    }

    //公有的静态方法，供外部调用来获取单例对象
    public static NetApi getInstance() {
        if (singleton == null) {    //第一次调用该方法时，创建对象。
            singleton = new NetApi();
            Log.e("here", "getInstance: "+"初始化" );
        }
        return singleton;
    }

    public static void setSingleton() {
        NetApi.singleton = null;
    }

    /*
    * 具体调用的方法，如果在Request里面提供了一个抽象方法，这里面就要进行具体实现
    * 以下代码可以作为模范
    * 注意他的参数均为调用者传入
    * */


/*    public void getLoginResult(Subscriber<ApiResponse<LogInBean>> subscriber, String name, String password) {
        netWorkRequests.getLoginResult(name, password).compose(schedulersTransformer()).subscribe(subscriber);
    }*/

    //一个内部类，抽象了线程调度逻辑
    Observable.Transformer schedulersTransformer() {
        return new Observable.Transformer() {

            @Override
            public Object call(Object observable) {
                return ((Observable)  observable).subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
