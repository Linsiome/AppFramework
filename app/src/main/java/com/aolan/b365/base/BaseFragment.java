package com.aolan.b365.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;


import com.aolan.b365.app.App;
import com.aolan.b365.di.component.DaggerFragmentComponent;
import com.aolan.b365.di.component.FragmentComponent;
import com.aolan.b365.di.module.FragmentModule;
import com.aolan.b365.utils.StatusBar;
import com.aolan.b365.utils.StatusBarCompat;

import javax.inject.Inject;

/**
 * MVP Fragment基类
 */
public abstract class BaseFragment<P extends IBasePresenter> extends SimpleFragment implements IBaseView {

    @Inject
    protected P mPresenter;     //dagger2依赖注入

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        mPresenter.attachView(mContext, this);
        super.onViewCreated(view, savedInstanceState);

        StatusBarCompat.appOverlayStatusBar(getActivity(), true, false);
        StatusBar.transportStatus(getActivity());
    }


    @Override
    public void onDestroyView() {
        if (mPresenter != null) mPresenter.detachView();
        super.onDestroyView();
    }


    @Override
    public void useNightMode(boolean isNight) {

    }

    protected abstract void initInject();
}