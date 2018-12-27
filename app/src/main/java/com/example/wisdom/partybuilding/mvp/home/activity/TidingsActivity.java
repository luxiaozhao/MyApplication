package com.example.wisdom.partybuilding.mvp.home.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.base.BaseActivity;
import com.example.wisdom.partybuilding.base.BasePresenter;
import com.example.wisdom.partybuilding.mvp.bean.home.DynamicBean;
import com.example.wisdom.partybuilding.mvp.common.WebViewCurrencyActivity;
import com.example.wisdom.partybuilding.net.URLS;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class TidingsActivity extends BaseActivity {
    @BindView(R.id.tidings_return)
    ImageView tidingsReturn;
    @BindView(R.id.tidings_recycler)
    RecyclerView tidingsRecycler;
    private NoticeAdapter folderAdapter1;
    private List<DynamicBean.NewsBean> bwws = new ArrayList<>();


    public static void start(Context context) {
        Intent intent = new Intent(context, TidingsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        getdata(1,20);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        folderAdapter1 = new NoticeAdapter(this, bwws);
        tidingsRecycler.setLayoutManager(linearLayoutManager1);
        tidingsRecycler.setNestedScrollingEnabled(false);
        tidingsRecycler.setLayoutManager(new GridLayoutManager(this, 1));
        tidingsRecycler.setAdapter(folderAdapter1);

        folderAdapter1.setOnClickLinstener(new NoticeAdapter.onClickLinstener() {
            @Override
            public void setOnClick(View view, int position) {
                WebViewCurrencyActivity.start(TidingsActivity.this, "党委新闻", URLS.HOME_NEWS_DETAIL+bwws.get(position).getId(),bwws.get(position).getTitile());
//                TidingsActivity.start(getActivity());
            }
        });

    }
    private void getdata(int pageIndex,int pageSize) {
        OkHttpUtils
                .get()
                .url(URLS.HOME_DYNAMIC)
                .addParams("itemName","党委新闻")
                .addParams("pageIndex",pageIndex+"")
                .addParams("pageSize",pageSize+"")
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
                        folderAdapter1.notifyDataSetChanged();
                    }
                });


    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_tidings;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {

    }

    @OnClick({R.id.tidings_return})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tidings_return:
                finish();
                break;

        }
    }
}
