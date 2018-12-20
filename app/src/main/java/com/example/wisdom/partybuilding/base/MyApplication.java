package com.example.wisdom.partybuilding.base;

import android.app.Application;
import android.util.Log;

import com.example.wisdom.partybuilding.R;
import com.orhanobut.hawk.Hawk;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;

import org.xutils.x;

import easypermission.davidinchina.com.easylibrary.EasyPermission;

public class MyApplication extends Application {
    public static BaseActivity mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        initHawk();


        x.Ext.init(this);
        x.Ext.setDebug(false); //输出debug日志，开启会影响性能

        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.setDownloadWithoutWifi(true);//非wifi条件下允许下载X5内核

        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {

            }

            @Override
            public void onViewInitFinished(boolean b) {
                Log.e("apptbs", " onViewInitFinished is " + b);
            }
        };

        QbSdk.setTbsListener(new TbsListener() {
            @Override
            public void onDownloadFinish(int i) {
                Log.d("apptbs", "onDownloadFinish");
            }

            @Override
            public void onInstallFinish(int i) {
                Log.d("apptbs", "onInstallFinish");
            }

            @Override
            public void onDownloadProgress(int i) {
                Log.d("apptbs", "onDownloadProgress:" + i);
            }
        });

        QbSdk.initX5Environment(getApplicationContext(), cb);
    }


    /**
     * 以键值对的形式存储数据
     */
    private void initHawk() {
        Hawk.init(getApplicationContext()).build();
    }
}