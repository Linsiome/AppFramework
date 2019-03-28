package com.aolan.b365.manager;

import com.aolan.b365.constants.Constants;
import com.aolan.b365.mvp.model.entity.UserInfo;
import com.aolan.b365.utils.SharedPreferencesUtil;

/**
 * Created by Administrator on 2018/4/2.
 */

public class UserManager {
    private volatile static UserManager instance;

    public static UserManager getInstance() {
        if (instance == null) {
            synchronized (UserManager.class) {
                if (instance == null) {
                    instance = new UserManager();
                }
            }
        }
        return instance;
    }

    private UserManager() {
    }

    public void save(UserInfo userInfo) {
        DbManager.getInstance().insert(userInfo);
    }

    public UserInfo getUserInfo() {
        UserInfo userInfo = DbManager.getInstance().getQueryById(getUserId(), UserInfo.class);

        return userInfo;
    }

    public void deleteInfo() {
        DbManager.getInstance().delete(UserInfo.class);
    }

    public boolean isLogin() {
        if (getUserId() != 0) {
            return true;
        }
        return false;
    }

    public long getUserId() {
        return SharedPreferencesUtil.queryLongValue(Constants.USERID);
    }

    public void setUserId(long userId) {
        SharedPreferencesUtil.keepShared(Constants.USERID, userId);
    }

    public String getBackground() {
        return SharedPreferencesUtil.queryValue(Constants.BACKGROUND);
    }

    public void setBackground(String flag) {
        SharedPreferencesUtil.keepShared(Constants.BACKGROUND, flag);
    }

    public String getFirstEnterFlag() {
        return SharedPreferencesUtil.queryValue(Constants.FIRSTFLAG);
    }

    public void setFirstEnterFlag(String flag) {
        SharedPreferencesUtil.keepShared(Constants.FIRSTFLAG, flag);
    }

    public String getUserToken() {
        return SharedPreferencesUtil.queryValue(Constants.ACCESS_TOKEN);
    }

    public void setUserToken(String token) {
        SharedPreferencesUtil.keepShared(Constants.ACCESS_TOKEN, token);
    }

    public String getRefreshToken() {
        return SharedPreferencesUtil.queryValue(Constants.REFRESH_TOKEN);
    }

    public void setRefreshToken(String refreshToken) {
        SharedPreferencesUtil.keepShared(Constants.REFRESH_TOKEN, refreshToken);
    }

    public String getAvatar() {
        return SharedPreferencesUtil.queryValue(Constants.AVATAR);
    }

    public void setAvatar(String Avatar) {
        SharedPreferencesUtil.keepShared(Constants.AVATAR, Avatar);
    }

    public String getAvatarUrl() {
        return SharedPreferencesUtil.queryValue(Constants.AVATARURL);
    }

    public void setAvatarUrl(String AvatarUrl) {
        SharedPreferencesUtil.keepShared(Constants.AVATARURL, AvatarUrl);
    }

    public String getLoginPhone() {
        return SharedPreferencesUtil.queryValue(Constants.PHONE);
    }

    public void setLoginPhone(String loginUsername) {
        SharedPreferencesUtil.keepShared(Constants.PHONE, loginUsername);
    }


    public String getLongitude() {
        return SharedPreferencesUtil.queryValue(Constants.LONGITUDE);
    }

    public void setLongitude(String longitude) {
        SharedPreferencesUtil.keepShared(Constants.LONGITUDE, longitude);
    }

    public String getLatitude() {
        return SharedPreferencesUtil.queryValue(Constants.LATITUDE);
    }

    public void setLatitude(String latitude) {
        SharedPreferencesUtil.keepShared(Constants.LATITUDE, latitude);
    }

    public String getFollowing() {
        return SharedPreferencesUtil.queryValue(Constants.FOLLOWING);
    }

    public void setFollowing(String following) {
        SharedPreferencesUtil.keepShared(Constants.FOLLOWING, following);
    }

    public String getFollowers() {
        return SharedPreferencesUtil.queryValue(Constants.FOLLOWERS);
    }

    public void setFollowers(String followers) {
        SharedPreferencesUtil.keepShared(Constants.FOLLOWERS, followers);
    }


    public void LoginOut() {
        deleteInfo();
        setUserToken("");
    }
}
