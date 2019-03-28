package com.aolan.b365.mvp.contract;


import com.aolan.b365.base.IBasePresenter;
import com.aolan.b365.base.IBaseView;

public class HomeContract {
    public interface View extends IBaseView {
        void updateUI(String str);
    }

    public interface Presenter extends IBasePresenter<View> {
        void doSomething(String str);
    }
}
