package com.yiche.baselibrary.util.ARouter;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.yiche.baselibrary.app.BaseApplicaticon;

/**
 * @Description: java类作用描述
 * @Author: yiche_li
 * @CreateDate: 2020/6/18 14:58
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/6/18 14:58
 */

public class ARouterUtil {
    public static void jumpToActivity(String path) {
        ARouter.getInstance()
                .build(path)
                .navigation();
    }


    public static void jumpToActivity(String path, Bundle bundle) {
        ARouter.getInstance()
                .build(path)
                .with(bundle)
                .navigation(BaseApplicaticon.getMyInstance(), new NavCallback() {
                    @Override
                    public void onFound(Postcard postcard) {
                        Log.e("ARouter", "onArrival: 找到了 ");
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        Log.e("ARouter", "onArrival: 找不到了 ");
                    }

                    @Override
                    public void onArrival(Postcard postcard) {
                        Log.e("ARouter", "onArrival: 跳转完了 ");
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        Log.e("ARouter", "onArrival: 被拦截了 ");
                    }
                });
    }

}
