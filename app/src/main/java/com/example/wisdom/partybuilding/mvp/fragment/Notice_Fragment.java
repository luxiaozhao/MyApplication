package com.example.wisdom.partybuilding.mvp.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.base.BaseFragment;
import com.example.wisdom.partybuilding.base.IPresenter;
import com.example.wisdom.partybuilding.mvp.activity.adapter.home.NoticeAdapter;
import com.example.wisdom.partybuilding.mvp.activity.adapter.home.TidingsActivity;
import com.example.wisdom.partybuilding.mvp.bean.Bean;
import com.example.wisdom.partybuilding.mvp.bean.Bww;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/*
 *  通知
 * */

public class Notice_Fragment extends BaseFragment {
    @BindView(R.id.notice_recycler)
    RecyclerView noticeRecycler;
    Unbinder unbinder;
    private List<Bww> bwws = new ArrayList<>();
    private NoticeAdapter folderAdapter1;

    @Override
    protected void initEventAndData() {

        getdata();


//        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);


        folderAdapter1 = new NoticeAdapter(getActivity(), bwws);
        noticeRecycler.setLayoutManager(linearLayoutManager1);
        noticeRecycler.setNestedScrollingEnabled(false);
        noticeRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        noticeRecycler.setAdapter(folderAdapter1);

        folderAdapter1.setOnClickLinstener(new NoticeAdapter.onClickLinstener() {
            @Override
            public void setOnClick(View view, int position) {

//                TidingsActivity.start(getActivity());

            }
        });


    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_notice_fragment_layout;
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

    private void getdata() {
        bwws.clear();
        for (int i = 0; i < 50; i++) {
            Bww bww = new Bww();
            bww.setName("按实际库存你上课经常看见和出口市场卡死啊飒飒阿萨飒飒啊飒飒");
            bww.setShijian("2018年12月12日");
            bwws.add(bww);
        }
    }


}
