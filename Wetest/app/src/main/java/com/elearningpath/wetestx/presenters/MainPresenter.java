package com.elearningpath.wetestx.presenters;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.elearningpath.wetestx.activities.VideoActivity;
import com.elearningpath.wetestx.base.BasePresenterImpl;
import com.elearningpath.wetestx.base.BaseView;
import com.elearningpath.wetestx.models.MainModel;
import com.elearningpath.wetestx.stream.Config;
import com.elearningpath.wetestx.stream.SWCodecCameraStreamingActivity;
import com.elearningpath.wetestx.utils.RetrofitCancel;
import com.elearningpath.wetestx.utils.ToastUtil;
import com.orhanobut.logger.Logger;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.Lazy;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
    public MainPresenter() {
    }

    public void loadData(){
        //模拟网络耗时操作
        getView().showProgress();
        final RetrofitCancel retrofitCancel=new RetrofitCancel();
       Subscriber<Long> subscriber= new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                getView().hideProgress();
                retrofitCancel.timerStop();
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Long aLong) {
                lazyMainModel.get().setTitle("标题"+ new Random().nextInt(200));
                lazyMainModel.get().setNumberStr("内容"+new Random().nextInt(200));
                getView().showData(lazyMainModel.get());
            }
        };
        retrofitCancel.setSubscriber(subscriber);
        retrofitCancel.timerStart();
        Observable.interval(3, TimeUnit.SECONDS)
                .take(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

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
    private void startLiveShow(String url){
        Intent intent=new Intent(getContext(), SWCodecCameraStreamingActivity.class);
        intent.putExtra(Config.EXTRA_KEY_PUB_URL,url);
        getContext().startActivity(intent);
    }
    @Override
    public void initMvpView() {

    }
}
