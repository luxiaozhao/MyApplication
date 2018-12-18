package com.example.wisdom.partybuilding.mvp.activity.upcoming;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.base.BaseActivity;
import com.example.wisdom.partybuilding.base.BasePresenter;

/*
 * 积极分子列为发展对象
 * */
public class GrowingActivity extends BaseActivity {

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_growing);
//    }
    public static void start(Context context) {
        Intent intent = new Intent(context, GrowingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_growing;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {

    }
}
