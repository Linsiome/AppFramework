package com.aolan.b365.netWork;



import com.aolan.b365.base.BaseResponse;
import com.aolan.b365.mvp.model.entity.LoginInfo;
import com.aolan.b365.mvp.model.entity.User;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * Created by Administrator on 2017/3/22.
 */
public interface NetApi {

    /**
     * 密码登录
     */
    @FormUrlEncoded
    @POST("api/auth/login")
    Observable<LoginInfo> fixed_login(@Field("telephone") String phone, @Field("password") String passWord);


    /**
     * 查询个人信息
     */
    @GET("api/auth/user")
    Observable<BaseResponse<User>> getUserinfo();
}
