package com.aolan.b365.base;

import android.support.v7.app.AppCompatDelegate;


import com.aolan.b365.app.App;
import com.aolan.b365.di.component.ActivityComponent;
import com.aolan.b365.di.component.DaggerActivityComponent;
import com.aolan.b365.di.module.ActivityModule;
import com.aolan.b365.utils.StatusBar;
import com.aolan.b365.utils.StatusBarCompat;

import javax.inject.Inject;

/**
 * MVP activity基类
 */
public abstract class BaseActivity<T extends IBasePresenter> extends SimpleActivity implements IBaseView {

    @Inject
    protected T mPresenter;     //dagger2依赖注入

    protected ActivityComponent getActivityComponent(){
        return  DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected abstract void initInject();

    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }


    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initInject();
        if (mPresenter != null){
            mPresenter.attachView(mContext,this);
        }

        StatusBarCompat.appOverlayStatusBar(this, true, true);
        StatusBar.transportStatus(this);
    }


    @Override
    protected void onDestroy() {
        if (mPresenter != null)
            mPresenter.detachView();
        super.onDestroy();
    }


    @Override
    public void useNightMode(boolean isNight) {
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }


}