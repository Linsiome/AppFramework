package com.aolan.b365.mvp.presenter;

import com.aolan.b365.helper.MyObserver;
import com.aolan.b365.netWork.NetClient;
import com.aolan.b365.helper.RxUtil;
import com.aolan.b365.base.RxPresenter;
import com.aolan.b365.mvp.contract.LoginContract;
import com.aolan.b365.mvp.model.entity.LoginInfo;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * Created by codeest on 16/8/9.
 */

public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {

    @Inject
    public LoginPresenter() {

    }

    @Override
    public void loginByPw(String phone, String password) {
        NetClient.getInstance(mContext)
                .getNetApi()
                .fixed_login(phone, password)
                .compose(RxUtil.dialogSchedulers(mView,"正在登录..."))
                .subscribe(new MyObserver<LoginInfo>(mContext) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //添加到容器中
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(LoginInfo loginInfo) {
                        int status_code = loginInfo.getCode();
                        if (status_code == 200) {
                            mView.loginSuccess(loginInfo);
                        } else {
                            mView.setErrorTips(loginInfo.getStatus());
                        }
                    }
                });
    }


    @Override
    public void loginByPhone(String phone, String password) {

    }

}
