package com.example.wisdom.partybuilding.mvp.home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.base.BaseActivity;
import com.example.wisdom.partybuilding.base.BasePresenter;
import com.example.wisdom.partybuilding.utils.CommonUtil;
import com.example.wisdom.partybuilding.zxing.activity.CaptureActivity;

import butterknife.BindView;

public class ScanItActivity extends BaseActivity {

    @BindView(R.id.qrCodeText)
    TextView qrCodeText;

    //打开扫描界面请求码
    private int REQUEST_CODE = 0x01;
    //扫描成功返回码
    private int RESULT_OK = 0xA1;

    public static void start(Context context) {
        Intent intent = new Intent(context, ScanItActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        //打开二维码扫描界面
        if (CommonUtil.isCameraCanUse()) {
            Intent intent = new Intent(ScanItActivity.this, CaptureActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        } else {
            Toast.makeText(this, "请打开此应用的摄像头权限！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scan_it;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {

    }

        @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
        if (resultCode == RESULT_OK) { //RESULT_OK = -1
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("qr_scan_result");
            //将扫描出的信息显示出来
            qrCodeText.setText(scanResult);
        }
    }
}
