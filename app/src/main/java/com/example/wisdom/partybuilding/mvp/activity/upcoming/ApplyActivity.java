package com.example.wisdom.partybuilding.mvp.activity.upcoming;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.base.BaseActivity;
import com.example.wisdom.partybuilding.base.BasePresenter;
import com.example.wisdom.partybuilding.mvp.activity.mine.AboutUsActivity;

/*
 * 党员申请
 * */
public class ApplyActivity extends BaseActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, ApplyActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {

    }
}
