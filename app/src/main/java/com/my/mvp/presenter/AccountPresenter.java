package com.my.mvp.presenter;

/**
 * Created by lp on 2016/10/27.
 */
public interface AccountPresenter extends MvpPresenter {
    void login(String mobile, String password);

    void register(String mobile, String password, String numCode);
}
