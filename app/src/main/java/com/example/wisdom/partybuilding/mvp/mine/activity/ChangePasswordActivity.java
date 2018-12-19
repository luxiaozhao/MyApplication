package com.example.wisdom.partybuilding.mvp.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.mvp.bean.SuccessBean;
import com.example.wisdom.partybuilding.mvp.bean.amendPasswordBean;
import com.example.wisdom.partybuilding.utils.ToastUtils;

import com.example.wisdom.partybuilding.base.BaseActivity;
import com.example.wisdom.partybuilding.base.BasePresenter;
import com.example.wisdom.partybuilding.net.Contants;
import com.example.wisdom.partybuilding.net.URLS;
import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class ChangePasswordActivity extends BaseActivity {
    @BindView(R.id.changepasswordactivity_used_password)
    EditText changepasswordactivityUsedPassword;
    @BindView(R.id.changepasswordactivity_fresh_password)
    EditText changepasswordactivityFreshPassword;
    @BindView(R.id.changepasswordactivity_confirm_modify)
    TextView changepasswordactivityConfirmModify;
    @BindView(R.id.changepasswordactivity_return)
    ImageView changepasswordactivityReturn;

    public static void start(Context context) {
        Intent intent = new Intent(context, ChangePasswordActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_password;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {

    }


    @OnClick({R.id.changepasswordactivity_return, R.id.changepasswordactivity_confirm_modify})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.changepasswordactivity_return:
                finish();
                break;
            case R.id.changepasswordactivity_confirm_modify:
                submit();
                if (Hawk.contains(Contants.loginInformation)) {
                    SuccessBean successBean = Hawk.get(Contants.loginInformation);
                    OkHttpUtils
                            .get()
                            .url(URLS.CHANGEPASSWORD)//    http://119.80.161.8:9999/FHBE/login.ht?username=djys&password=2
                            .addParams("sid", successBean.getSid())
                            .addParams("pid", successBean.getPid())
                            .addParams("oldpassword", getused())//旧
                            .addParams("newpassword", getfresh())//新
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    Log.e("TAG", "登陆失败：" + e.toString());
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Gson gson = new Gson();
                                    amendPasswordBean bean = gson.fromJson(response, amendPasswordBean.class);
                                    if (bean.isCode()) {
                                        ToastUtils.getInstance().showTextToast(ChangePasswordActivity.this, bean.getMsg());
                                        finish();
                                    } else {
                                        ToastUtils.getInstance().showTextToast(ChangePasswordActivity.this, bean.getMsg());
                                    }
                                }
                            });
                }else {
                    Log.e("TAG","需要跳转到登陆界面");
                }
                break;
        }
    }

    /**
     * 判断必填项是否为空
     */
    private void submit() {
        if (TextUtils.isEmpty(getused())) {
            Toast.makeText(this, "旧密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(getfresh())) {
            Toast.makeText(this, "请输入新密码", Toast.LENGTH_SHORT).show();
            return;
        }


    }

    /**
     * 原密码
     */
    private String getused() {
        return changepasswordactivityUsedPassword.getText().toString().trim();
    }

    /**
     * 新密码
     */
    private String getfresh() {
        return changepasswordactivityFreshPassword.getText().toString().trim();
    }

}
