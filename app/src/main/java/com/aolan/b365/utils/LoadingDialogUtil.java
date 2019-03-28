package com.aolan.b365.utils;

import android.content.Context;

import com.android.tu.loadingdialog.LoadingDialog;

public class LoadingDialogUtil {
    public LoadingDialog dialog1;
    LoadingDialog.Builder builder1;

    /**
     * 显示进度条
     */
    public void showProgressDialog(Context context, String messege) {
        builder1 = new LoadingDialog.Builder(context)
                .setMessage(messege)
                .setCancelable(false);
        if (dialog1 == null) {
            dialog1 = builder1.create();
            dialog1.show();
        } else {
            dialog1.show();
        }
    }

    /**
     * 隐藏进度条
     */
    public void dimissProgressDialog() {
        if (dialog1 != null)
            dialog1.dismiss();
    }
}
