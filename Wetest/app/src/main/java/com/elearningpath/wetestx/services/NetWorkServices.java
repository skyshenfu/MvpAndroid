package com.elearningpath.wetestx.services;

import com.elearningpath.wetestx.beans.ApiResponse;
import com.elearningpath.wetestx.beans.DataInterface;
import com.elearningpath.wetestx.utils.NetWorkRequests;
import com.elearningpath.wetestx.utils.RequestInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 创建者：韦小宝
 * 创建日期：16/10/27
 * 版本号：1.0.0
 * 功能说明：提供网络方法的外部调用，调用者提供在主线程的显示操作
 */

public class NetWorkServices {
    private NetWorkRequests netWorkRequests;
    private Retrofit retrofit;
    private static NetWorkServices singleton = null;    //私有的、类型为Singleton自身的静态成员变量

    //构造方法被设为私有，防止外部使用new来创建对象，破坏单例
    private NetWorkServices() {
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
                .baseUrl("")
                .build();
        netWorkRequests = retrofit.create(NetWorkRequests.class);
    }

    //公有的静态方法，供外部调用来获取单例对象
    public static NetWorkServices getInstance() {
        if (singleton == null) {    //第一次调用该方法时，创建对象。
            singleton = new NetWorkServices();
        }
        return singleton;
    }

    public static void setSingleton() {
        NetWorkServices.singleton = null;
    }

    /*
    * 具体调用的方法，如果在Request里面提供了一个抽象方法，这里面就要进行具体实现
    * 以下代码可以作为模范
    * 注意他的参数均为调用者传入
    * */


/*    public void getLoginResult(Subscriber<ApiResponse<LogInBean>> subscriber, String name, String password) {
        new Inner<>(subscriber).getThreadTurn(netWorkRequests.getLoginResult(name, password));
    }*/

    //一个内部类，抽象了线程调度逻辑
    private class Inner<T extends DataInterface>{
        private Subscriber<ApiResponse<T>> subscriber;

        public Inner(Subscriber<ApiResponse<T>> subscriber) {
            this.subscriber = subscriber;
        }
        public void getThreadTurn(Observable<ApiResponse<T>> observable){
            observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber);
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
        }
    }
}
