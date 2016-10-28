package com.my.mvp.model;


import com.my.mvp.presenter.listener.OnAccountListener;

/**
 * 所有的model都需要继承MvpModel
 * 该Model主要处理账户中心逻辑
 * <p/>
 * Created by lp on 2016/10/27.
 */
public interface AccountModel extends MvpModel {
    void login(String mobile, String password, OnAccountListener listener);

    void register(String mobile, String password, String numCode, OnAccountListener listener);
}
