package com.example.wisdom.partybuilding.net;

import com.example.wisdom.partybuilding.base.MyApplication;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;


public class HttpUtils implements IHttp {
    private static HttpUtils mHttpUtils;


    public synchronized static HttpUtils getInstance() {
        if (mHttpUtils == null)
            mHttpUtils = new HttpUtils();
        return mHttpUtils;
    }

    // http://www.qq.com?name=xxx&pwd=xxx;
    @Override
    public <T> void get(String url, Map<String, String> params, final MyCallBack<T> callBack) {
        if (params != null) {
            StringBuffer sb = new StringBuffer(url);
            sb.append("?");
            Set<String> set = params.keySet();
            for (String key : set) {
                String value = params.get(key);
                sb.append(key).append("=").append(value).append("&");
            }
            url = sb.deleteCharAt(sb.length() - 1).toString();
        }
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, final Exception e, int id) {
                        MyApplication.mContext.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                callBack.onFaile(e.getMessage());
                            }
                        });
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        final String jsonData = response;
                        MyApplication.mContext.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    callBack.onSuccess(getGeneric(jsonData, callBack));
                                } catch (Exception i) {

                                }
                            }
                        });
                    }
                });

    }

    /**
     * Gson解析 获取实体对象
     * 通过反射机制获取当前接口实现类的类型
     * 根据类获取当前类对应的对象
     *
     * @param <T>
     * @param jsonData
     * @param callback
     * @return
     */
    private <T> T getGeneric(String jsonData, MyCallBack<T> callback) {
        Type[] types = callback.getClass().getGenericInterfaces();
        Type[] parameterTypes = ((ParameterizedType) types[0]).getActualTypeArguments();
        Type type = parameterTypes[0];
        Gson gson = new Gson();
        T t = gson.fromJson(jsonData, type);
        return t;
    }


    @Override
    public <T> void post(String url, Map<String, String> params, final MyCallBack<T> callBack) {
       OkHttpUtils.post()
               .url(url)
               .params(params)
               .build()
               .execute(new StringCallback() {
                   @Override
                   public void onError(Call call, final Exception e, int id) {
                       MyApplication.mContext.runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               callBack.onFaile(e.getMessage());
                           }
                       });
                   }

                   @Override
                   public void onResponse(String response, int id) {
                       try {
                           final String jsonData = response;
                           MyApplication.mContext.runOnUiThread(new Runnable() {
                               @Override
                               public void run() {
                                   try {
                                       callBack.onSuccess(getGeneric(jsonData, callBack));
                                   } catch (Exception i) {

                                   }

                               }
                           });
                       } catch (Exception io) {

                       }
                   }
               });


    }
}
