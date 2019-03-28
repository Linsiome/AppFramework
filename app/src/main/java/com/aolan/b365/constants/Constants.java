package com.aolan.b365.constants;

import android.os.Environment;

import com.aolan.b365.app.App;

import java.io.File;

/**
 * Created by Administrator on 2018/4/2.
 */

public class Constants {
    public static final String ACCESS_TOKEN = "token";

    public static final String USERID = "id";

    public static final String PHONE = "phone";

    public static final String AVATAR = "avatar";

    public static final String AVATARURL = "avatar_url";

    public static final String NAME = "name";

    public static final String FIRSTFLAG = "FIRSTFLAG";

    public static final String BACKGROUND = "background";

    public static final String REFRESH_TOKEN = "refresh_token";

    public static final String LATITUDE = "latitude";

    public static final String LONGITUDE = "longitude";

    public static final String FOLLOWING = "following";

    public static final String FOLLOWERS = "followers";

    //================= PATH ====================

    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "B365";


}
