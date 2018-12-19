package com.example.wisdom.partybuilding.mvp.common;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.base.BaseActivity;
import com.example.wisdom.partybuilding.base.BasePresenter;
import com.example.wisdom.partybuilding.mvp.home.activity.LoginActivity;
import com.example.wisdom.partybuilding.mvp.home.fragment.Home_Fragment;
import com.example.wisdom.partybuilding.mvp.mine.fragment.Mine_Fragment;
import com.example.wisdom.partybuilding.mvp.notice.fragment.Notice_Fragment;
import com.example.wisdom.partybuilding.mvp.upcoming.fragment.Upcoming_Fragment;
import com.example.wisdom.partybuilding.net.Contants;
import com.orhanobut.hawk.Hawk;

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
        radioGroup.check(R.id.main_home);
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
                        LoginActivity.start(this);
                    }


                    break;
                case R.id.main_notice:
                    if (Hawk.contains(Contants.loginInformation)) {
                        supportFragmentManager.beginTransaction()
                                .hide(upcoming_Fragment)

                                .hide(mine_Fragment)
                                .hide(home_Fragment)
                                .show(notice_Fragment).commit();
                    } else {
                        LoginActivity.start(this);
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
                        LoginActivity.start(this);
                    }
                    break;
            }
//        } else {
//            supportFragmentManager.beginTransaction()
//                    .hide(mine_Fragment)
//                    .hide(notice_Fragment)
//                    .hide(upcoming_Fragment)
//                    .show(home_Fragment).commit();
//            radioGroup.check(R.id.main_home);
//            ToastUtils.getInstance().showTextToast(this, "请登录");
//        }


    }
}
