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
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dynamic);
//    }
    String title;

    public static void start(Context context, String position) {
        Intent intent = new Intent(context, DynamicActivity.class);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }


    @Override
    protected void initView() {
        Log.e("TAG","222222");
        ButterKnife.bind(this);

        title = getIntent().getStringExtra("position");
        dynamicTitle.setText(title);

//
//
//            if (position==0){
//                ToastUtils.getInstance().showTextToast(this,"000000000");
//            }else  if (position==0){
//                ToastUtils.getInstance().showTextToast(this,"111111111");
//            }else if (position==1){
//                ToastUtils.getInstance().showTextToast(this,"2222222222");
//            }else if (position==2){
//
//                title="党委新闻";
//
//
//            }else if (position==3){
//                ToastUtils.getInstance().showTextToast(this,"444444444");
//            }else if (position==4){
//                ToastUtils.getInstance().showTextToast(this,"555555555555");
//            }else if (position==5){
//                ToastUtils.getInstance().showTextToast(this,"666666666");
//            }else if (position==6){
//                ToastUtils.getInstance().showTextToast(this,"77777777");
//            }else if (position==7){
//                ToastUtils.getInstance().showTextToast(this,"888888888");
//            }else if (position==8){
//                ToastUtils.getInstance().showTextToast(this,"9999999");
//            } else {
//                ToastUtils.getInstance().showTextToast(this,"没有数据");
//            }



        getdata(title,1,20);


        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);  //     reverse  layout
//        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        dynamicAdapter = new DynamicAdapter(this, bwws);
        dynamicRecycler.setLayoutManager(linearLayoutManager1);
//        homeNewsRecycler.setNestedScrollingEnabled(true);//是否滚动
        dynamicRecycler.setNestedScrollingEnabled(false);

        dynamicRecycler.setLayoutManager(new GridLayoutManager(this, 1));
        dynamicRecycler.setAdapter(dynamicAdapter);
        dynamicAdapter.setOnClickLinstener(new DynamicAdapter.onClickLinstener() {
            @Override
            public void setOnClick(View view, int position) {
                ToastUtils.getInstance().showTextToast(DynamicActivity.this,"详情页");
            }
        });


    }

    private void getdata(String str,int pageIndex,int pageSize) {
        OkHttpUtils
                .get()
                .url(URLS.HOME_DYNAMIC)
                .addParams("itemName",str)
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
