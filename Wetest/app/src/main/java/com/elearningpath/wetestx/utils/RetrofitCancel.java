//package com.elearningpath.wetestx.utils;
//
//import android.util.Log;
//
//import org.reactivestreams.Subscriber;
//import org.reactivestreams.Subscription;
//
//import java.util.concurrent.TimeUnit;
//
//import io.reactivex.Flowable;
//import io.reactivex.Observable;
//import io.reactivex.Observer;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.disposables.Disposable;
//import io.reactivex.schedulers.Schedulers;
//
//
///**
// * Created by zty
// * 个人github地址：http://www.github.com/skyshenfu
// * 日期：2017/3/16
// * 版本号：1.0.0
// * 描述：
// */
//
//public class RetrofitCancel {
//    private Observer observer;
//    private Subscriber<Long> timerSubscriber;
//
//    public RetrofitCancel() {
//    }
//
//    public void setObserver(Observer observer) {
//        this.observer = observer;
//    }
//
//    public void timerStart(){
//
//        timerSubscriber=new Subscriber<Long>() {
//
//            @Override
//            public void onSubscribe(Subscription s) {
//                s.request(Long.MAX_VALUE);
//            }
//
//            @Override
//            public void onNext(Long aLong) {
//                cancleNetRequest();
//                Log.e("TAG", "onNext: 解绑" );
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//
//        };
//        Flowable.interval(8, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .take(1)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(timerSubscriber);
//    }
//    public void timerStop(){
//        if (timerSubscriber!=null){
//            timerSubscriber.
//            Log.e("TAG", "onNext: 解绑-寿终正寝" );
//        }
//    }
//    private void cancleNetRequest(){
//        if (observer !=null&&!observer.isUnsubscribed())
//        {
//            Log.e("TAG", "onNext: 解绑-取消请求" );
//            observer.unsubscribe();
//        }else {
//            Log.e("TAG", "onNext: 解绑-8秒完毕" );
//        }
//
//    }
//
//    @Override
//    protected void finalize() throws Throwable {
//        super.finalize();
//        Log.e("回收了", "finalize: "+this.toString() );
//    }
//}
