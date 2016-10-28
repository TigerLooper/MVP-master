package com.my.mvp.model.bean;

/**
 * Created by lp on 2016/10/27.
 * 所有的bean都必须继承这个共有类
 */
public class BaseBean {
    public static final String SUCCESS_CODE = "0";
    public static final String SESSION_TIME_OUT = "1021";
    public static final String ACCOUNT_ERROR = "1020";

    public static final String JSON_ERROR = "error";
    public static final String JSON_MESSAGE = "message";
    public static final String JSON_CONTENT = "content";

    public String error;
    public String message;

    public BaseBean() {
    }

    public boolean isAccountError() {
        return ACCOUNT_ERROR.equals(error);
    }

    public BaseBean(String error, String message) {
        this.error = error;
        this.message = message;
    }

    /**
     * 判断请求是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        return SUCCESS_CODE.equals(error);
    }

    /**
     * 判断session是否超时
     *
     * @return
     */
    public boolean isSessionTimeout() {
        return SESSION_TIME_OUT.equals(error);
    }

}
