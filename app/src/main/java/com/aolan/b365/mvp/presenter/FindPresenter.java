package com.aolan.b365.mvp.presenter;

import com.aolan.b365.base.RxPresenter;
import com.aolan.b365.mvp.contract.ClassifyContract;
import com.aolan.b365.mvp.contract.FindContract;

import javax.inject.Inject;

/**
 * Created by codeest on 16/8/9.
 */

public class FindPresenter extends RxPresenter<FindContract.View> implements FindContract.Presenter{

    @Inject
    public FindPresenter() {

    }


    @Override
    public void doSomething(String str) {

    }
}
