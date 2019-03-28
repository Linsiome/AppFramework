package com.aolan.b365.netWork;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;

import com.aolan.b365.constants.HTTPConstants;
import com.aolan.b365.manager.UserManager;
import com.aolan.b365.utils.CacheActivity;
import com.aolan.b365.utils.LogUtil;
import com.aolan.b365.mvp.ui.activity.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Administrator on 2017/3/22.
 */
public class NetClient {
    private static NetClient netClient;
    private static final String TAG = "Api";
    private static Context mContext;
    private String mHost;
    public static final int BASETYPE = 1;
    public static final int TESTTYPE = 2;

    public static NetClient getInstance(Context context) {
        WeakReference<Context> contextWeakReference = new WeakReference<Context>(context);
        mContext = contextWeakReference.get();
        if (netClient == null) {
            return new NetClient();
        }
        return netClient;
    }


    //根据hostType获取baseUrl
    public String getHost(int hostType) {
        String host = null;
        switch (hostType) {
            case BASETYPE:
                    host = HTTPConstants.baseUrl;
                break;
            case TESTTYPE:
                    host = HTTPConstants.testUrl;
                break;
        }
        return host;
    }

    //请求头设置key=Authorization，value=Bearer+（空格）+token的值
    String authorizationValue = "Bearer " + UserManager.getInstance().getUserToken();
    Interceptor authorizationInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", authorizationValue)
                    .build();
            return chain.proceed(request);
        }
    };

    Interceptor XRequestedWithInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("X-Requested-With", "XMLHttpRequest")
                    .build();
            return chain.proceed(request);
        }
    };

    public NetApi getNetApi() {
        //设置缓存
        File httpCacheDirectory = new File(mContext.getCacheDir(), "cache_responses");
        Cache cache = null;
        try {
            cache = new Cache(httpCacheDirectory, 100 * 1024 * 1024);   //100M
        } catch (Exception e) {
            Log.e("OKHttp", "Could not create http cache", e);
        }

        //开启Log,打印网络请求信息
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(
                new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        if (TextUtils.isEmpty(message)) return;
                        Log.i(TAG, message);
                    }
                });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
               // .addInterceptor(interceptorToken)
                .addInterceptor(XRequestedWithInterceptor)
                .build();

        mHost = getHost(BASETYPE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mHost)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit.create(NetApi.class);
    }

    //带Authorization
    public NetApi getNetApi2() {
        //设置缓存
        File httpCacheDirectory = new File(mContext.getCacheDir(), "cache_responses");
        Cache cache = null;
        try {
            cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
        } catch (Exception e) {
            Log.e("OKHttp", "Could not create http cache", e);
        }

        //开启Log,打印网络请求信息
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(
                new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        if (TextUtils.isEmpty(message)) return;
                        Log.i(TAG, message);
                    }
                });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
//                .addInterceptor(interceptorToken)
                .addInterceptor(XRequestedWithInterceptor)
                .addInterceptor(authorizationInterceptor)
                .build();

        mHost = getHost(BASETYPE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mHost)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit.create(NetApi.class);
    }


    Interceptor interceptorToken = new Interceptor() {
        @Override
        public Response intercept(final Chain chain) throws IOException {
            Timer timer = new Timer();
            Request originalRequest = chain.request();

            Response response = chain.proceed(originalRequest);
            ResponseBody responseBody = response.body();
            String body = (String) responseBody.string();//注意：response.body().string()只能获取一次,否则java.lang.IllegalStateException: closed。
            LogUtil.d("response.code()=====service========>" + response.code());
            LogUtil.d("response.body()=======" + body);
            if (response.code() == 200) {
                try {
                    JSONObject jsonObject = new JSONObject(body);
//                    int status_code = jsonObject.getInt("status_code");
                    int status_code = jsonObject.getInt("code");
                    LogUtil.d("token_test=====errCode========>" + status_code);
                    if (status_code == 401) {
                        LogUtil.d("token_test=====errCode=======401=>");
                        timer.schedule(task, 2000);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (response.code() == 401) {
                timer.schedule(task, 2000);
            } else {

            }
            return chain.proceed(originalRequest);
        }
    };

    TimerTask task = new TimerTask() {
        public void run() {
            //execute the task
            CacheActivity.finishActivity();
            LoginActivity.open(mContext, false);
            LoginActivity.openFinishSource(mContext);
        }
    };

    /***
     * 拦截器，保存缓存的方法
     */
    private int netGet = 0;
    private int nonetGet = 0;
    Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            if (isNetworkConnected()) {//有网时
                Response response = chain.proceed(request);
                String cacheControl = request.cacheControl().toString();
                switch (netGet) {
                    case 0://总获取实时信息
                        return response.newBuilder()
                                .removeHeader("Pragma")
                                .removeHeader("Cache-Control")
                                .header("Cache-Control", "public, max-age=" + 0)
                                .build();
                    case 1://t（s）之后获取实时信息--此处是20s
                        return response.newBuilder()
                                .removeHeader("Pragma")
                                .removeHeader("Cache-Control")
                                .header("Cache-Control", "public, max-age=" + cacheTime)
                                .build();
                }
                return null;
            } else {//无网时
                switch (nonetGet) {
                    case 0://无网时一直请求有网请求好的缓存数据，不设置过期时间
                        request = request.newBuilder()
                                .cacheControl(CacheControl.FORCE_CACHE)//此处不设置过期时间
                                .build();
                        break;
                    case 1://此处设置过期时间为t(s);t（s）之前获取在线时缓存的信息(此处t=20)，t（s）之后就不获取了
                        request = request.newBuilder()
                                .cacheControl(FORCE_CACHE1)//此处设置了t秒---修改了系统方法
                                .build();
                        break;
                }

                Response response = chain.proceed(request);
                //下面注释的部分设置也没有效果，因为在上面已经设置了
                return response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached")
                        .removeHeader("Pragma")
                        .build();
            }

        }
    };
    private int cacheTime = 60;
    //这是设置在多长时间范围内获取缓存里面
    public CacheControl FORCE_CACHE1 = new CacheControl.Builder()
            .onlyIfCached()
            .maxStale(cacheTime, TimeUnit.SECONDS)//CacheControl.FORCE_CACHE--是int型最大值
            .build();

    /**
     * 检测网络是否可用
     *
     * @return
     */
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

}
