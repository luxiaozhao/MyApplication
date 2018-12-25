package com.example.wisdom.partybuilding.base;

import android.app.Application;
import android.util.Log;

import com.example.wisdom.partybuilding.R;
import com.orhanobut.hawk.Hawk;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import org.xutils.x;


public class MyApplication extends Application {
    public static BaseActivity mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        initHawk();

        UMConfigure.setLogEnabled(true);
//        分享
        UMConfigure.init(this, "5c19ad27f1f5563dda00003b"//  5c19ad27f1f5563dda00003b
                , "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
        x.Ext.init(this);
        x.Ext.setDebug(false); //输出debug日志，开启会影响性能

        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
//        QbSdk.setDownloadWithoutWifi(true);//非wifi条件下允许下载X5内核
//
//        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
//            @Override
//            public void onCoreInitFinished() {
//
//            }
//
//            @Override
//            public void onViewInitFinished(boolean b) {
//                Log.e("apptbs", " onViewInitFinished is " + b);
//            }
//        };
//
//        QbSdk.setTbsListener(new TbsListener() {
//            @Override
//            public void onDownloadFinish(int i) {
//                Log.d("apptbs", "onDownloadFinish");
//            }
//
//            @Override
//            public void onInstallFinish(int i) {
//                Log.d("apptbs", "onInstallFinish");
//            }
//
//            @Override
//            public void onDownloadProgress(int i) {
//                Log.d("apptbs", "onDownloadProgress:" + i);
//            }
//        });
//
//        QbSdk.initX5Environment(getApplicationContext(), cb);
    }
    {
        //微信
        PlatformConfig.setWeixin("wx5da20be46a1af30b", "43138a1d80f6b01a5d023685bc6e9677");
        //QQ
//        PlatformConfig.setQQZone("1107514649", "jaDIzVjsFJ7GG4LK");
    }

    /**
     * 以键值对的形式存储数据
     */
    private void initHawk() {
        Hawk.init(getApplicationContext()).build();
    }

}