package com.example.wisdom.partybuilding.mvp.activity.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.base.BaseActivity;
import com.example.wisdom.partybuilding.base.BasePresenter;
import com.example.wisdom.partybuilding.mvp.bean.Bean;
import com.example.wisdom.partybuilding.mvp.bean.Bww;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TidingsActivity extends BaseActivity {
    @BindView(R.id.tidings_return)
    ImageView tidingsReturn;
    @BindView(R.id.tidings_recycler)
    RecyclerView tidingsRecycler;
    private NoticeAdapter folderAdapter1;
    private List<Bww> bwws = new ArrayList<>();
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tidings);
//    }

    public static void start(Context context) {
        Intent intent = new Intent(context, TidingsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        getdata();
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        folderAdapter1 = new NoticeAdapter(this, bwws);
        tidingsRecycler.setLayoutManager(linearLayoutManager1);
        tidingsRecycler.setNestedScrollingEnabled(false);
        tidingsRecycler.setLayoutManager(new GridLayoutManager(this, 1));
        tidingsRecycler.setAdapter(folderAdapter1);

        folderAdapter1.setOnClickLinstener(new NoticeAdapter.onClickLinstener() {
            @Override
            public void setOnClick(View view, int position) {

//                TidingsActivity.start(getActivity());
            }
        });

    }
    private void getdata() {
        bwws.clear();
        for (int i = 0; i < 40; i++) {
            Bww bww = new Bww();
            bww.setName("按实际库存你上课经常看见和出口市场卡死啊飒飒阿萨飒飒啊飒飒");
            bww.setShijian("2018年12月12日");
            bwws.add(bww);
        }
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
