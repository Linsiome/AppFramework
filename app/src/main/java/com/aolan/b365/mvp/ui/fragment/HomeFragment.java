package com.aolan.b365.mvp.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aolan.b365.R;
import com.aolan.b365.base.BaseFragment;
import com.aolan.b365.base.IBaseView;
import com.aolan.b365.mvp.presenter.HomePresenter;
import com.aolan.b365.mvp.presenter.LoginPresenter;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements IBaseView {

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initEventAndData() {

    }
}
