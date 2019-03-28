package com.aolan.b365.mvp.presenter;

import com.aolan.b365.base.RxPresenter;
import com.aolan.b365.mvp.contract.ClassifyContract;
import com.aolan.b365.mvp.contract.HomeContract;

import javax.inject.Inject;

/**
 * Created by codeest on 16/8/9.
 */

public class ClassifyPresenter extends RxPresenter<ClassifyContract.View> implements ClassifyContract.Presenter{

    @Inject
    public ClassifyPresenter() {

    }


    @Override
    public void doSomething(String str) {

    }
}
