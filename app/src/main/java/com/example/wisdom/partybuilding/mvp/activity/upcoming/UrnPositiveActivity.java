package com.example.wisdom.partybuilding.mvp.activity.upcoming;

import android.content.Context;
import android.content.Intent;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.base.BaseActivity;
import com.example.wisdom.partybuilding.base.BasePresenter;

/*
 *  预备党员转为正式党员
 * */
public class UrnPositiveActivity extends BaseActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_urn_positive);
//    }
public static void start(Context context) {
    Intent intent = new Intent(context, UrnPositiveActivity.class);
    context.startActivity(intent);
}
    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_urn_positive;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {

    }
}
