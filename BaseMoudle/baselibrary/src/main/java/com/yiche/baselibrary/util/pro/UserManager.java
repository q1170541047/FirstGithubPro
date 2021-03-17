package com.yiche.baselibrary.util.pro;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.yiche.baselibrary.app.BaseApplicaticon;
import com.yiche.baselibrary.javabean.UserBean;
import com.yiche.baselibrary.util.StringUtil;


public class UserManager {

    private static UserManager sInstance;

    private String mToken;
//    private  mUser;

    private UserManager() {

    }

    public static synchronized UserManager getInstance() {
        if (sInstance == null) {
            sInstance = new UserManager();
        }
        return sInstance;
    }

//    public void setToken(String token) {
//        mToken = token;
//    }

//    public String getToken() {
//        return mToken;
//    }

    public String getTokenString() {
        SharedPreferencesUtil sharedPreferencesUtil = SharedPreferencesUtil.newInstance(BaseApplicaticon.getMyInstance(), IntentKeys.UserSp.NAME);
        String strToken = sharedPreferencesUtil.getString(IntentKeys.UserSp.TOKEN, "");
        if (!TextUtils.isEmpty(strToken)) {
            return strToken;
        } else {
            return "";
        }
    }

    /**
     * 登录用户名
     *
     * @return
     */
    public String getUserName() {
        SharedPreferencesUtil sharedPreferencesUtil = SharedPreferencesUtil.newInstance(BaseApplicaticon.getMyInstance(), IntentKeys.UserSp.NAME);
        String strToken = sharedPreferencesUtil.getString(IntentKeys.UserSp.USER_NAME, "");
        if (!TextUtils.isEmpty(strToken)) {


            return strToken;


        } else {
            return "";
        }

    }

    public String getYcs_user_id() {
        if (getUser() == null) {
            return "-1";
        } else {
            return getUser().getYcs_user_id();
        }

    }

    public boolean isVip() {
        try {
            String vip = StringUtil.getString(getUser().getMembers());
            if (vip.equals("0") || vip.isEmpty()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 是否登录
     *
     * @return
     */
    public boolean isLogin() {
        if (TextUtils.isEmpty(getTokenString())) {
            return false;
        }
        return true;
    }

    public String getHeadUrl() {
        try {
            return StringUtil.getString(getUser().getUser_info().getHead_url());
        } catch (Exception e) {
            return "";
        }
    }

    public UserBean getUser() {
        {
            SharedPreferencesUtil sharedPreferencesUtil = SharedPreferencesUtil.newInstance(BaseApplicaticon.getMyInstance(), IntentKeys.UserSp.NAME);
            String strUser = sharedPreferencesUtil.getString(IntentKeys.UserSp.USER, null);
            if (strUser != null) {
                try {
                    UserBean user = new Gson().fromJson(strUser, UserBean.class);

                    return user;
                } catch (Exception e) {
                    return null;
                }
            }
            return null;
        }
    }

//    public AppConfigData getAppConfig() {
//
//
//            SharedPreferencesUtil sharedPreferencesUtil = SharedPreferencesUtil.newInstance(BaseApplication.getInstance(), SPContracts.UserSp.NAME);
//
//
//            String strUser = sharedPreferencesUtil.getString(SPContracts.UserSp.APPCONFIG, null);
//            if(strUser != null){
//                try {
//                    AppConfigData appConfigData = new Gson().fromJson(strUser, AppConfigData.class);
//
//                    return appConfigData;
//                } catch (Exception e) {
//                    return null;
//                }
//            }
//            return null;
//
//    }


//    public void setUser(UserData user) {
//        this.mUser = user;
//    }

//    public void setUserAndToken(UserData user, String token) {
//        mUser = user;
//        mToken = token;
//    }
}
