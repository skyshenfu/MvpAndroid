package com.elearningpath.wetestx.configs.modules;

import android.content.Context;

import com.elearningpath.wetestx.configs.scope.ActivityScope;
import com.elearningpath.wetestx.utils.ProgressUtils;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhangty on 2017/3/14.
 */
@Module
public class ProgressMoudle {
 private Context context;

    public ProgressMoudle(Context context) {
        this.context = context;
    }
    @ActivityScope
    @Provides
    ProgressUtils provideProgressUtils(){
        return new ProgressUtils(this.context);
    }
}
