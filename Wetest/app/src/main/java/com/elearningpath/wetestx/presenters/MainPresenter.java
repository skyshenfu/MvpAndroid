package com.elearningpath.wetestx.presenters;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.elearningpath.wetestx.activities.DaoTestActivity;
import com.elearningpath.wetestx.activities.VideoActivity;
import com.elearningpath.wetestx.base.BasePresenterImpl;
import com.elearningpath.wetestx.base.BaseView;
import com.elearningpath.wetestx.beans.ApiResponse;
import com.elearningpath.wetestx.beans.ArticleTypeBean;
import com.elearningpath.wetestx.events.Event;
import com.elearningpath.wetestx.models.ArticleTypeModel;
import com.elearningpath.wetestx.models.MainModel;
import com.elearningpath.wetestx.stream.Config;
import com.elearningpath.wetestx.stream.SWCodecCameraStreamingActivity;
import com.elearningpath.wetestx.utils.RxBus;
import com.elearningpath.wetestx.utils.ToastUtil;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.Lazy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/3/16
 * 版本号：1.0.0
 * 描述：
 */

public class MainPresenter extends BasePresenterImpl<BaseView> {
    @Inject
    Lazy<MainModel> lazyMainModel;
    @Inject
    Lazy<ArticleTypeModel> lazyArticleTypeModel;
    @Inject
    public MainPresenter() {
    }
    public void loadData(){
        //模拟网络耗时操作
        getView().showProgress();
        DisposableObserver<Long> observer= new DisposableObserver<Long>() {
            @Override
            public void onNext(Long aLong) {
                lazyMainModel.get().setNumberStr("内容"+new Random().nextInt(200));
                getView().showData(lazyMainModel.get());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                getView().hideProgress();
//                compositeDisposable.remove(this);
                Log.e("here", "onComplete: ");
            }
        };
        compositeDisposable.add(observer);
        Observable.interval(15, TimeUnit.SECONDS)
                .take(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);


    }
    public void testRx(){
        RxBus.getInstance().post(new Event("hello"));

    }
    public void loadRemoteData(){
        getView().showProgress();
        DisposableObserver<ApiResponse<ArticleTypeBean>> observer= new DisposableObserver<ApiResponse<ArticleTypeBean>>() {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
              getView().hideProgress();
              compositeDisposable.remove(this);
            }

            @Override
            public void onNext(ApiResponse<ArticleTypeBean> mainBeanApiResponse) {
                lazyArticleTypeModel.get().setArticleTypeBean(mainBeanApiResponse.getData());
      /*          Intent intent=new Intent(getContext(), DaoTestActivity.class);
                intent.putExtra("DATA",lazyArticleTypeModel.get().getArticleTypeBean());
                getContext().startActivity(intent);*/

            }
        };
        compositeDisposable.add(observer);
        lazyArticleTypeModel.get().getArticleTypeRemote().compose(schedulersTransformer()).subscribe(observer);

    }
    public String requestStream(String appServerUrl) {
        try {
            HttpURLConnection httpConn = (HttpURLConnection) new URL(appServerUrl).openConnection();
            httpConn.setRequestMethod("POST");
            httpConn.setConnectTimeout(5000);
            httpConn.setReadTimeout(10000);
            int responseCode = httpConn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                return null;
            }

            int length = httpConn.getContentLength();
            if (length <= 0) {
                length = 16*1024;
            }
            InputStream is = httpConn.getInputStream();
            byte[] data = new byte[length];
            int read = is.read(data);
            is.close();
            if (read <= 0) {
                return null;
            }
            return new String(data, 0, read);
        } catch (Exception e) {
            ToastUtil.showToast(getContext(),"Network error!",Toast.LENGTH_SHORT);
        }
        return null;
    }
    public void startVideoPlay(){
        getContext().startActivity(new Intent(getContext(),VideoActivity.class));
    }
    public void startLiveShow(String url){
        Intent intent=new Intent(getContext(), SWCodecCameraStreamingActivity.class);
        intent.putExtra(Config.EXTRA_KEY_PUB_URL,url);
        getContext().startActivity(intent);
    }
    @Override
    public void initMvpView() {

    }
}
