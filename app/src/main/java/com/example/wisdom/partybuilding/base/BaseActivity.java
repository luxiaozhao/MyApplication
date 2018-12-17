package com.example.wisdom.partybuilding.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.wisdom.partybuilding.utils.AppManager;
import com.orhanobut.hawk.Hawk;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity <P extends BasePresenter> extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    protected View root;
    /**
     * 代理类
     */

    protected AppCompatActivity activity;
    protected P mPresenter;
    protected LayoutInflater mInflate;
    private Unbinder mUnBinder = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        Hawk.init(this).build();
        this.setStatusBarFullTransparent();
        AppManager.getAppManager().addActivity(this);




        MyApplication.mContext=this;
        root = LayoutInflater.from(this).inflate(getLayoutId(), null);
        mInflate = LayoutInflater.from(this);
        this.setContentView(root);
        mUnBinder = ButterKnife.bind(this);
        activity = this;
        initView();
        mPresenter = getPresenter();
        initDataAndEvent();
        root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                root.setFocusable(true);
                root.setFocusableInTouchMode(true);
                root.requestFocus();
                return false;
            }
        });
    }

    protected abstract void initView();
    /**
     * 获取contentViewId
     *
     * @return
     */
    protected abstract int getLayoutId();

    protected abstract P getPresenter();

    /**
     * 初始化数据和事件
     */
    protected abstract void initDataAndEvent();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
        AppManager.getAppManager().finishActivity(this);
    }

    /**
     * 全透状态栏
     */
    protected void setStatusBarFullTransparent() {
        if (Build.VERSION.SDK_INT >= 21) {//21表示5.0
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
}
