package com.example.wisdom.partybuilding.mvp.notice.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.base.BaseFragment;
import com.example.wisdom.partybuilding.base.IPresenter;
import com.example.wisdom.partybuilding.mvp.home.activity.NoticeAdapter;
import com.example.wisdom.partybuilding.mvp.bean.home.DynamicBean;
import com.example.wisdom.partybuilding.net.URLS;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;


/*
 *  待办
 * */

public class Notice_Fragment extends BaseFragment {
    @BindView(R.id.notice_recycler)
    RecyclerView noticeRecycler;
    Unbinder unbinder;
    private List<DynamicBean.NewsBean> bwws = new ArrayList<>();
    private NoticeAdapter folderAdapter1;


    @Override
    protected void initEventAndData() {


        getdata(1,20);

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

    private void getdata(int pageIndex,int pageSize) {
        OkHttpUtils
                .get()
                .url(URLS.HOME_DYNAMIC)
                .addParams("itemName","通知公告")
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

}
