package com.example.wisdom.partybuilding.mvp.home.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.mvp.bean.SuccessBean;
import com.example.wisdom.partybuilding.mvp.home.fragment.Home_Fragment;
import com.example.wisdom.partybuilding.net.URLS;
import com.example.wisdom.partybuilding.utils.ToastUtils;
import com.example.wisdom.partybuilding.base.BaseActivity;
import com.example.wisdom.partybuilding.base.BasePresenter;
import com.example.wisdom.partybuilding.net.Contants;
import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_accountNumber)
    EditText loginAccountNumber;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_remember_img)
    ImageView loginRememberImg;
    @BindView(R.id.login_landed)
    TextView loginLanded;
    @BindView(R.id.login_backtrack)
    TextView loginBacktrack;
    @BindView(R.id.login_remember_password)
    LinearLayout login_remember_password;

    private boolean aBoolean = true;

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        Glide.with(LoginActivity.this).load(R.mipmap.remember2).into(loginRememberImg);
        aBoolean = false;

        if (Hawk.contains(Contants.rememberAccountAndPassword)) {
            Map<String, String> stringStringMap = Hawk.get(Contants.rememberAccountAndPassword);
            loginAccountNumber.setText(stringStringMap.get("accountNumber"));
            loginPassword.setText(stringStringMap.get("password"));
            Glide.with(LoginActivity.this).load(R.mipmap.remember2).into(loginRememberImg);
            aBoolean = false;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {

    }

    @OnClick({R.id.login_landed, R.id.login_backtrack, R.id.login_remember_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_landed:
                submit();
                if (!aBoolean) {
                    if (Hawk.contains(Contants.rememberAccountAndPassword)) {
                        Hawk.delete(Contants.rememberAccountAndPassword);
                    }
                    Map<String, String> stringStringMap = new HashMap<>();
                    stringStringMap.put("accountNumber", getloginAccountNumber());
                    stringStringMap.put("password", getloginPassword());
                    Hawk.put(Contants.rememberAccountAndPassword, stringStringMap);
                } else {
                    Hawk.delete(Contants.rememberAccountAndPassword);
                }
                geturl();
                break;
            case R.id.login_backtrack:
//                Home_Fragment.start(LoginActivity.this);

                finish();
                break;
            case R.id.login_remember_password:
                if (aBoolean) {
                    ToastUtils.getInstance().showTextToast(LoginActivity.this, "记住密码");
                    Glide.with(LoginActivity.this).load(R.mipmap.remember2).into(loginRememberImg);
                    aBoolean = false;
                } else {
                    ToastUtils.getInstance().showTextToast(LoginActivity.this, "忘记密码");
                    Glide.with(LoginActivity.this).load(R.mipmap.remember).into(loginRememberImg);
                    aBoolean = true;
                }
                break;
        }
    }

    private void geturl() {

        Log.e("TAG", "登陆网址:" + URLS.SEND_MESSAGE + "username:" + getloginAccountNumber() + "---password:" + getloginPassword());
        OkHttpUtils
                .get()
                .url(URLS.SEND_MESSAGE)//    http://119.80.161.8:9999/FHBE/login.ht?username=djys&password=2
                .addParams("username", getloginAccountNumber())
                .addParams("password", getloginPassword())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "登陆失败：" + e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            Gson gson = new Gson();
                            SuccessBean successBean = gson.fromJson(response, SuccessBean.class);
                            String msg = successBean.getMsg();
                            ToastUtils.getInstance().showTextToast(LoginActivity.this, msg);
                            if (successBean.isSuccess()) {
                                Hawk.put(Contants.loginInformation, successBean);
                                Home_Fragment.start(LoginActivity.this);
                                finish();
                            }
                        } catch (Exception e) {
                            Log.e("TAG", "失败:" + e.toString());
                        }
                    }
                });
    }

    /**
     * 判断必填项是否为空
     */
    private void submit() {

        if (TextUtils.isEmpty(getloginAccountNumber())) {
            Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(getloginPassword())) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    //账号
    private String getloginAccountNumber() {
        return loginAccountNumber.getText().toString().trim();
    }

    //密码
    private String getloginPassword() {
        return loginPassword.getText().toString().trim();
    }

}
