package com.yiche.baselibrary.javabean.base;

import java.io.Serializable;

/**
 * 类说明：返回基础实体类
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @time 2019/6/10 11:39
 */
public class BaseBean<T> implements Serializable {
    private String error_no;
    private String error_msg;
    private int code;
    private T result;

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public String getError_no() {
        return error_no;
    }

    public void setError_no(String error_no) {
        this.error_no = error_no;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}