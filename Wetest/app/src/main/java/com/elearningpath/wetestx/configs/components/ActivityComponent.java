package com.elearningpath.wetestx.configs.components;

import com.elearningpath.wetestx.configs.scope.ActivityScope;

import dagger.Component;

/**
 * Created by zhangty on 2017/3/8.
 */
@ActivityScope
@Component(dependencies = AppComponent.class)
public interface ActivityComponent {
}
