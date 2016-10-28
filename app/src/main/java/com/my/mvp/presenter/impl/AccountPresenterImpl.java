package com.my.mvp.presenter.impl;


import com.my.mvp.model.AccountModel;
import com.my.mvp.model.bean.User;
import com.my.mvp.model.bean.UserBean;
import com.my.mvp.model.impl.AccountModelImpl;
import com.my.mvp.presenter.AccountPresenter;
import com.my.mvp.presenter.listener.OnAccountListener;
import com.my.mvp.ui.views.LoginView;

/**
 * Created by lp on 2016/10/27.
 */
public class AccountPresenterImpl implements AccountPresenter, OnAccountListener {
    private LoginView mLoginView;//负责传数据和拿数据
    private AccountModel mAccountModel;

    public AccountPresenterImpl(LoginView loginView) {
        this.mLoginView = loginView;
        this.mAccountModel = new AccountModelImpl();
    }

    @Override
    public void login(String mobile, String password) {
        mAccountModel.login(mobile, password, this);

    }

    @Override
    public void register(String mobile, String password, String numCode) {

    }

    //这些方法是将M层请求的数据返回到P层
    @Override
    public void onLoginSuccess(UserBean userBean) {
        mLoginView.loginSuccess(userBean);
    }

    @Override
    public void onLoginError(String eMsg) {
        mLoginView.showError(eMsg);
    }

    @Override
    public void onRegisterSuccess(User user) {

    }

    @Override
    public void onRegisterError(String eMsg) {

    }
}
