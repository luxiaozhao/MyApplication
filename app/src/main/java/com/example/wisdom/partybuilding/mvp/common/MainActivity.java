package com.example.wisdom.partybuilding.mvp.common;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.aurora.ExampleUtil;
import com.example.wisdom.partybuilding.base.BaseActivity;
import com.example.wisdom.partybuilding.base.BasePresenter;
import com.example.wisdom.partybuilding.mvp.home.activity.LoginActivity;
import com.example.wisdom.partybuilding.mvp.home.fragment.Home_Fragment;
import com.example.wisdom.partybuilding.mvp.mine.fragment.Mine_Fragment;
import com.example.wisdom.partybuilding.mvp.notice.fragment.Notice_Fragment;
import com.example.wisdom.partybuilding.mvp.upcoming.fragment.Upcoming_Fragment;
import com.example.wisdom.partybuilding.net.Contants;
import com.example.wisdom.partybuilding.utils.ToastUtils;
import com.orhanobut.hawk.Hawk;

import cn.jpush.android.api.JPushInterface;
import easypermission.davidinchina.com.easylibrary.EasyPermission;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private FragmentTransaction fragmentTransaction;
    private FragmentManager supportFragmentManager;
    private Home_Fragment home_Fragment;
    private Upcoming_Fragment upcoming_Fragment;
    private Notice_Fragment notice_Fragment;
    private Mine_Fragment mine_Fragment;
    private RadioGroup radioGroup;


    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        registerMessageReceiver();  // used for receive msg

        getCompetence();

        JPushInterface.setDebugMode(false);
        try {
            JPushInterface.init(getApplicationContext());
        } catch (Exception e) {
        }

        home_Fragment = new Home_Fragment();
        upcoming_Fragment = new Upcoming_Fragment();
        notice_Fragment = new Notice_Fragment();
        mine_Fragment = new Mine_Fragment();


        supportFragmentManager = getSupportFragmentManager();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.conta, home_Fragment, "0")
                .add(R.id.conta, upcoming_Fragment, "2")
                .add(R.id.conta, notice_Fragment, "3")
                .add(R.id.conta, mine_Fragment, "4")
                .commit();
        radioGroup = (RadioGroup) findViewById(R.id.radio);

        radioGroup.setOnCheckedChangeListener(this);
        radioGroup.check(R.id.main_home);//选中状态
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {

//        /**
//         * 网络请求
//         *
//         */
//        HttpUtils.getInstance().get(URLS.HTTP, null, new MyCallBack<String>() {
//            @Override
//            public void onSuccess(String s) {
//
//            }
//
//            @Override
//            public void onFaile(String msg) {
//
//            }
//        });
    }


    private boolean aBoolean=true;
    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {


//        if (Hawk.contains(Contants.loginInformation)) {
        switch (checkedId) {
            case R.id.main_home:
                supportFragmentManager.beginTransaction()
                        .hide(mine_Fragment)
                        .hide(notice_Fragment)
                        .hide(upcoming_Fragment)
                        .show(home_Fragment).commit();

                break;
            case R.id.main_upcoming:

                if (Hawk.contains(Contants.loginInformation)) {
                    supportFragmentManager.beginTransaction()
                            .hide(home_Fragment)
                            .hide(notice_Fragment)
                            .hide(mine_Fragment)
                            .show(upcoming_Fragment).commit();
                } else {

                    if (aBoolean){
                        LoginActivity.start(this);
                        aBoolean=false;
                    }else {
                        aBoolean=true;
                    }
                    Log.e("TAH","1111111111111111"+aBoolean);
                }
/*
12-26 11:30:12.883 901-1261/? E/InputReader: QEEXO fs_classify_touch NULL, not calling FingerSense
12-26 11:30:19.727 901-1261/? E/InputReader: QEEXO fs_classify_touch NULL, not calling FingerSense
*/

                break;
            case R.id.main_notice:
                if (Hawk.contains(Contants.loginInformation)) {
                    supportFragmentManager.beginTransaction()
                            .hide(upcoming_Fragment)

                            .hide(mine_Fragment)
                            .hide(home_Fragment)
                            .show(notice_Fragment).commit();
                } else {

                    if (aBoolean){
                        LoginActivity.start(this);
                        aBoolean=false;
                    }else {
                        aBoolean=true;
                    }

                }
                break;
            case R.id.main_mine:
                if (Hawk.contains(Contants.loginInformation)) {
                    supportFragmentManager.beginTransaction()
                            .hide(upcoming_Fragment)
                            .hide(notice_Fragment)
                            .hide(home_Fragment)
                            .show(mine_Fragment).commit();
                } else {

                    if (aBoolean){
                        LoginActivity.start(this);
                        aBoolean=false;
                    }else {
                        aBoolean=true;
                    }
                }
                break;
        }

    }


    @Override
    protected void onRestart() {
        super.onRestart();

        if (!Hawk.contains(Contants.loginInformation)) {
            radioGroup.check(R.id.main_home);//选中状态
            aBoolean=true;
        }

    }

    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
    public static boolean isForeground = false;


    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }

    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    if (!ExampleUtil.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                    }
//                    setCostomMsg(showMsg.toString());
                }
            } catch (Exception e) {
            }
        }
    }

//    private void setCostomMsg(String msg) {
////        if (null != msgText) {
////            msgText.setText(msg);
////            msgText.setVisibility(android.view.View.VISIBLE);
////        }
//    }

    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;

    //点击两次返回键退出应用
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void getCompetence() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        EasyPermission.handleResult(this, requestCode, permissions, grantResults);//处理权限申请回调结果

    }

}
