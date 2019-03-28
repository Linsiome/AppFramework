package com.aolan.b365.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.tu.loadingdialog.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 无MVP的Fragment基类
 */

public abstract class SimpleFragment extends Fragment implements IBaseView{
    protected View mView;
    protected Context mContext;
    private Unbinder mUnBinder;

    public LoadingDialog dialog;
    LoadingDialog.Builder builder;

    @Override
    public void onAttach(Context context) {
        mContext = getActivity();
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnBinder = ButterKnife.bind(this, view);
        initEventAndData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
    }

    protected abstract int getLayoutId();
    protected abstract void initEventAndData();



    /**
     * 显示隐藏进度条
     */
    @Override
    public synchronized void showProgressDialog(String messege) {
        if(TextUtils.isEmpty(messege)) messege="加载中...";
        builder = new LoadingDialog.Builder(getActivity())
                .setMessage(messege)
                .setCancelable(false);
        if (dialog == null) {
            dialog = builder.create();
            dialog.show();
        } else {
            dialog.show();
        }
    }

    /**
     * 隐藏进度条
     */
    @Override
    public synchronized void dimissProgressDialog() {
        if (dialog != null)
            dialog.dismiss();
    }


}
