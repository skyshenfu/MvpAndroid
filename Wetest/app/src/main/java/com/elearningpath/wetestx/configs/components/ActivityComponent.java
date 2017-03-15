package com.elearningpath.wetestx.configs.components;

import com.elearningpath.wetestx.activities.MainActivity;
import com.elearningpath.wetestx.configs.modules.MainModule;
import com.elearningpath.wetestx.configs.modules.ProgressMoudle;
import com.elearningpath.wetestx.configs.modules.RxbusSubscriptionModule;
import com.elearningpath.wetestx.configs.scope.ActivityScope;

import dagger.Component;

/**
 * Created by zhangty on 2017/3/8.
 */
@ActivityScope
@Component(modules = {MainModule.class, ProgressMoudle.class,RxbusSubscriptionModule.class})
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}
