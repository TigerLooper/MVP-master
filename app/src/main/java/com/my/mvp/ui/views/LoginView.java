package com.my.mvp.ui.views;


import com.my.mvp.model.bean.UserBean;

/**
 * Created by geek on 2015/8/7.
 */
public interface LoginView extends AccountView {
    void loginSuccess(UserBean userBean);

    void showError(String errorMsg);
}
