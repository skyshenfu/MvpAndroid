package com.elearningpath.wetestx.configs.modules;

import android.view.View;

import com.elearningpath.wetestx.activities.MainActivity;
import com.elearningpath.wetestx.configs.scope.ActivityScope;
import com.elearningpath.wetestx.events.Event;
import com.elearningpath.wetestx.utils.ResponseErrorUtil;
import com.elearningpath.wetestx.utils.RxBus;

import dagger.Module;
import dagger.Provides;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/3/16
 * 版本号：1.0.0
 * 描述：
 */
@Module
public class RxbusSubscriptionModule {
    private Action1<Event> action;

    public RxbusSubscriptionModule(Action1<Event> action) {
        this.action = action;
    }
    @Provides
    @ActivityScope
    Subscription provideSubscription(){
        return RxBus.getInstance().toObserverable(Event.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this.action);
    }
}
