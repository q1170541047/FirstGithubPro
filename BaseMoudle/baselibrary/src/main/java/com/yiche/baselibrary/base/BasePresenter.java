package com.yiche.baselibrary.base;

import java.lang.ref.WeakReference;

/**
 * Created by ${li} on 2018/5/23.
 */

public class BasePresenter<V> {
    protected WeakReference<V> mViewRef;

    public void connectionView(V view) {
        mViewRef = new WeakReference<>(view);
    }

    public void removeView() {
//        LogUtil.d("回收");
        if (mViewRef != null) {
//            LogUtil.d("回收了");
            mViewRef.clear();
            mViewRef = null;
        }
    }

    public V getContext() {
        if (mViewRef != null) {
            return mViewRef.get();
        }
        return null;
    }

}
