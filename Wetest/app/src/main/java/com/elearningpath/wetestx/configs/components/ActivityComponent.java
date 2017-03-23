package com.elearningpath.wetestx.configs.components;

import com.elearningpath.wetestx.activities.MainActivity;
import com.elearningpath.wetestx.configs.modules.ActivityModule;
import com.elearningpath.wetestx.configs.modules.MainModule;
import com.elearningpath.wetestx.configs.modules.ProgressMoudle;
import com.elearningpath.wetestx.configs.modules.RxbusSubscriptionModule;
import com.elearningpath.wetestx.configs.scope.ActivityScope;

import dagger.Component;

/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/3/16
 * 版本号：1.0.0
 * 描述：
 */
@ActivityScope
@Component(modules = {ProgressMoudle.class,RxbusSubscriptionModule.class})
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}
