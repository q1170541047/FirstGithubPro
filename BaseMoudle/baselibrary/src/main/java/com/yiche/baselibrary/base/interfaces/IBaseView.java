package com.yiche.baselibrary.base.interfaces;

import android.os.Bundle;

import com.yiche.baselibrary.base.BaseActivity;


public interface IBaseView {
    /**
     * 默认的时间间隔
     */
    long DEFAULT_TIME_MILLS = 500L;
    /**
     * 显示loading框
     */
    void showLoadingDialog();
    /**
     * 显示loading框
     */
    void showLoadingDialog(String city);

    /**
     * 隐藏loading框
     */
    void hideLoadingDialog();

    /**
     * 是否连续点击 默认值
     * 防止用户重复操作
     * @return
     */
    boolean isFastClick();

    /**
     * 指定时间间隔
     * @param mills  毫秒数
     * @return
     */
    boolean isFastClick(long mills);

    /**
     * 跳转
     * @param aClass 要跳转的activity
     */
    void jumpToActivity(Class<? extends BaseActivity> aClass);

    /**
     * 跳转传值
     * @param aClass 要跳转的activity
     * @param bundle 数据
     */
    void jumpToActivity(Class<? extends BaseActivity> aClass, Bundle bundle);

    /**
     * 跳转后销毁原来界面
     * @param aClass activity
     */
    void jumpToActivityAndFinish(Class<? extends BaseActivity> aClass);

    /**
     * 跳转后销毁原来界面
     * @param aClass activity
     * @param bundle 数据
     */
    void jumpToActivityAndFinish(Class<? extends BaseActivity> aClass, Bundle bundle);
    void jumpToActivityForResult(Class<? extends BaseActivity> aClass, int resultCode);
    void jumpToActivityForResult(Class<? extends BaseActivity> aClass, Bundle bundle, int resultCode);
//    /**
//     * 普通路由跳转
//     * */
//    void jumpToActivity(String path);
//    /**
//     * 携带普通Bundle参数路由跳转
//     * */
//    void jumpToActivity(String path,Bundle bundle);
//    /**
//     * 携带普通Bundle参数路由带回调跳转
//     * */
//    void jumpToActivity(String path,Bundle bundle,int requestCode);
}
