package com.aolan.b365.base;


import android.content.Context;

/**
 * Presenter基类
 */
public interface IBasePresenter<V extends IBaseView>{
    void attachView(Context context,V view);

    void detachView();
}
