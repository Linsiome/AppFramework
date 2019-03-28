package com.aolan.b365.di.component;

import android.app.Activity;


import com.aolan.b365.di.module.FragmentModule;
import com.aolan.b365.di.scope.FragmentScope;
import com.aolan.b365.mvp.ui.fragment.ClassifyFragment;
import com.aolan.b365.mvp.ui.fragment.FindFragment;
import com.aolan.b365.mvp.ui.fragment.HomeFragment;
import com.aolan.b365.mvp.ui.fragment.LoginByPhoneFragment;
import com.aolan.b365.mvp.ui.fragment.LoginByPwFragment;
import com.aolan.b365.mvp.ui.fragment.PersonalFragment;
import com.aolan.b365.mvp.ui.fragment.ShoppingCartFragment;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(LoginByPwFragment fragment);

    void inject(LoginByPhoneFragment fragment);

    void inject(HomeFragment fragment);

    void inject(ClassifyFragment fragment);

    void inject(FindFragment fragment);

    void inject(ShoppingCartFragment fragment);

    void inject(PersonalFragment fragment);
}
