package com.yiche.baselibrary.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.gyf.immersionbar.ImmersionBar;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.yiche.baselibrary.BuildConfig;
import com.yiche.baselibrary.R;
import com.yiche.baselibrary.base.customview.TitleBarView;
import com.yiche.baselibrary.base.interfaces.IBaseView;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import io.reactivex.Observable;
import me.jessyan.autosize.AutoSizeCompat;

public abstract class BaseActivity extends RxAppCompatActivity implements IBaseView {
    //当前正在显示的fragment  可以为空  在需要回调IFragmentListener时赋值
    protected View mRootView;
    //沉浸式状态栏
    protected ImmersionBar mImmersionBar;
    private long lastClickTime;
    public BasePresenter mPresenter;
    protected final String TAG = getClass().getSimpleName();
    private Context mContext;//上下文
    protected BaseActivity mActivity;//activity
    protected boolean FINISH_ACT = true;//退出是否有动画

    public ImmersionBar getmImmersionBar() {
        return mImmersionBar;
    }

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O && isTranslucentOrFloating()) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//禁止横屏
        }
        mContext = this;
        mActivity = this;
        beforeContentView();
        mRootView = getLayoutInflater().inflate(getLayoutId(), null);
        setContentView(mRootView);
        if (useImmersion()) {
            initImmersionBar();
        }
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.connectionView(this);
        }
        initData();
        initAdapter();
        initRefreshs();
        initHttp();
        parseIntent();
    }
    /**
     * @param
     * @return
     * @method
     * @description 是否显示去客服页面图标
     * @date:
     */
    protected boolean showServiceIcon() {
        return true;
    }
//    ClickTagListener clickTagListener;
//
//    public ClickTagListener getClickTagListener() {
//        return clickTagListener;
//    }
//
//    public void setClickTagListener(ClickTagListener clickTagListener) {
//        this.clickTagListener = clickTagListener;
//    }
//
//    public interface ClickTagListener {
//        void clickCamera();
//        void clickCamera2(String aaa);
//    }

    protected void initRefreshs() {

    }

    private boolean isTranslucentOrFloating() {
        boolean isTranslucentOrFloating = false;
        try {
            int[] styleableRes = (int[]) Class.forName("com.android.internal.R$styleable").getField("Window").get(null);
            final TypedArray ta = obtainStyledAttributes(styleableRes);
            Method m = ActivityInfo.class.getMethod("isTranslucentOrFloating", TypedArray.class);
            m.setAccessible(true);
            isTranslucentOrFloating = (boolean) m.invoke(null, ta);
            m.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isTranslucentOrFloating;
    }

    protected void setErrNetEmpty() {
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        parseIntent();
    }

    /**
     * 七鱼需要
     */
    private void parseIntent() {
    }

    protected BasePresenter createPresenter() {
        return null;
    }

    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true)
                .transparentStatusBar()
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题，默认为false，还有一个重载方法，可以指定软键盘mode
                .keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)  //单独指定软键盘模式
                .fitsSystemWindows(true)
                .statusBarColor(R.color.white)
                .init();
    }

    /**
     * 返回布局信息
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化
     */
    protected abstract void initData();

    /**
     * 在setContentView之前调用,默认空实现;子类如果需要,可以重写.
     */
    protected void beforeContentView() {
    }

    /**
     * 初始化adapter
     */
    protected void initAdapter() {
    }

    /**
     * 接口请求
     */
    protected void initHttp() {
    }

    /**
     * 是否使用沉浸式
     *
     * @return true使用/false 不使用  默认true  子类可以修改
     */
    protected boolean useImmersion() {
        return true;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
//
//        if (getLocalClassName().contains("MainYiChe2Activity")) {
//            MobclickAgent.onPageStart("首页");
//            LogUtil.d("首页");
//        } else {
//            TextView titleText = findViewById(R.id.titleText);
//            TextView toolbar_mytitle = findViewById(R.id.toolbar_mytitle);
//            if (titleText != null) {
//                if (!titleText.getText().toString().trim().isEmpty()) {
//                    MobclickAgent.onPageStart(titleText.getText().toString().trim());
//                    LogUtil.d(titleText.getText().toString().trim());
//                } else {
//                    MobclickAgent.onPageStart(this.getLocalClassName());
//                    LogUtil.d(this.getLocalClassName());
//                }
//            } else {
//                if (toolbar_mytitle != null) {
//                    if (!toolbar_mytitle.getText().toString().trim().isEmpty()) {
//                        MobclickAgent.onPageStart(toolbar_mytitle.getText().toString().trim());
//                        LogUtil.d(toolbar_mytitle.getText().toString().trim());
//                    } else {
//                        MobclickAgent.onPageStart(this.getLocalClassName());
//                        LogUtil.d(this.getLocalClassName());
//                    }
//                } else {
//                    MobclickAgent.onPageStart(this.getLocalClassName());
//                    LogUtil.d(this.getLocalClassName());
//                }
//            }
//        }
//        MobclickAgent.onResume(this); // 不能遗漏
        Observable.interval(1, TimeUnit.SECONDS)
                .compose(bindToLifecycle())
                .subscribe();
    }

    @Override
    protected void onPause() {
        super.onPause();

//        if (getLocalClassName().contains("MainYiChe2Activity")) {
//            MobclickAgent.onPageEnd("首页");
//            LogUtil.d("首页");
//        } else {
//            TextView titleText = findViewById(R.id.titleText);
//            TextView toolbar_mytitle = findViewById(R.id.toolbar_mytitle);
//            if (titleText != null) {
//                if (!titleText.getText().toString().trim().isEmpty()) {
//                    MobclickAgent.onPageEnd(titleText.getText().toString().trim());
//                    LogUtil.d(titleText.getText().toString().trim());
//                } else {
//                    MobclickAgent.onPageEnd(this.getLocalClassName());
//                    LogUtil.d(this.getLocalClassName());
//                }
//            } else {
//                if (toolbar_mytitle != null) {
//                    if (!toolbar_mytitle.getText().toString().trim().isEmpty()) {
//                        MobclickAgent.onPageEnd(toolbar_mytitle.getText().toString().trim());
//                        LogUtil.d(toolbar_mytitle.getText().toString().trim());
//                    } else {
//                        MobclickAgent.onPageEnd(this.getLocalClassName());
//                        LogUtil.d(this.getLocalClassName());
//                    }
//                } else {
//                    MobclickAgent.onPageEnd(this.getLocalClassName());
//                    LogUtil.d(this.getLocalClassName());
//                }
//            }
//        }
//        MobclickAgent.onPause(this); // 不能遗漏
    }

    @CallSuper
    @Override
    protected void onDestroy() {
//        if (mImmersionBar != null) {
//            mImmersionBar.destroy();
//        }
        if (mPresenter != null) {
            mPresenter.removeView();
        }
        super.onDestroy();
    }





    @Override
    public boolean isFastClick() {
        return isFastClick(DEFAULT_TIME_MILLS);
    }

    @Override
    public boolean isFastClick(long mills) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastClickTime <= mills) {
            return true;
        }
        lastClickTime = currentTime;
        return false;
    }

    /**
     * 隐藏状态栏
     */
    public void hideStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 隐藏输入框
     */
    public void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 点击空白区域隐藏软键盘
     *//*
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        try {
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                *//*获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）*//*
                View v = getCurrentFocus();
                if (isShouldHideInput(v, ev)) {
                    hideSoftInput(v.getWindowToken());
                }
            }
            return super.dispatchTouchEvent(ev);
        } catch (IllegalArgumentException l) {
            return false;
        }
    }

    *//**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
     *//*
    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom) {
                *//*点击EditText的事件，忽略它。*//*
                return false;
            } else {
                return true;
            }
        }
        *//*如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点*//*
        return false;
    }

    */

    /**
     * 多种隐藏软件盘方法的其中一种
     *//*
    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }*/
    @Override
    public void jumpToActivity(Class<? extends BaseActivity> aClass) {
        Log.d("jumpToActivity", aClass.getSimpleName());
        Intent intent = new Intent(this, aClass);
        startActivity(intent);
        mActivity.overridePendingTransition(R.anim.act_right_in, R.anim.act_home);
    }


    @Override
    public void jumpToActivity(Class<? extends BaseActivity> aClass, Bundle bundle) {
        Intent intent = new Intent(this, aClass);
        intent.putExtras(bundle);
        startActivity(intent);
        mActivity.overridePendingTransition(R.anim.act_right_in, R.anim.act_home);
    }

    @Override
    public void jumpToActivityAndFinish(Class<? extends BaseActivity> aClass) {
        jumpToActivity(aClass);
        finish();
    }

    @Override
    public void jumpToActivityAndFinish(Class<? extends BaseActivity> aClass, Bundle bundle) {
        jumpToActivity(aClass, bundle);
        finish();
    }

    @Override
    public void jumpToActivityForResult(Class<? extends BaseActivity> aClass, int resultCode) {
        Intent intent = new Intent(this, aClass);
        startActivityForResult(intent, resultCode);
        mActivity.overridePendingTransition(R.anim.act_right_in, R.anim.act_home);
    }

    @Override
    public void jumpToActivityForResult(Class<? extends BaseActivity> aClass, Bundle bundle, int resultCode) {
        Intent intent = new Intent(this, aClass);
        intent.putExtras(bundle);
        startActivityForResult(intent, resultCode);
        mActivity.overridePendingTransition(R.anim.act_right_in, R.anim.act_home);
    }

    //    public void jumpARouter(String path){
//        ARouter.getInstance().build(path).navigation();
//    }
//    public void initTitleBar(int leftId, String text) {
//        TitleBarView v = (TitleBarView) findViewById(R.id.titleBarView);
//        if (v != null) {
//            v.initTitleBar(leftId, -1, text,
//                    new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            // TODO Auto-generated method stub
//                            if (v.getId() == R.id.title_finishTv) {
//                                finish();
//                            }
//                        }
//                    });
//        }
//    }

    /**
     * 结束activity(添加动画)
     */
    @Override
    public void finish() {
        super.finish();
        if (FINISH_ACT) {
            overridePendingTransition(R.anim.act_home, R.anim.act_right_out);
        }
    }

    /**
     * 重写此方法目的是app不随系统字体变化
     *
     * @return Resources
     */
    @Override
    public Resources getResources() {
        AutoSizeCompat.autoConvertDensityOfGlobal((super.getResources()));//如果没有自定义需求用这个方法
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());//此操作耗时某些手机会ANR和卡顿
        }
//        Resources res = super.getResources();
//        Configuration config = new Configuration();
//        config.setToDefaults();
//        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    public void initTitleBar(int leftId, String text) {
        TitleBarView v = (TitleBarView) findViewById(R.id.titleBarView);
        if (v != null) {
            v.initTitleBar(leftId, -1, text,
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            if (v.getId() == R.id.title_finishTv) {
                                onBackPressed();
                            }
                        }
                    });
        }
    }

    public void initTitleBar(int leftIconId, String text, String rightText) {
        TitleBarView v = findViewById(R.id.titleBarView);
        if (v != null) {
            v.initTitleBar(leftIconId, text, rightText,
                    v1 -> {
                        if (v1.getId() == R.id.title_finishTv) {
                            onBackPressed();
                        }
                    });
        }
    }

    public void initTitleBar(int leftIconId, int rightIconId, String text) {
        TitleBarView v = findViewById(R.id.titleBarView);
        if (v != null) {
            v.initTitleBar(leftIconId, rightIconId, text,
                    v1 -> {
                        if (v1.getId() == R.id.title_finishTv) {
                            onBackPressed();
                        }
                    });
        }
    }
}