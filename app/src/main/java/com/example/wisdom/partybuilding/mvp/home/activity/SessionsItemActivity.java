package com.example.wisdom.partybuilding.mvp.home.activity;

import android.content.Context;
import android.content.Intent;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.base.BaseActivity;
import com.example.wisdom.partybuilding.base.BasePresenter;

public class SessionsItemActivity extends BaseActivity {
    public static void start(Context context) {
        Intent intent = new Intent(context, SessionsItemActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sessions_item;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {

    }
}
