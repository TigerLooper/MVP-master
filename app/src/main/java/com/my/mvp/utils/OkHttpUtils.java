package com.my.mvp.utils;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by lp on 2016/3/28.
 */
public class OkHttpUtils {
    private static final OkHttpClient okHttpClient = new OkHttpClient();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String CHARSET_NAME = "UTF-8";

    static {
        okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
    }

    /**
     * HTTP POST
     * POST提交Json数据
     */
    String post(String url, String json) throws IOException {
        RequestBody requestBody = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /**
     * POST提交键值对
     * 很多时候我们会需要通过POST方式把键值对数据传送到服务器。 OkHttp提供了很方便的方式来做这件事情。
     */
    String post(String url) throws IOException {
        RequestBody formBody = new FormEncodingBuilder()
                .add("platform", "android")
                .add("name", "bug")
                .add("subject", "XXXXXXXXXXXXXXX")
                .build();
        Request request = new Request.Builder().build();
        request.newBuilder().url(url).post(formBody);
        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /**
     * 建议使用这个封装方法
     * POST提交键值对
     * OkHttp官方文档并不建议我们创建多个OkHttpClient，因此全局使用一个
     * enqueue为OkHttp提供的异步方法
     */

    /**
     * 开启异步线程访问网络
     *
     * @param request
     * @param responseCallBack
     */
    public static void execute(Request request, Callback responseCallBack) {
        okHttpClient.newCall(request).enqueue(responseCallBack);
    }

    public static Response execute(Request request) throws IOException {
        return okHttpClient.newCall(request).execute();
    }

    public static void engueue(Request request) {

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonString = response.body().string();
                } else {
                    throw new IOException("Unexpected code " + response);
                }
            }
        });
    }


}
