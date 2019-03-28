package com.aolan.b365.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.android.tu.loadingdialog.LoadingDialog;
import com.aolan.b365.app.App;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 无MVP的activity基类
 */

public abstract class SimpleActivity extends AppCompatActivity implements IBaseView{

    public Context mContext;
    private Unbinder mUnBinder;

    public LoadingDialog dialog;
    LoadingDialog.Builder builder;

    /**
     * 显示隐藏进度条
     */
    @Override
    public synchronized void showProgressDialog(String messege) {
        if(TextUtils.isEmpty(messege)) messege="加载中...";
        builder = new LoadingDialog.Builder(this)
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        onViewCreated();
        App.getInstance().addActivity(this);
        initEventAndData();
    }

    protected abstract int getLayout();
    protected abstract void initEventAndData();

    protected void onViewCreated() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);
        mUnBinder.unbind();
    }

}
