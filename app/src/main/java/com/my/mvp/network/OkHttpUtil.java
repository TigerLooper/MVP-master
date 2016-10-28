package com.my.mvp.network;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.my.mvp.model.bean.BaseBean;
import com.my.mvp.utils.LogUtils;
import com.my.mvp.utils.NetUtils;
import com.my.mvp.utils.OkHttpClientManager;
import com.my.mvp.utils.ResultCallback;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by lp on 2016/4/28.
 */
public class OkHttpUtil {

    private static OkHttpClient sOkHttpClient;
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    private Gson mGson;
    private Handler mDelivery;
    private static OkHttpUtil mInstance;

    public OkHttpUtil() {
        mGson = new Gson();
        mDelivery = new Handler();
        sOkHttpClient = new OkHttpClient();
    }

    //获取本类对象
    public static OkHttpUtil getInstance() {
        if (mInstance == null) {
            synchronized (OkHttpClientManager.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpUtil();
                }
            }
        }
        return mInstance;
    }

    /**
     * 异步的post请求
     * 功能：可以上传string和多文件上传
     *
     * @param mUrl
     * @param mMap
     * @param list
     * @param type
     */
    public void postStringAndFilesAysc(Context context, final Type type, List<String> list, Map<String, String> mMap, String mUrl, final WDTResponse wdtResponse) {
        LogUtils.i(mMap.toString());
        LogUtils.i(list.toString());
        LogUtils.i(mUrl);
        if (!NetUtils.isNetworkAvailable(context)) {
            wdtResponse.onFailure("网络无法连接，请检查网络连接");
            return;
        }
        MultipartBuilder multipartBuilder = new MultipartBuilder().type(MultipartBuilder.FORM);
        for (String key : mMap.keySet()) {
            multipartBuilder.addFormDataPart(key, mMap.get(key));
        }
        for (String path : list) {
            multipartBuilder.addFormDataPart("upLoad", null, RequestBody.create(MEDIA_TYPE_PNG, new File(path)));
        }
        RequestBody mRequestBody = multipartBuilder.build();
        Request request = new Request.Builder()
                .url(mUrl)//地址
                .post(mRequestBody)//添加请求体
                .build();

        sOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                RequestBody requestBody = request.body();
                String error = requestBody.toString();
                wdtResponse.onFailure(error);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String result = response.body().string();//jsonString
                LogUtils.i(result);//打印结果
                BaseBean baseBean = WDTParse.parseObject(result, type);//得到bean对象
                wdtResponse.onSuccess(baseBean);
            }
        });
    }

    /**
     * 异步的post请求
     * 功能：只能上传String
     *
     * @param url
     * @param callback
     * @param params
     */
    public void postStringAsyn(String url, final ResultCallback callback, Map<String, String> params) {
        Param[] paramsArr = mapParams(params);
        Request request = buildPostRequest(url, paramsArr);
        deliveryResult(callback, request);
    }

    private Param[] mapParams(Map<String, String> params) {
        if (params == null) return new Param[0];
        int size = params.size();
        Param[] res = new Param[size];
        Set<Map.Entry<String, String>> entries = params.entrySet();
        int i = 0;
        for (Map.Entry<String, String> entry : entries) {
            res[i++] = new Param(entry.getKey(), entry.getValue());
        }
        return res;
    }

    private void deliveryResult(final ResultCallback callback, Request request) {
        sOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Request request, final IOException e) {
                sendFailedStringCallback(request, e, callback);
            }

            @Override
            public void onResponse(final Response response) {
                try {
                    final String string = response.body().string();
                    LogUtils.i(string);//打印请求结果
                    if (callback.mType == String.class) {
                        sendSuccessResultCallback(string, callback);
                    } else {
                        Object o = mGson.fromJson(string, callback.mType);
                        sendSuccessResultCallback(o, callback);
                    }
                } catch (IOException e) {
                    sendFailedStringCallback(response.request(), e, callback);
                } catch (com.google.gson.JsonParseException e) {
                    sendFailedStringCallback(response.request(), e, callback);
                }

            }
        });
    }

    private void sendFailedStringCallback(final Request request, final Exception e, final ResultCallback callback) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null)
                    callback.onError(request, e);
            }
        });
    }

    private void sendSuccessResultCallback(final Object object, final ResultCallback callback) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onResponse(object);
                }
            }
        });
    }


    private Request buildPostRequest(String url, Param[] params) {
        if (params == null) {
            params = new Param[0];
        }
        FormEncodingBuilder builder = new FormEncodingBuilder();
        for (Param param : params) {
            builder.add(param.key, param.value);
        }
        RequestBody requestBody = builder.build();
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
    }

    public static class Param {
        public Param(String key, String value) {
            this.key = key;
            this.value = value;
        }

        String key;
        String value;
    }
}
