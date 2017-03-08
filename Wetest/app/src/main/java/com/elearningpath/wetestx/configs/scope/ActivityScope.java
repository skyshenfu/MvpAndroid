package com.elearningpath.wetestx.configs.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by zhangty on 2017/3/8.
 */

@Scope
@Retention(RUNTIME)
public @interface ActivityScope {
}
