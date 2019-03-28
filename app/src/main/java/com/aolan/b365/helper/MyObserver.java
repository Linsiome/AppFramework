package com.aolan.b365.helper;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.aolan.b365.manager.UserManager;
import com.aolan.b365.utils.CacheActivity;
import com.aolan.b365.utils.ToastUitl;
import com.aolan.b365.mvp.ui.activity.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public abstract class MyObserver<T> implements Observer<T> {
    protected Context context;
    protected Disposable disposable;

    public MyObserver(Context context) {
        this.context = context;
    }

    @Override
    public abstract void onSubscribe(Disposable d);

    @Override
    public abstract void onNext(T t);

    @Override
    public void onError(Throwable e) {
        Log.e("Api", "------------>onError: " + e.toString());
        if (e.toString().contains("SocketTimeoutException")) {
            ToastUitl.showLong("连接服务器超时！");
        } else if (e.toString().contains("ConnectException")) {
            ToastUitl.showLong("连接服务器失败，请检查您当前的网络状态！");
        }else if (e.toString().contains("UnknownHostException")) {
            ToastUitl.showLong("网络连接错误！");
        } else {
            if (e instanceof IOException) {
                ToastUitl.showLong("未知错误！");
            } else if (e.toString().contains("401")) {
                ToastUitl.showLong("登录过期，请重新登录！");
                CacheActivity.finishActivity();
                LoginActivity.openFinishSource(context);
                UserManager.getInstance().LoginOut();
            } else if (e.toString().contains("403")) {
                ToastUitl.showLong("密码错误！");
            } else if (e.toString().contains("404")) {
                ToastUitl.showLong("未找到相应的数据！");
            } else if (e.toString().contains("405")) {
                ToastUitl.showLong("方法不被允许！");
            } else if (e.toString().contains("500")) {
                ToastUitl.showLong("服务器异常！");
            }else if (e.toString().contains("503")) {
                ToastUitl.showLong("请求拒绝！");
            } else if (e instanceof HttpException) {
                try {
                    ResponseBody errorBody = ((HttpException) e).response().errorBody();
                    String bodySting = errorBody.string();//注意是errorBody.string而不是errorBody.toString，不然它打印出来的只是地址。
                    JSONObject jsonObject = new JSONObject(bodySting);
                    int code = jsonObject.getInt("code");
                    String message = jsonObject.getString("message");
                    Log.e("Api", "------------>code=" + code);
                    Log.e("Api", "------------>message=" + message);
                    if (message != null && !TextUtils.isEmpty(message))
                        ToastUitl.showShort(message);
                } catch (IOException e1) {
                    Log.e("Api", "------------>IOException: " + e1.toString());
                } catch (JSONException e1) {
                    Log.e("Api", "------------>JSONException: " + e1.toString());
                }
            } else {
                ToastUitl.showLong("网络请求错误！");
            }
        }
    }

    @Override
    public void onComplete() {

    }
}
