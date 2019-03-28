package com.aolan.b365.mvp.contract;


import com.aolan.b365.base.IBasePresenter;
import com.aolan.b365.base.IBaseView;

public class MainContract {
    public interface View extends IBaseView {
        void updateUI(String str);
    }

    public interface Presenter extends IBasePresenter<View> {
        void getUserinfo();
        void doSomething(String str);
    }
}
