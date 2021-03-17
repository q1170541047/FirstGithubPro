package com.yiche.baselibrary.base.interfaces;

import android.view.MotionEvent;

/**
 * Created by changehe_li on 2018/5/11.
 * 公司：
 * 描述：回调fragment中不具备的生命周期  如果子类需要,可以重写
 */

public interface IFragmentListener {
    /**
     * 事件分发
     * @param motionEvent
     * @return true 消费事件  false 不消费事件
     */
    boolean dispatchTouchEvent(MotionEvent motionEvent);

    /**
     * 回调返回键
     * @return  如果fragment消费事件,返回true,activity不会响应back事件
     */
    boolean onBackPressed();

    /**
     * 焦点变化
     * @param hasFocus view绘制完成后会回调该方法,在这里可以正确的获取view的宽高
     */
    void onWindowFocusChanged(boolean hasFocus);
}
