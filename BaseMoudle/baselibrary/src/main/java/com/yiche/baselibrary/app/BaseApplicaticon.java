package com.yiche.baselibrary.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.yiche.baselibrary.util.ActivityManager;
import com.yiche.baselibrary.util.MyToastUtil;

/**
 * @Description: java类作用描述
 * @Author: yiche_li
 * @CreateDate: 2021/3/15 15:46
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/3/15 15:46
 */

public class BaseApplicaticon extends Application{
    private static Application instance = null;
    private int mActivityCount;

    public int getmActivityCount() {
        return mActivityCount;
    }

    public static synchronized Application getMyInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        MyToastUtil.init(instance);
        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savfedInstanceState) {
                ActivityManager.getInstance().push(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                mActivityCount++;
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
                mActivityCount--;
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                ActivityManager.getInstance().remove(activity.getClass());
            }
        });
    }

}
