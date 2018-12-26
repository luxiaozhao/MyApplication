package com.example.wisdom.partybuilding.start;

import android.content.Intent;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.base.BaseActivity;
import com.example.wisdom.partybuilding.base.BasePresenter;
import com.example.wisdom.partybuilding.mvp.common.MainActivity;

import easypermission.davidinchina.com.easylibrary.EasyPermission;

public class GuideActivity extends BaseActivity  {
    @Override
    protected void initView() {
        init();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();

    }


    /*
     * 动态权限
     * */
    private void init() {
        EasyPermission.with(this).code(101).request();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        EasyPermission.handleResult(this, requestCode, permissions, grantResults);//处理权限申请回调结果
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {

    }
}

