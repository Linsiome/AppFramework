package com.aolan.b365.di.component;




import com.aolan.b365.app.App;
import com.aolan.b365.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    App getContext();  // 提供App的Context

}
