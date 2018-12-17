package com.example.wisdom.partybuilding.base;

import android.app.Application;

import com.orhanobut.hawk.Hawk;

public class MyApplication extends Application {
    public static BaseActivity mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        initHawk();
    }

    /**
     * 以键值对的形式存储数据
     */
    private void initHawk() {
        Hawk.init(getApplicationContext()).build();
    }
}
