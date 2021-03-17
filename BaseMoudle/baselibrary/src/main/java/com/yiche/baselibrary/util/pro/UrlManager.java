package com.yiche.baselibrary.util.pro;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.yiche.baselibrary.app.BaseApplicaticon;
import com.yiche.baselibrary.javabean.JsUrlBean;


public class UrlManager {
    private static UrlManager sInstance;
    private String mToken;
//    private  mUser;
    private UrlManager() {

    }
    public static synchronized UrlManager getInstance() {
        if (sInstance == null) {
            sInstance = new UrlManager();
        }
        return sInstance;
    }

//    public void setToken(String token) {
//        mToken = token;
//    }

//    public String getToken() {
//        return mToken;
//    }

//    public String getBaseUrl() {
//        SharedPreferencesUtil sharedPreferencesUtil = SharedPreferencesUtil.newInstance(BaseApp.getInstance(), IntentKeys.UserSp.NAME);
//        String strToken = sharedPreferencesUtil.getString(IntentKeys.UserSp.TOKEN, "");
//        if(!TextUtils.isEmpty(strToken)){
//
//
//            return strToken;
//
//
//
//        }else {return "";}
//
//    }

//    public JsUrlBean JsUrlBean() {
//   {
//            SharedPreferencesUtil sharedPreferencesUtil = SharedPreferencesUtil.newInstance(BaseApp.getInstance(), IntentKeys.UserSp.NAME);
//            String strUser = sharedPreferencesUtil.getString(IntentKeys.JSURL, null);
//            if(strUser != null){
//                try {
//                    JsUrlBean urlBean = new Gson().fromJson(strUser, JsUrlBean.class);
//
//                      return urlBean;
//                } catch (Exception e) {
//                    return null;
//                }
//            }
//            return null;
//        }
//    }


    public String getOSSUrl() {
        {
            SharedPreferencesUtil sharedPreferencesUtil = SharedPreferencesUtil.newInstance(BaseApplicaticon.getMyInstance(), IntentKeys.MYJS);
            String strUser = sharedPreferencesUtil.getString(IntentKeys.JSURL, null);
            if (strUser != null && strUser.length() > 2) {
                try {
                    JsUrlBean urlBean = new Gson().fromJson(strUser, JsUrlBean.class);
                    if (urlBean != null) {
                        if (!TextUtils.isEmpty(urlBean.getOss())) {

                            return urlBean.getOss();

                        }


                    } else {
                        return "";
                    }


                } catch (Exception e) {
                    return "";
                }
            }
            return "";
        }
    }

    public String geth5Url() {
        {
            SharedPreferencesUtil sharedPreferencesUtil = SharedPreferencesUtil.newInstance(BaseApplicaticon.getMyInstance(), IntentKeys.MYJS);
            String strUser = sharedPreferencesUtil.getString(IntentKeys.JSURL, null);
            if (strUser != null && strUser.length() > 2) {
                try {
                    JsUrlBean urlBean = new Gson().fromJson(strUser, JsUrlBean.class);
                    if (urlBean != null) {
                        if (!TextUtils.isEmpty(urlBean.getH5())) {
                            return urlBean.getH5();
                        } else {
                            return "";
                        }


                    } else {
                        return "";
                    }


                } catch (Exception e) {
                    return "";
                }
            }
            return "";
        }
    }

    public String getUrl() {
        {
            SharedPreferencesUtil sharedPreferencesUtil = SharedPreferencesUtil.newInstance(BaseApplicaticon.getMyInstance(), IntentKeys.MYJS);
            String strUser = sharedPreferencesUtil.getString(IntentKeys.JSURL, null);
            if (strUser != null && strUser.length() > 2) {
                try {
                    JsUrlBean urlBean = new Gson().fromJson(strUser, JsUrlBean.class);
                    if (urlBean != null) {
                        if (!TextUtils.isEmpty(urlBean.getUrl())) {

                            return urlBean.getUrl();

                        } else {
                            return "";
                        }


                    } else {
                        return "";
                    }


                } catch (Exception e) {
                    return "";
                }
            }
            return "";
        }
//        return ApiConstants.BASE_URL;
    }

    public String getImageUrl() {
        if (!TextUtils.isEmpty(getOSSUrl())) {

            return getOSSUrl() + "?file_type=image&project=yiche";

        } else {
            return "";
        }
        //    return "https://master-api-ycy-beta.saasyc.com/yiche/oss/config"+"?file_type=image&project=yiche";
    }

    public String getVideoUrl() {
        if (!TextUtils.isEmpty(getOSSUrl())) {

            return getOSSUrl() + "?file_type=video&project=yiche";

        } else {
            return "";
        }
    }

    public String getAudioUrl() {
        if (!TextUtils.isEmpty(getOSSUrl())) {

            return getOSSUrl() + "?file_type=audio&project=yiche";

        } else {
            return "";
        }
    }


//    public void setUser(UserData user) {
//        this.mUser = user;
//    }

//    public void setUserAndToken(UserData user, String token) {
//        mUser = user;
//        mToken = token;
//    }
}
