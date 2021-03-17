package com.yiche.baselibrary.util;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;


public class LogUtil {

    private static final String LOG_TAG = "code";

    static {
        FormatStrategy strategy = PrettyFormatStrategy.newBuilder().tag(LOG_TAG).build();
        Logger.addLogAdapter(new AndroidLogAdapter(strategy));
    }

    public static void d(String message) {
        Logger.d(message);
    }
    public static void d(String tag, String message) {
        Logger.t(tag).d(message);
    }

    public static void d(Object o) {
        Logger.d(o);
    }

    public static void d(String tag, Object o) {
        Logger.t(tag).d(o);
    }

    public static void e(String message) {
        Logger.e(message);
    }

    public static void e(String tag, String message) {
        Logger.t(tag).e(message);
    }

    public static void e(Throwable throwable, String message) {
        Logger.e(throwable, message);
    }

    public static void e(String tag, Throwable throwable, String message) {
        Logger.t(tag).e(throwable, message);
    }

}
