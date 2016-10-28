package com.my.mvp.model.impl;


import com.my.mvp.model.AccountModel;
import com.my.mvp.model.bean.UserBean;
import com.my.mvp.network.OkHttpUtil;
import com.my.mvp.presenter.listener.OnAccountListener;
import com.my.mvp.utils.ResultCallback;
import com.squareup.okhttp.Request;

import java.util.HashMap;

/**
 * Created by lp on 2016/10/27.
 */
public class AccountModelImpl implements AccountModel {
    private static final String URI_FILE = "http://img.hb.aicdn.com/d2024a8a998c8d3e4ba842e40223c23dfe1026c8bbf3-OudiPA_fw580";

    //在这个类里面处理网络请求，OnAccountListener对象是将值带出来
    @Override
    public void login(String mobile, String password, final OnAccountListener listener) {
        HashMap hashMap = new HashMap();
        hashMap.put("userPhone", mobile);
        hashMap.put("psw", password);

        OkHttpUtil.getInstance().postStringAsyn(URI_FILE, new ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(Object response) {
                listener.onLoginSuccess((UserBean) response);
            }
        }, hashMap);
    }

    @Override
    public void register(String mobile, String password, String numCode, OnAccountListener listener) {

    }

}
