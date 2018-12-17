package com.example.wisdom.partybuilding.mvp.activity.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.base.BaseActivity;
import com.example.wisdom.partybuilding.base.BasePresenter;
import com.example.wisdom.partybuilding.mvp.activity.adapter.home.RvDividerItemDecoration;
import com.example.wisdom.partybuilding.mvp.bean.home.DetailBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends BaseActivity {
    @BindView(R.id.detail_return)
    ImageView detailReturn;
    @BindView(R.id.detail_recycler)
    RecyclerView detailRecycler;


    public static void start(Context context) {
        Intent intent = new Intent(context, DetailActivity.class);
        context.startActivity(intent);
    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detail);
//    }



    private List<DetailBean> detailBeans=new ArrayList<>();
    @Override
    protected void initView() {
        ButterKnife.bind(this);

        getdata();
//        home33Adapter = new Home33Adapter(DetailActivity.this, detailBeans);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        detailRecycler.setLayoutManager(layoutManager);
//        detailRecycler.setNestedScrollingEnabled(false);
//        detailRecycler.setAdapter(home33Adapter);
//


    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {

    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // TODO: add setContentView(...) invocation
//
//    }

    @OnClick({R.id.detail_return})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.detail_return:
                finish();
                break;

        }
    }

    private void getdata() {
//        private List<DetailBean> detailBeans=new ArrayList<>();


//        for (int j = 0; j < 5; j++) {
//            DetailBean detailBean=new DetailBean();
//            for (int i = 0; i < 10; i++) {
//                DetailBean.XiangqingBean xiangqingBean=new DetailBean.XiangqingBean();
//                xiangqingBean.setShijian("10月份党费");
//                xiangqingBean.setShijian2("2018/08/15");
//                detailBean.setNian("2018年");
//                detailBean.setXiangqing(xiangqingBean);
//
//            }
//            DetailBean.XiangqingBean xiangqingBean=new DetailBean.XiangqingBean();
//            xiangqingBean.setShijian("10月份党费");
//            xiangqingBean.setShijian2("2018/08/15");
//            detailBean.setNian("2018年");
//            detailBean.setXiangqing(xiangqingBean);
//
//            detailBeans.add(detailBean);
//
//
//        }


    }
}
