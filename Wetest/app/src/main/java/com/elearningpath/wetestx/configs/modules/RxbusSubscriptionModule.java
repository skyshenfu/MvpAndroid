package com.elearningpath.wetestx.configs.modules;

import android.view.View;

import com.elearningpath.wetestx.activities.MainActivity;
import com.elearningpath.wetestx.configs.scope.ActivityScope;
import com.elearningpath.wetestx.events.Event;
import com.elearningpath.wetestx.utils.ResponseErrorUtil;
import com.elearningpath.wetestx.utils.RxBus;

import org.reactivestreams.Subscription;

import dagger.Module;
import dagger.Provides;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/3/16
 * 版本号：1.0.0
 * 描述：
 */
@Module
public class RxbusSubscriptionModule {
    private Consumer<Event> consumer;

    public RxbusSubscriptionModule(Consumer<Event> consumer) {
        this.consumer = consumer;
    }
    @Provides
    @ActivityScope
    Disposable provideSubscription(){
        return RxBus.getInstance().toObservable(Event.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
