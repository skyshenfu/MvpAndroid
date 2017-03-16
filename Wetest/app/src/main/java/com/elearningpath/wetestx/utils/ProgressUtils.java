package com.elearningpath.wetestx.utils;

import android.content.Context;
import android.widget.Toast;

import com.elearningpath.wetestx.R;
import com.elearningpath.wetestx.widgets.LoadingDialog;

import java.util.concurrent.TimeUnit;

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

public class ProgressUtils {
    private LoadingDialog progress;
    private Context context;
    private Subscriber subscriber;


    public ProgressUtils( Context context) {
        this.context = context;
    }

    public  void progressShow(){
        if (progress==null){
            initProgress();
        }
        progress.show();
        subscriber=new Subscriber<Long>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                progressDismiss();
                Toast.makeText(context, "网络情况太差，请重试", Toast.LENGTH_SHORT).show();

            }
        };
        Observable.interval(8, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .take(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    private void initProgress() {
        progress=new LoadingDialog(context);
    }
    public void progressDismiss(){
        if (progress!=null) {
            if (progress.isShowing()) {
                progress.dismiss();
            }
        }
        if (subscriber!=null&&!subscriber.isUnsubscribed()){
            subscriber.unsubscribe();
        }
    }
}
