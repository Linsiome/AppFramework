package com.aolan.b365.mvp.presenter;

import com.aolan.b365.helper.MyObserver;
import com.aolan.b365.netWork.NetClient;
import com.aolan.b365.helper.RxUtil;
import com.aolan.b365.app.App;
import com.aolan.b365.base.RxPresenter;
import com.aolan.b365.utils.ToastUitl;
import com.aolan.b365.mvp.contract.MainContract;
import com.aolan.b365.mvp.model.entity.User;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by codeest on 16/8/9.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter{

    @Inject
    public MainPresenter() {

    }


    @Override
    public void getUserinfo() {
        NetClient.getInstance(mContext)
                .getNetApi2()
                .getUserinfo()
                .compose(RxUtil.aTSchedulers())
                .compose(RxUtil.dialogSchedulers(mView,null))
                .subscribe(new MyObserver<User>(mContext) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(User user) {
                        ToastUitl.showShort(user.getNickname()+"欢迎您!");
                    }
                });

    }

    @Override
    public void doSomething(String str) {

    }


}
