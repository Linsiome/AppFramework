package com.aolan.b365.mvp.presenter;

import com.aolan.b365.base.RxPresenter;
import com.aolan.b365.mvp.contract.ClassifyContract;
import com.aolan.b365.mvp.contract.ShoppingCartContract;

import javax.inject.Inject;

/**
 * Created by codeest on 16/8/9.
 */

public class ShoppingCartPresenter extends RxPresenter<ShoppingCartContract.View> implements ShoppingCartContract.Presenter{

    @Inject
    public ShoppingCartPresenter() {

    }


    @Override
    public void doSomething(String str) {

    }
}
