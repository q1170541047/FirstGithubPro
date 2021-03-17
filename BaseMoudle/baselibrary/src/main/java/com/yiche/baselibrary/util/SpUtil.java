package com.yiche.baselibrary.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.yiche.baselibrary.app.BaseApplicaticon;

import java.util.ArrayList;
import java.util.Set;

/**
 * SharedPreferences工具类
 */
public class SpUtil {
    private static SharedPreferences sPreferences;
    private static final String PREFERENCE_NAME = "businesss";
    private static final String TAG = "perference";

    static {
        sPreferences = BaseApplicaticon.getMyInstance().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 存值
     *
     * @param key   key
     * @param value 值
     */
    public static void putString(String key, String value) {
        sPreferences.edit().putString(key, value).apply();
    }

    /**
     * 存值
     *
     * @param key   key
     * @param value 值
     */
    public static <T> void putStringSet(String key, T value) {
        sPreferences.edit().putStringSet(key, (Set<String>) value).commit();
    }

    /**
     * 取
     *
     * @param key   key
     * @param value 值
     */
    public static <T> T getStringSet(String key, T value) {
        return (T) sPreferences.getStringSet(key, (Set<String>) value);
    }

    /**
     * 存值
     *
     * @param key   key
     * @param value 值
     * @param <T>   类
     */
    public static <T> void put(String key, T value) {
        if (value == null) {
            return;
        }
        SharedPreferences.Editor editor = sPreferences.edit();
        if (value instanceof String) {
            editor.putString(key, ((String) value));
        } else if (value instanceof Integer) {
            editor.putInt(key, ((Integer) value));
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, ((Boolean) value));
        } else if (value instanceof Float) {
            editor.putFloat(key, ((Float) value));
        } else if (value instanceof Long) {
            editor.putLong(key, ((Long) value));
        } else {
            throw new IllegalArgumentException("SharedPreference 非法的存储类型---->" + value.getClass().getCanonicalName());
        }
        editor.apply();
//        LogUtil.d(TAG, "存入缓存内容：key=" + key + "\tvalue" + value);
    }

    /**
     * 存值
     *
     * @param key   key
     * @param value 值
     * @param <T>   类
     */
    public static <T> void commit(String key, T value) {
        if (value == null) {
            return;
        }
        SharedPreferences.Editor editor = sPreferences.edit();
        if (value instanceof String) {
            editor.putString(key, ((String) value));
        } else if (value instanceof Integer) {
            editor.putInt(key, ((Integer) value));
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, ((Boolean) value));
        } else if (value instanceof Float) {
            editor.putFloat(key, ((Float) value));
        } else if (value instanceof Long) {
            editor.putLong(key, ((Long) value));
        } else {
            throw new IllegalArgumentException("SharedPreference 非法的存储类型---->" + value.getClass().getCanonicalName());
        }
        editor.commit();
        LogUtil.d(TAG, "存入缓存内容：key=" + key + "\tvalue" + value);
    }

    /**
     * 获取值
     *
     * @param key      key
     * @param defValue 值
     * @param <T>      类
     * @return 获取值
     */
    public static <T> T get(String key, T defValue) {
        if (TextUtils.isDigitsOnly(key)) {
            throw new IllegalArgumentException("key不能为空");
        }
        T result;
        if (defValue instanceof String) {
            result = ((T) sPreferences.getString(key, ((String) defValue)));
        } else if (defValue instanceof Integer) {
            result = (T) Integer.valueOf(sPreferences.getInt(key, ((Integer) defValue)));
        } else if (defValue instanceof Boolean) {
            result = (T) Boolean.valueOf(sPreferences.getBoolean(key, ((Boolean) defValue)));
        } else if (defValue instanceof Long) {
            result = (T) Long.valueOf(sPreferences.getLong(key, ((Long) defValue)));
        } else if (defValue instanceof Float) {
            result = (T) Float.valueOf(sPreferences.getFloat(key, ((Float) defValue)));
        } else {
            throw new IllegalArgumentException("错误的默认类型, type=" + defValue.getClass().getCanonicalName());
        }
//        LogUtil.d(TAG, "从缓存中取出：key=" + key + "\tvalue=" + result);
        return result;
    }

    /**
     * 全部清除
     */
    public static void clear() {
        sPreferences.edit().clear().apply();
    }

    /**
     * 移除指定key
     *
     * @param key key
     */
    public static void remove(String key) {
        sPreferences.edit().remove(key).apply();
    }

    /**
     * 是否存在
     *
     * @param key key
     * @return 是否存在
     */
    public static boolean contains(String key) {
        return sPreferences.contains(key);
    }


    //    public static void putLogBean(LogInfo logInfo) {
//        try {
//            Gson gson = new Gson();
//            LogBean logBean = gson.fromJson(SpUtil.get(Configs.LOGBEANINFO, ""), LogBean.class);
//            ArrayList<LogInfo> arrayList = logBean.getLogList();
//            arrayList.add(logInfo);
//            String logStr=gson.toJson(LogBean.class)
//            return logBean;
//        } catch (Exception e) {
//            return null;
//        }
//    }

}