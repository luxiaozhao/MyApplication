package com.example.wisdom.partybuilding.utils;

import android.util.Log;

import com.example.wisdom.partybuilding.mvp.bean.SuccessBean;
import com.example.wisdom.partybuilding.net.Contants;
import com.example.wisdom.partybuilding.net.URLS;
import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class Verification {

  private static   boolean aBoolean=false;

    /*
    * 验证是否登陆
    * true 登陆成功
    * false 登陆失败
    * */
    public static boolean getVerificationInformation() {

        if (Hawk.contains(Contants.loginInformation)) {
            SuccessBean successBean = Hawk.get(Contants.loginInformation);
            String sid = successBean.getSid();
            String pid = successBean.getPid();

            Log.e("TAG","-----------sid:"+sid+"--------pid:"+pid);

            OkHttpUtils
                    .get()
                    .url(URLS.REVERIFICATION)
                    .addParams("sid", successBean.getSid())
                    .addParams("pid", successBean.getPid())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            aBoolean = false;

                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Gson gson = new Gson();
//                            bean = gson.fromJson(response, AttestBean.class);

                            aBoolean = true;
                        }
                    });
//            aBoolean = true;
//
//            Log.e("TAG","首页1"+aBoolean);
//
//            aBoolean=true;
            return aBoolean;
        }
        return aBoolean;
    }


}
