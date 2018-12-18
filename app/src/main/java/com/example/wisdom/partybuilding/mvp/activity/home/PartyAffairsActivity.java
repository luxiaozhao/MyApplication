package com.example.wisdom.partybuilding.mvp.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.base.BaseActivity;
import com.example.wisdom.partybuilding.base.BasePresenter;
import com.example.wisdom.partybuilding.mvp.adapter.DynamicAdapter;
import com.example.wisdom.partybuilding.mvp.adapter.PartyAffairs_Adapter;
import com.example.wisdom.partybuilding.mvp.adapter.Partyknowledge_Adapter;
import com.example.wisdom.partybuilding.mvp.bean.home.DynamicBean;
import com.example.wisdom.partybuilding.mvp.bean.home.PartyknowledgeBean;
import com.example.wisdom.partybuilding.net.URLS;
import com.example.wisdom.partybuilding.utils.ToastUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class PartyAffairsActivity extends BaseActivity {

    @BindView(R.id.dynamic_return)
    ImageView dynamicReturn;
    @BindView(R.id.dynamic_title)
    TextView dynamicTitle;
    @BindView(R.id.dynamic_recycler)
    RecyclerView dynamicRecycler;
    String title;
    private List<PartyknowledgeBean.CarouselmapBean> bwws = new ArrayList<>();
    private PartyAffairs_Adapter dynamicAdapter;

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_party_affairs);
//    }
    public static void start(Context context, String position) {
        Intent intent = new Intent(context, PartyAffairsActivity.class);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        title = getIntent().getStringExtra("position");
        dynamicTitle.setText(title);

        getdata();

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);  //     reverse  layout
//        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        dynamicAdapter = new PartyAffairs_Adapter(this, bwws);//Partyknowledge_Adapter
        dynamicRecycler.setLayoutManager(linearLayoutManager1);
//        homeNewsRecycler.setNestedScrollingEnabled(true);//是否滚动
        dynamicRecycler.setNestedScrollingEnabled(false);

        dynamicRecycler.setLayoutManager(new GridLayoutManager(this, 1));
        dynamicRecycler.setAdapter(dynamicAdapter);
        dynamicAdapter.setOnClickLinstener(new PartyAffairs_Adapter.onClickLinstener() {
            @Override
            public void setOnClick(View view, int position) {
                ToastUtils.getInstance().showTextToast(PartyAffairsActivity.this,"详情页");
            }
        });


    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_dynamic;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {

    }


    @OnClick({R.id.dynamic_return})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dynamic_return:
                finish();
                break;

        }
    }

    private void getdata() {
        OkHttpUtils
                .get()
                .url(URLS.HOME_PARTYKNOWLEDGE)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "这是失败的方法1" + e.toString());

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        String responses = "{carouselmap:" + response + "}";
                        PartyknowledgeBean bean = gson.fromJson(responses, PartyknowledgeBean.class);


                        bwws.addAll( bean.getCarouselmap());
                        dynamicAdapter.notifyDataSetChanged();
                    }
                });


    }

}
