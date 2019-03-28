package com.aolan.b365.mvp.presenter;

import com.aolan.b365.base.RxPresenter;
import com.aolan.b365.helper.MyObserver;
import com.aolan.b365.helper.RxUtil;
import com.aolan.b365.mvp.contract.HomeContract;
import com.aolan.b365.mvp.contract.MainContract;
import com.aolan.b365.mvp.model.entity.User;
import com.aolan.b365.netWork.NetClient;
import com.aolan.b365.utils.ToastUitl;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by codeest on 16/8/9.
 */

public class HomePresenter extends RxPresenter<HomeContract.View> implements HomeContract.Presenter{

    @Inject
    public HomePresenter() {

    }


    @Override
    public void doSomething(String str) {

    }
}
