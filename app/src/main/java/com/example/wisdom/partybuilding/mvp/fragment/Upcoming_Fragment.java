package com.example.wisdom.partybuilding.mvp.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.base.BaseFragment;
import com.example.wisdom.partybuilding.base.IPresenter;
import com.example.wisdom.partybuilding.mvp.activity.upcoming.ApplyActivity;
import com.example.wisdom.partybuilding.mvp.activity.upcoming.GrowingActivity;
import com.example.wisdom.partybuilding.mvp.activity.upcoming.MakeReadyActivity;
import com.example.wisdom.partybuilding.mvp.activity.upcoming.TransferActivity;
import com.example.wisdom.partybuilding.mvp.activity.upcoming.TransferOutActivity;
import com.example.wisdom.partybuilding.mvp.activity.upcoming.UrnPositiveActivity;
import com.example.wisdom.partybuilding.mvp.adapter.UpcomingAdapter;
import com.example.wisdom.partybuilding.mvp.bean.Bean1;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Upcoming_Fragment extends BaseFragment {

    @BindView(R.id.upcoming_recycler)
    RecyclerView upcoming_recycler;
    private UpcomingAdapter upcomingAdapter;


    private List<Bean1> beans = new ArrayList<>();


    @Override
    protected void initEventAndData() {

        getdata();


        for (int i = 0; i < 20; i++) {
            Bean1 bean1 = new Bean1();
            bean1.setName1("赵健");
            bean1.setName2("孟祥超");
            bean1.setLeixing("发展对象转为预备党员");
            bean1.setShijian("2018-12-1" + i);
            beans.add(bean1);
        }


        upcomingAdapter = new UpcomingAdapter(getActivity(), beans);
        upcoming_recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        upcoming_recycler.setNestedScrollingEnabled(false);
        upcoming_recycler.setAdapter(upcomingAdapter);

        upcomingAdapter.setOnClickLinstener(new UpcomingAdapter.onClickLinstener() {
            @Override
            public void setOnClick(View view, final int position) {

                switch (position) {
                    case 1://申请入党
                        ApplyActivity.start(getActivity());
                        break;
                    case 2://预备党员转为正式党员
                        UrnPositiveActivity.start(getActivity());
                        break;
                    case 3://组织关系转出
                        TransferOutActivity.start(getActivity());
                        break;
                    case 4://组织关系转入
                        TransferActivity.start(getActivity());
                        break;
                    case 5://发展对象转为预备党员
                        MakeReadyActivity.start(getActivity());
                        break;
                    case 6://积极分子列为发展对象
                        GrowingActivity.start(getActivity());
                        break;
                }



            }
        });


    }


    @Override
    protected int getLayoutId() {
        return R.layout.home_upcoming_fragment_layout;
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }


    private void getdata() {


    }
}
