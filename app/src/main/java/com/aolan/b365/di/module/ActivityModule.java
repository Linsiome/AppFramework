package com.aolan.b365.di.module;

import android.app.Activity;
import android.content.Context;


import com.aolan.b365.di.scope.ActivityScope;
import com.aolan.b365.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by codeest on 16/8/7.
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @FragmentScope
    public Context provideContext() {
        return mActivity;
    }
}
