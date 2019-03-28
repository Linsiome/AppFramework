package com.aolan.b365.di.component;

import android.app.Activity;


import com.aolan.b365.mvp.ui.activity.MainActivity;
import com.aolan.b365.di.module.ActivityModule;
import com.aolan.b365.di.scope.ActivityScope;

import dagger.Component;


/**
 * Created by codeest on 16/8/7.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(MainActivity mainActivity);
}
