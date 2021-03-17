package com.yiche.basemoudle;

import com.yiche.baselibrary.util.JsonUtil;
import com.yiche.baselibrary.util.LogUtil;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @Description: java类作用描述
 * @Author: yiche_li
 * @CreateDate: 2020/5/9 15:10
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/5/9 15:10
 */

public class HttpLogger implements HttpLoggingInterceptor.Logger {
    StringBuilder mMessage = new StringBuilder();
    @Override
    public void log(String message) {
        // 请求或者响应开始
        if (message.startsWith("--> POST")) {
            mMessage.setLength(0);
        }
        if (message.startsWith("--> GET")) {
            mMessage.setLength(0);
        }
        // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
        if ((message.startsWith("{") && message.endsWith("}")) || (message.startsWith("[") && message.endsWith("]"))) {
            message = JsonUtil.formatJson(message);
        }
        mMessage.append(message.concat("\n"));
        LogUtil.d(mMessage);
        // 请求或者响应结束，打印整条日志
    }

}
