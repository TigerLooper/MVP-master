package com.my.mvp.network;

import com.google.gson.Gson;
import com.my.mvp.model.bean.BaseBean;

import org.json.JSONObject;

import java.lang.reflect.Type;

public class WDTParse {

    public static <T> T parse(String json, Class<T> tClass) {
        Gson gson = new Gson();
        return gson.fromJson(json, tClass);
    }


    public static BaseBean parseObject(String json, final Type type) {
        BaseBean baseBean = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            String error = jsonObject.getString(BaseBean.JSON_ERROR);
            if (BaseBean.SUCCESS_CODE.equals(error)) {
                Gson gson = new Gson();
                baseBean = gson.fromJson(json, type);
            } else {
                String errorMsg = jsonObject.getString(BaseBean.JSON_MESSAGE);
                baseBean = new BaseBean(error, errorMsg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseBean = new BaseBean("-1", "JSON解析失败");
        }
        return baseBean;
    }
}