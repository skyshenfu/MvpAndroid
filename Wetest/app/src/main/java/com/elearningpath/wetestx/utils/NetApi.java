package com.elearningpath.wetestx.utils;

import android.util.Log;

import com.elearningpath.wetestx.beans.ApiResponse;
import com.elearningpath.wetestx.beans.ArticleTypeBean;

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
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/3/16
 * 版本号：1.0.0
 * 描述：
 */
public class NetApi {
    private NetWorkRequests netWorkRequests;
    private Retrofit retrofit;
    private static NetApi singleton = null;    //私有的、类型为Singleton自身的静态成员变量

    //构造方法被设为私有，防止外部使用new来创建对象，破坏单例
    private NetApi() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.addInterceptor(new RequestInterceptor())
                        .connectTimeout(8, TimeUnit.SECONDS)
                        .readTimeout(8, TimeUnit.SECONDS)
                        .writeTimeout(8, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constant.BASEURL)
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
 public Observable<ApiResponse<ArticleTypeBean>> getArticleResult() {
        return netWorkRequests.getArticleResult();
    }
}
