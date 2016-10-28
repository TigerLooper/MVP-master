package com.my.mvp.network;


import com.my.mvp.model.bean.BaseBean;

/**
 * Created by Administrator on 2015/3/18.
 */
public interface WDTResponse {

    void onFailure(String error);

    void onSuccess(BaseBean baseBean);

}
