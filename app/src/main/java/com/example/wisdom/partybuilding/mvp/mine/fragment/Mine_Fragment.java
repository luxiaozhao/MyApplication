package com.example.wisdom.partybuilding.mvp.mine.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.mvp.common.WebViewCurrencyActivity;
import com.example.wisdom.partybuilding.mvp.bean.SuccessBean;
import com.example.wisdom.partybuilding.mvp.bean.login.AttestBean;
import com.example.wisdom.partybuilding.mvp.home.fragment.Home_Fragment;
import com.example.wisdom.partybuilding.net.URLS;
import com.example.wisdom.partybuilding.utils.ToastUtils;
import com.example.wisdom.partybuilding.mvp.mine.activity.ChangePasswordActivity;
import com.example.wisdom.partybuilding.mvp.mine.activity.AboutUsActivity;
import com.example.wisdom.partybuilding.base.BaseFragment;
import com.example.wisdom.partybuilding.base.IPresenter;
import com.example.wisdom.partybuilding.net.Contants;
import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

public class Mine_Fragment extends BaseFragment {
    @BindView(R.id.mine_name)
    TextView mineName;
    @BindView(R.id.mine_position)
    TextView minePosition;
    @BindView(R.id.mine_data)
    LinearLayout mineData;
    @BindView(R.id.mine_password)
    LinearLayout minePassword;
    @BindView(R.id.mine_course)
    LinearLayout mineCourse;
    @BindView(R.id.mine_examination)
    LinearLayout mineExamination;
    @BindView(R.id.mine_about)
    LinearLayout mineAbout;
    @BindView(R.id.mine_cache)
    LinearLayout mineCache;
    @BindView(R.id.mine_signout)
    TextView mineSignout;
    Unbinder unbinder;

    @Override
    protected void initEventAndData() {


        try {
            SuccessBean successBean = Hawk.get(Contants.loginInformation);
            mineName.setText(successBean.getUsername());
            minePosition.setText(successBean.getOrg());

        } catch (Exception e) {
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_mine_fragment_layout;
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.mine_data, R.id.mine_password, R.id.mine_course, R.id.mine_examination, R.id.mine_about, R.id.mine_cache, R.id.mine_signout})
    public void onViewClicked(View view) {
        SuccessBean successBean = Hawk.get(Contants.loginInformation);
        switch (view.getId()) {
            case R.id.mine_data://个人资料
//                MaterialActivity.start(getActivity());
                getmaterialur();
                break;
            case R.id.mine_password://修改密码
                ChangePasswordActivity.start(getActivity());
                break;
            case R.id.mine_course:
                WebViewCurrencyActivity.start(getActivity(), "我的课程", URLS.ONLINEEXAM + successBean.getSid() + "&pid=" + successBean.getPid(),"hide");
                break;
            case R.id.mine_examination://我的考试
                WebViewCurrencyActivity.start(getActivity(), "我的考试", URLS.ONLINEEXAM + successBean.getSid() + "&pid=" + successBean.getPid(),"hide");
                break;
            case R.id.mine_about://关于我们
                AboutUsActivity.start(getActivity());
                break;
            case R.id.mine_cache:
                ToastUtils.getInstance().showTextToast(getActivity(), "清理缓存");
                break;
            case R.id.mine_signout:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setTitle("确定退出智慧党建吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (Hawk.contains(Contants.loginInformation)) {
                            Hawk.delete(Contants.loginInformation);
                            //返回首页
                            Home_Fragment.start(getActivity());
                            getActivity().finish();
                        }

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();

                break;
        }
    }

    private void getmaterialur() {
        if (Hawk.contains(Contants.loginInformation)) {
            final SuccessBean successBean = Hawk.get(Contants.loginInformation);
            OkHttpUtils
                    .get()
                    .url(URLS.REVERIFICATION)
                    .addParams("sid", successBean.getSid())
                    .addParams("pid", successBean.getPid())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.e("TAG", "这是失败的方法1" + e.toString());
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Gson gson = new Gson();
                            AttestBean bean = gson.fromJson(response, AttestBean.class);
                            if (bean.isCode()) {
//                                http://192.168.1.199:9999/FHBE/personinfo.jsp?sid=FBFBEF8C665116A47DAE032AC6567D86&pid=14272319760723301X
                                String weburl = "http://192.168.1.199:9999/FHBE/personinfo.jsp?sid=" + successBean.getSid() + "&pid=" + successBean.getPid();
                                WebViewCurrencyActivity.start(getActivity(), "个人资料", weburl,"titile");
                            }
                        }
                    });
        }
    }

}
