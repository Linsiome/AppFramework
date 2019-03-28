package com.aolan.b365.mvp.contract;


import android.content.Context;

import com.aolan.b365.base.IBasePresenter;
import com.aolan.b365.base.IBaseView;
import com.aolan.b365.mvp.model.entity.LoginInfo;

public class LoginContract {
    public interface View extends IBaseView {
        void loginSuccess(LoginInfo loginInfo);
        void setErrorTips(String string);
    }

    public interface Presenter extends IBasePresenter<View> {
        void loginByPw(String phone, String password);
        void loginByPhone(String phone, String password);
    }
}
