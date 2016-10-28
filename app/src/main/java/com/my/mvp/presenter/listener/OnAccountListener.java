package com.my.mvp.presenter.listener;

import com.my.mvp.model.bean.User;
import com.my.mvp.model.bean.UserBean;

/**
 * Created by lp on 2016/10/27.
 * 目的是将请求的数据回调给present
 */
public interface OnAccountListener {
    void onLoginSuccess(UserBean userBean);

    void onLoginError(String eMsg);

    void onRegisterSuccess(User user);

    void onRegisterError(String eMsg);
}
