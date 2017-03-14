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
 * Created by zhangty on 2017/3/14.
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
