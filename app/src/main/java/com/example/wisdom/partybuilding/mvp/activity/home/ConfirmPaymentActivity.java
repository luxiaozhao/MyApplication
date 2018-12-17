package com.example.wisdom.partybuilding.mvp.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.base.BaseActivity;
import com.example.wisdom.partybuilding.base.BasePresenter;
import com.example.wisdom.partybuilding.mvp.activity.commonactivity.MainActivity;
import com.example.wisdom.partybuilding.mvp.activity.login.LoginActivity;
import com.example.wisdom.partybuilding.utils.AppManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 * 支付成功
 * */
public class ConfirmPaymentActivity extends BaseActivity {
    @BindView(R.id.confirm_return)
    ImageView confirmReturn;
    @BindView(R.id.confirm_name)
    TextView confirmName;
    @BindView(R.id.confirm_continue_pay)
    TextView confirmContinuePay;
    @BindView(R.id.confirm_back_home)
    TextView confirmBackHome;


    @BindView(R.id.confirm_detail)
    TextView confirmdetail;




    public static void start(Context context) {
        Intent intent = new Intent(context, ConfirmPaymentActivity.class);
        context.startActivity(intent);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_confirm_payment);
//    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_confirm_payment;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.confirm_return, R.id.confirm_continue_pay, R.id.confirm_back_home,R.id.confirm_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.confirm_return:
                finish();
                break;
            case R.id.confirm_continue_pay:
                finish();
                break;
            case R.id.confirm_back_home:
                MainActivity.start(this);
                AppManager.getAppManager().finishActivity(PayPartyFeesActivity.class);
                AppManager.getAppManager().finishActivity();
                break;
            case R.id.confirm_detail:
                DetailActivity.start(this);
                break;
        }
    }
}
