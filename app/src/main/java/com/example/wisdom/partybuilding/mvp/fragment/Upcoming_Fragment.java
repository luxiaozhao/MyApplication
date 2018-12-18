package com.example.wisdom.partybuilding.mvp.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.wisdom.partybuilding.Main2Activity;
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

        upcomingAdapter = new UpcomingAdapter(getActivity(), beans);
        upcoming_recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        upcoming_recycler.setNestedScrollingEnabled(false);
        upcoming_recycler.setAdapter(upcomingAdapter);

        upcomingAdapter.setOnClickLinstener(new UpcomingAdapter.onClickLinstener() {
            @Override
            public void setOnClick(View view, final int position) {
                switch (position) {
                    case 0://积极分子列为发展对象
                        Main2Activity.start(getActivity());

//                        GrowingActivity.start(getActivity());
                        break;
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


            Bean1 bean1 = new Bean1();
            bean1.setName1("赵健");
            bean1.setName2("孟祥超");
            bean1.setLeixing("积极分子列为发展对象");
            bean1.setShijian("2018-12-1" );
            beans.add(bean1);


        Bean1 bean2 = new Bean1();
        bean2.setName1("赵健");
        bean2.setName2("孟祥超");
        bean2.setLeixing("申请入党");
        bean2.setShijian("2018-12-1" );
        beans.add(bean2);


        Bean1 bean3 = new Bean1();
        bean3.setName1("赵健");
        bean3.setName2("孟祥超");
        bean3.setLeixing("预备党员转为正式党员");
        bean3.setShijian("2018-12-1" );
        beans.add(bean3);


        Bean1 bean4 = new Bean1();
        bean4.setName1("赵健");
        bean4.setName2("孟祥超");
        bean4.setLeixing("组织关系转出");
        bean4.setShijian("2018-12-1" );
        beans.add(bean4);


        Bean1 bean5 = new Bean1();
        bean5.setName1("赵健");
        bean5.setName2("孟祥超");
        bean5.setLeixing("组织关系转入");
        bean5.setShijian("2018-12-1" );
        beans.add(bean5);

        Bean1 bean6 = new Bean1();
        bean6.setName1("赵健");
        bean6.setName2("孟祥超");
        bean6.setLeixing("发展对象转为预备党员");
        bean6.setShijian("2018-12-1" );
        beans.add(bean1);


    }
}
