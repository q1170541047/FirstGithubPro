package com.yiche.baselibrary.util;

import android.app.Application;
import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

/**
 * 吐丝工具类
 */
public class MyToastUtil {
    private static Context sContext;
    private static Toast toast = null;

    /**
     * 上下文初始化
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        sContext = context;
    }

    private static void checkInit() {
        if (sContext == null || !(sContext instanceof Application)) {
            throw new NullPointerException("请在Application中完成初始化");
        }
    }

    public static void showToast(String message) {
        show(message, Toast.LENGTH_SHORT);
    }

    public static void showToast(int resId) {
        show(resId, Toast.LENGTH_SHORT);
    }

    public static void showLongToast(String message) {
        show(message, Toast.LENGTH_LONG);
    }

    public static void showLongToast(int resId) {
        show(resId, Toast.LENGTH_LONG);
    }

    public static void cancel() {
    }

    private static void show(String message, int period) {
        if (message == null || message.isEmpty()) {
            return;
        }
        checkInit();
        try {
            if (RomUtil.isHuawei()) {
                toast = Toast.makeText(sContext, message, Toast.LENGTH_SHORT);
            } else {
                if (toast != null) {
                    toast.setText(message);
                } else {
                    toast = Toast.makeText(sContext, message, Toast.LENGTH_SHORT);
                }
            }

//            toast.setGravity(Gravity.CENTER, 0, 0);
//            View toastview= LayoutInflater.from(sContext).inflate(R.layout.toast_text_layout,null);
//            TextView text = (TextView) toastview.findViewById(R.id.tv_message);
//            text.setText(message);
//            LinearLayout toastView = (LinearLayout) toast.getView();
//            if (toastView!=null){
//                TextView textView =  toastView.findViewById(android.R.id.message);
//                if (textView!=null)
//                textView.setTextColor(sContext.getResources().getColor(R.color.white));
//                toastView.setBackgroundColor(sContext.getResources().getColor(R.color.black));
//            }
            toast.show();
        } catch (Exception e) {
            //解决在子线程中调用Toast的异常情况处理
            Looper.prepare();
            Toast.makeText(sContext, message, Toast.LENGTH_SHORT).show();
            Looper.loop();
        }
    }

    private static void show(int resId, int period) {
        checkInit();
        Toast.makeText(sContext, sContext.getString(resId), period).show();
    }

    /**
     * 普通Toast小米自带应用名称问题
     *
     * @param context
     * @param text
     */
    public static void show(Context context, String text) {
        try {
            if (toast != null) {
                toast.setText(text);
            } else {
                toast = Toast.makeText(sContext, text, Toast.LENGTH_SHORT);
            }
            toast.show();
        } catch (Exception e) {
            Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}