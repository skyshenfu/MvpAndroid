package com.elearningpath.wetestx.utils;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by zhangty on 2017/3/10.
 */

public class RetrofitCancel {
    private Subscriber subscriber;
    private Subscriber timerSubscriber;

    public RetrofitCancel(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public void timerStart(){

        timerSubscriber=new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                this.unsubscribe();
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Long aLong) {
                cancleNetRequest();
                Log.e("TAG", "onNext: 解绑" );
            }
        };
        Observable.interval(8, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .take(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(timerSubscriber);
    }
    public void timerStop(){
        if (timerSubscriber!=null&&!timerSubscriber.isUnsubscribed()){
            timerSubscriber.unsubscribe();
        }
    }
    private void cancleNetRequest(){
        if (subscriber!=null&&!subscriber.isUnsubscribed())
        {
            subscriber.unsubscribe();
        }else {
            Log.e("TAG", "onNext: 解绑22223333" );
        }

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Log.e("回收了", "finalize: " );
    }
}
