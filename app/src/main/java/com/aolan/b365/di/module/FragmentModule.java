package com.aolan.b365.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;


import com.aolan.b365.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by codeest on 16/8/7.
 */

@Module
public class FragmentModule {
    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }

    @Provides
    @FragmentScope
    public Context provideContext() {
        return fragment.getActivity();
    }
}
