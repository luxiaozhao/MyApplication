package com.example.wisdom.partybuilding.mvp.upcoming.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.base.BaseFragment;
import com.example.wisdom.partybuilding.base.IPresenter;
import com.example.wisdom.partybuilding.mvp.bean.SuccessBean;
import com.example.wisdom.partybuilding.mvp.bean.upcoming.UpcomingBean;
import com.example.wisdom.partybuilding.mvp.common.WebViewCurrencyActivity;
import com.example.wisdom.partybuilding.mvp.adapter.UpcomingAdapter;
import com.example.wisdom.partybuilding.net.Contants;
import com.example.wisdom.partybuilding.net.URLS;
import com.example.wisdom.partybuilding.utils.ToastUtils;
import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

/*
 * 待办
 * */
public class Upcoming_Fragment extends BaseFragment {

    @BindView(R.id.upcoming_recycler)
    RecyclerView upcoming_recycler;
    private UpcomingAdapter upcomingAdapter;

    private List<UpcomingBean.ResultsBean> upcomingBeans=new ArrayList<>();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            Log.e("TAG","通知1："+isVisibleToUser+"");
        }else {
            Log.e("TAG","通知2："+isVisibleToUser+"");
        }

    }
    @Override
    protected void initEventAndData() {

        getdata();

        upcomingAdapter = new UpcomingAdapter(getActivity(), upcomingBeans);
        upcoming_recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        upcoming_recycler.setNestedScrollingEnabled(false);
        upcoming_recycler.setAdapter(upcomingAdapter);

        upcomingAdapter.setOnClickLinstener(new UpcomingAdapter.onClickLinstener() {
            @Override
            public void setOnClick(View view, final int position) {


                SuccessBean successBean=  Hawk.get(Contants.loginInformation);
//                http://192.168.1.199:8080/FHBE/platform/bpm/task/tostartapp.ht?taskId=10000011990238&sid=47CA6FD0E05FB652904C1E52B8F8E9DB&pid=430111197811111111
                WebViewCurrencyActivity.start(getActivity(),upcomingBeans.get(position).getName(),
                        "http://192.168.1.199:8080/FHBE/platform/bpm/task/tostartapp.ht?taskId="+upcomingBeans.get(position).getId()+"&sid="+successBean.getSid()+"&pid="+successBean.getPid());

//                switch (position) {
//                    case 0://积极分子列为发展对象
////                        Main2Activity.start(getActivity());
//                        GrowingActivity.start(getActivity());
//                        break;
//                    case 1://申请入党
//                        ApplyActivity.start(getActivity());
//                        break;
//                    case 2://预备党员转为正式党员
//                        UrnPositiveActivity.start(getActivity());
//                        break;
//                    case 3://组织关系转出
//                        TransferOutActivity.start(getActivity());
//                        break;
//                    case 4://组织关系转入
//                        TransferActivity.start(getActivity());
//                        break;
//                    case 5://发展对象转为预备党员
//                        MakeReadyActivity.start(getActivity());
//                        break;
//                }
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

        SuccessBean successBean=Hawk.get(Contants.loginInformation);
        OkHttpUtils
                .get()
                .url(URLS.UPCOMING_LIST)
                .addParams("sid", successBean.getSid())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "这是失败的方法1" + e.toString());
                        ToastUtils.getInstance().showTextToast(getActivity(), "请登陆后继续访问");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        UpcomingBean bean = gson.fromJson(response, UpcomingBean.class);
                        upcomingBeans.addAll(bean.getResults());
                    }
                });

    }
}
