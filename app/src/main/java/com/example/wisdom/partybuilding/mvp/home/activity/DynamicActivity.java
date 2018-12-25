package com.example.wisdom.partybuilding.mvp.home.activity;

import android.content.Context;
import android.content.Intent;
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
import com.example.wisdom.partybuilding.mvp.bean.home.DynamicBean;
import com.example.wisdom.partybuilding.mvp.common.WebViewCurrencyActivity;
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

public class DynamicActivity extends BaseActivity {

    @BindView(R.id.dynamic_return)
    ImageView dynamicReturn;
    @BindView(R.id.dynamic_title)
    TextView dynamicTitle;
    @BindView(R.id.dynamic_recycler)
    RecyclerView dynamicRecycler;

    private DynamicAdapter dynamicAdapter;
    private List<DynamicBean.NewsBean> bwws = new ArrayList<>();

    String title;

    public static void start(Context context, String position) {
        Intent intent = new Intent(context, DynamicActivity.class);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        title = getIntent().getStringExtra("position");
        dynamicTitle.setText(title);
        getdata(title, 1, 20);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        dynamicAdapter = new DynamicAdapter(this, bwws);
        dynamicRecycler.setLayoutManager(linearLayoutManager1);
        dynamicRecycler.setNestedScrollingEnabled(false);
        dynamicRecycler.setLayoutManager(new GridLayoutManager(this, 1));
        dynamicRecycler.setAdapter(dynamicAdapter);
        dynamicAdapter.setOnClickLinstener(new DynamicAdapter.onClickLinstener() {
            @Override
            public void setOnClick(View view, int position) {
                if (bwws.get(position).getType().equals("党委新闻")) {
                    WebViewCurrencyActivity.start(activity, bwws.get(position).getType(), URLS.HOME_NEWS_DETAIL + bwws.get(position).getId());
                } else if (bwws.get(position).getType().equals("基层交流")) {
                    WebViewCurrencyActivity.start(activity, bwws.get(position).getType(), URLS.HOME_BASICLEVEL_DETAIL + bwws.get(position).getId());
                } else if (bwws.get(position).getType().equals("学习园地")) {
                    WebViewCurrencyActivity.start(activity, bwws.get(position).getType(), URLS.HOME_LEARNING_DETAIL + bwws.get(position).getId());
                }
            }
        });
    }

    private void getdata(String str, int pageIndex, int pageSize) {
        OkHttpUtils
                .get()
                .url(URLS.HOME_DYNAMIC)
                .addParams("itemName", str)
                .addParams("pageIndex", pageIndex + "")
                .addParams("pageSize", pageSize + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "这是失败的方法1" + e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        DynamicBean bean = gson.fromJson(response, DynamicBean.class);
                        bwws.addAll(bean.getNews());
                        dynamicAdapter.notifyDataSetChanged();
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
}