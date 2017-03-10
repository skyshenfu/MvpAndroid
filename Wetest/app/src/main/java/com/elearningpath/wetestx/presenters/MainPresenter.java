package com.elearningpath.wetestx.presenters;

import com.elearningpath.wetestx.base.BasePresenterImpl;
import com.elearningpath.wetestx.models.MainModel;
import com.elearningpath.wetestx.utils.RetrofitCancel;
import com.elearningpath.wetestx.views.MainView;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.Lazy;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhangty on 2017/2/7.
 * 列举了一个MainPresent作为样例
 */

public class MainPresenter extends BasePresenterImpl<MainView> {
    @Inject
    Lazy<MainModel> lazyMainModel;
    @Inject
    public MainPresenter(MainView view) {
        super(view);
    }

    public void loadData(){
        //模拟网络耗时操作
        view.showProgress();
        final RetrofitCancel retrofitCancel=new RetrofitCancel();
       Subscriber<Long> subscriber= new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                view.hideProgress();
                retrofitCancel.timerStop();
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Long aLong) {
                lazyMainModel.get().setTitle("标题"+ new Random().nextInt(200));
                lazyMainModel.get().setNumberStr("内容"+new Random().nextInt(200));
                view.showData(lazyMainModel.get());
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

    @Override
    public void initMvpView() {

    }
}
