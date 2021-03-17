package com.yiche.baselibrary.util.pro;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ParseException;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.google.gson.JsonParseException;
import com.yiche.baselibrary.R;
import com.yiche.baselibrary.util.LogUtil;
import com.yiche.baselibrary.util.MyToastUtil;
import com.yiche.baselibrary.util.SpUtil;

import org.json.JSONException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import androidx.annotation.NonNull;
import retrofit2.adapter.rxjava2.HttpException;

/**
 * APP工具类
 */
public class AppUtil {
    private static final String INVALID_DEVICE_ID = "000000000000000";
    private static final String INVALID_ANDROID_ID = "9774d56d682e549c";

//    /**
//     * 是否链接网络
//     *
//     * @return true:已链接  false：未连接
//     */
//    public static boolean isNetWorkAvailable() {
//        ConnectivityManager connectivityManager = (ConnectivityManager) YicheshangApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (connectivityManager != null) {
//            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//            if (activeNetworkInfo != null) {
//                return activeNetworkInfo.isAvailable();
//            }
//        }
//        return false;
//    }

    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(),0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得版本名称
     */
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    /**
     * 获得版本号
     */
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }


    /**
     * @param context
     * @return
     */
    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;
        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pi;
    }

    /**
     * @param throwable
     */
    public static void showException(Throwable throwable) {
        if (throwable instanceof JsonParseException || throwable instanceof JSONException || throwable instanceof ParseException) {
            MyToastUtil.showToast(R.string.json_format_error );
        } else if (throwable instanceof HttpException) {
            MyToastUtil.showToast(R.string.http_format_error + "");
        }  else if (throwable instanceof NullPointerException) {
            MyToastUtil.showToast("数据异常");
        }else if (throwable instanceof UnknownHostException) {
            MyToastUtil.showToast("连接服务器失败");
        } else if (throwable instanceof SocketTimeoutException) {
            MyToastUtil.showToast("连接超时");
        } else if (throwable instanceof ConnectException) {
            MyToastUtil.showToast("连接异常");
        } else if (throwable instanceof javax.net.ssl.SSLHandshakeException) {
            MyToastUtil.showToast("证书验证失败");
        } else {
            MyToastUtil.showToast("未知错误");
        }
        LogUtil.d("errlog", getTrace(throwable));
//        com.qyl.log.LogUtil.d("Throwable", "ThrowableStart" + throwable.getMessage());
    }

    public static String getTrace(Throwable t) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer = stringWriter.getBuffer();
        return buffer.toString();
    }

    /**
     * 判断 App 是否安装
     *
     * @param context     上下文
     * @param packageName 包名
     * @return true：安装  false：未安装
     */
    public static boolean isAppInstalled(Context context, @NonNull final String packageName) {
        return !isSpace(packageName) && context.getPackageManager().getLaunchIntentForPackage(packageName) != null;
    }

    /**
     * 是否有包名
     *
     * @param s 包名
     * @return true：不是包名   false：是包名
     */
    private static boolean isSpace(final String s) {
        if (s == null) {
            return true;
        }
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String getDeviceId(Context context) {
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                @SuppressLint("MissingPermission") String deviceId = telephonyManager.getDeviceId();
                if (!TextUtils.isEmpty(deviceId) && !INVALID_DEVICE_ID.equals(deviceId)) {
                    return deviceId;
                }
            } catch (Exception ignore) {

            }
        }
        return null;
    }

    public static String getAndroidID(Context context) {
        if (context != null) {
            String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            if (!TextUtils.isEmpty(androidId) && !INVALID_ANDROID_ID.equals(androidId)) {
                return androidId;
            }
        }
        return null;
    }

    public static String getChannel(Context ctx) {
        return "";
    }

    /**
     * 获得渠道名
     */
    public static String getChannelName(Context ctx) {
        if (!SpUtil.get(Configs.CHANNEL_NAME, "").isEmpty()) {
            return SpUtil.get(Configs.CHANNEL_NAME, "");
        }
        if (ctx == null) {
            return "";
        }
        String channelName = "";
        try {
            PackageManager packageManager = ctx.getPackageManager();
            if (packageManager != null) {
                //注意此处为ApplicationInfo 而不是 ActivityInfo,因为友盟设置的meta-data是在application标签中，而不是某activity标签中，所以用ApplicationInfo
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        channelName = applicationInfo.metaData.getString("UMENG_CHANNEL");
                        if (channelName == null) {
                            return "";
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return channelName;
    }
    public static void clipboard(Context context,String text) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData mClipData = ClipData.newPlainText("Label", text);
        cm.setPrimaryClip(mClipData);
        MyToastUtil.showToast("已复制到剪贴板");
    }
}