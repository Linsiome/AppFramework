package com.aolan.b365.base;

import android.support.annotation.NonNull;

/**
 * View基类
 */
public interface IBaseView {

    /**
     * 显示加载
     */
    void showProgressDialog(String msg);

    /**
     * 隐藏加载
     */
    void dimissProgressDialog();


    void useNightMode(boolean isNight);
}
