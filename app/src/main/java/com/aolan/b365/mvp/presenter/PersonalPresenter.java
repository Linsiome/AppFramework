package com.aolan.b365.mvp.presenter;

import com.aolan.b365.base.RxPresenter;
import com.aolan.b365.mvp.contract.FindContract;
import com.aolan.b365.mvp.contract.PersonalContract;

import javax.inject.Inject;

/**
 * Created by codeest on 16/8/9.
 */

public class PersonalPresenter extends RxPresenter<PersonalContract.View> implements PersonalContract.Presenter{

    @Inject
    public PersonalPresenter() {

    }


    @Override
    public void doSomething(String str) {

    }
}
