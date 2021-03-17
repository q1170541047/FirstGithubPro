package com.yiche.baselibrary.util;

import android.app.Activity;

import java.util.Iterator;
import java.util.Stack;


public class ActivityManager {

    private Stack<Activity> mActivities;

    private ActivityManager() {
        mActivities = new Stack<>();
    }

    public static ActivityManager getInstance() {
        return SingletonHolder.ACTIVITY_MANAGER;
    }

    public void push(Activity activity) {
        mActivities.push(activity);
    }

    public Activity currentActivity() {
        if (mActivities.isEmpty()) {
            return null;
        }
        return mActivities.lastElement();
    }

    public void removeLast() {
        if (mActivities.isEmpty()) {
            return;
        }
        Activity activity = mActivities.pop();
        if (!activity.isFinishing()) {
            activity.finish();
        }
    }

    public boolean hasActivity(Class<?> aClass) {
        if (mActivities.isEmpty()) {
            return false;
        }
        Iterator<Activity> iterator = mActivities.iterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if (activity.getClass().getCanonicalName().equals(aClass.getCanonicalName())) {
                return true;
            }
        }
        return false;
    }

    public void remove(Class<?> aClass) {
        if (mActivities.isEmpty()) {
            return;
        }
        Iterator<Activity> iterator = mActivities.iterator();
//        if (aClass.getCanonicalName().contains("CarDetailActivity")) {
//            return;
//        }
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if (activity.getClass().getCanonicalName().equals(aClass.getCanonicalName()) && !activity.getClass().getCanonicalName().contains("CustomerDetailActivity")
                    && !activity.getClass().getCanonicalName().contains("MyFollowListActivity")
                    && !activity.getClass().getCanonicalName().contains("CircleDetailActivity")
                    && !activity.getClass().getCanonicalName().contains("SingleCircleDetailActivity")
                    && !activity.getClass().getCanonicalName().contains("XunCheDetailActivity")
                    && !activity.getClass().getCanonicalName().contains("ZYCarDetailActivity")
                    && !activity.getClass().getCanonicalName().contains("DealerCarDetailActivity")) {
                if (!activity.isFinishing()) {
                    activity.finish();
                }
                iterator.remove();
                break;
            }
        }
    }

    public void delete(Class<?> aClass) {
        if (mActivities.isEmpty()) {
            return;
        }
        Iterator<Activity> iterator = mActivities.iterator();
//        if (aClass.getCanonicalName().contains("CarDetailActivity")) {
//            return;
//        }
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if (activity.getClass().getCanonicalName().equals(aClass.getCanonicalName())) {
                if (!activity.isFinishing()) {
                    activity.finish();
                }
                iterator.remove();
                break;
            }
        }
    }

    public void remove(Class<?>... activities) {
        if (activities == null || activities.length == 0) {
            return;
        }
        for (Class<?> activity : activities) {
            remove(activity);
        }
    }

    public void finishAll() {
        if (mActivities.isEmpty()) {
            return;
        }
        for (Activity activity : mActivities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        mActivities.clear();
    }

    private static class SingletonHolder {
        private static final ActivityManager ACTIVITY_MANAGER = new ActivityManager();
    }
}
