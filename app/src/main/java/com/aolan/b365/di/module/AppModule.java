package com.aolan.b365.di.module;


import android.content.Context;

import com.aolan.b365.app.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by codeest on 16/8/7.
 */

@Module
public class AppModule {
    public App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideAppContext() {
        return application;
    }

}
