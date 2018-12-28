package com.example.wisdom.partybuilding.mvp.home.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.base.BaseActivity;
import com.example.wisdom.partybuilding.base.BasePresenter;
import com.example.wisdom.partybuilding.mvp.bean.SuccessBean;
import com.example.wisdom.partybuilding.mvp.bean.home.MeetingBean;
import com.example.wisdom.partybuilding.mvp.home.adapter.MeetingAdapter;
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
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class SessionsItemActivity extends BaseActivity {
    @BindView(R.id.sessions_item_return)
    ImageView sessionsItemReturn;
    @BindView(R.id.sessions_item_title)
    TextView sessionsItemTitle;
    @BindView(R.id.sessions_item_relative)
    RecyclerView sessionsItemRelative;
    @BindView(R.id.sessions_item_layout)
    RelativeLayout sessions_item_layout;
    @BindView(R.id.sessions_item_layout_recy)
    LinearLayout sessions_item_layout_recy;

    private List<MeetingBean.MeetingsBean> meetings = new ArrayList<>();
    private MeetingAdapter folderAdapter1;

    public static void start(Context context, String time) {
        Intent intent = new Intent(context, SessionsItemActivity.class);
        intent.putExtra("time", time);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        SuccessBean successBean = Hawk.get(Contants.loginInformation);

        String stringExtra = getIntent().getStringExtra("time");

        if (stringExtra.equals("支部党员大会")) {
            sessionsItemTitle.setText("支部党员大会");
            getdata(URLS.THREE_SESSIONS_AND_ONE_LESSON_BRANCH_PARTY_ASSEMBLY, successBean.getPid(), "1", successBean.getDeptid() + "");
        } else if (stringExtra.equals("支部委员会")) {
            sessionsItemTitle.setText("支部委员会");
            getdata(URLS.THREE_SESSIONS_AND_ONE_LESSON_BRANCH_COMMITTEE, successBean.getPid(), "2", successBean.getDeptid() + "");
        } else if (stringExtra.equals("党小组会")) {
            sessionsItemTitle.setText("党小组会");
            getdata(URLS.THREE_SESSIONS_AND_ONE_LESSON_PARTY_GROUP_MEETING, successBean.getPid(), "3", successBean.getDeptid() + "");
        } else if (stringExtra.equals("党课")) {
            sessionsItemTitle.setText("党课");
            getdata(URLS.THREE_SESSIONS_AND_ONE_LESSON_PARTYCLASS, successBean.getPid(), "4", successBean.getDeptid() + "");
        }


//        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
//        folderAdapter1 = new MeetingAdapter(activity, meetings);
//        sessionsItemRelative.setLayoutManager(linearLayoutManager1);
//        sessionsItemRelative.setNestedScrollingEnabled(false);
//        sessionsItemRelative.setLayoutManager(new GridLayoutManager(activity, 1));
//        sessionsItemRelative.setAdapter(folderAdapter1);
//


//        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
//        meetingAdapter = new MeetingAdapter(activity, meetings);
//        sessionsItemRelative.setLayoutManager(linearLayoutManager1);
//        sessionsItemRelative.setNestedScrollingEnabled(false);
//        sessionsItemRelative.setLayoutManager(new GridLayoutManager(activity, 1));
//        sessionsItemRelative.setAdapter(meetingAdapter);

//        meetingAdapter.setOnClickLinstener(new MeetingAdapter.onClickLinstener() {
//            @Override
//            public void setOnClickaNotes(View view, int position) {
//                ToastUtils.getInstance().showTextToast(activity, "会议记录");
//            }
//
//            @Override
//            public void setOnClickaSign(View view, int position) {
//                ToastUtils.getInstance().showTextToast(activity, "签到成功");
//            }
//
//            @Override
//            public void setOnClickaLinear(View view, int position) {
//                ToastUtils.getInstance().showTextToast(activity, "详情");
//            }
//        });


    }

    private void getdata(String url, String pid, final String type, String deptid) {

        OkHttpUtils
                .get()
                .url(url)
                .addParams("pid", pid)
                .addParams("meettype", type)
                .addParams("deptid", deptid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAGG", "response---------------" + e.toString());
                        sessions_item_layout.setVisibility(View.VISIBLE);
                        sessions_item_layout_recy.setVisibility(View.GONE);

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            String responses = "{meetings:" + response + "}";
                            Gson gson = new Gson();
                            MeetingBean bean = gson.fromJson(responses, MeetingBean.class);

                            meetings = bean.getMeetings();

//                        folderAdapter1.notifyDataSetChanged();

                            LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
                            folderAdapter1 = new MeetingAdapter(activity, meetings);
                            sessionsItemRelative.setLayoutManager(linearLayoutManager1);
                            sessionsItemRelative.setNestedScrollingEnabled(false);
                            sessionsItemRelative.setLayoutManager(new GridLayoutManager(activity, 1));
                            sessionsItemRelative.setAdapter(folderAdapter1);
                            sessions_item_layout.setVisibility(View.GONE);
                            sessions_item_layout_recy.setVisibility(View.VISIBLE);

                            folderAdapter1.setOnClickLinstener(new MeetingAdapter.onClickLinstener() {
                                @Override
                                public void setOnClickaNotes(View view, int position) {
                                    MeetingBean.MeetingsBean meetingsBean = meetings.get(position);
                                    MeetingRecordActivity.start(activity,meetingsBean);
                                }

                                @Override
                                public void setOnClickaSign(View view, int position) {
                                    ToastUtils.getInstance().showTextToast(activity, "签到成功");
                                }

                                @Override
                                public void setOnClickaLinear(View view, int position) {
                                    MeetingBean.MeetingsBean meetingsBean = meetings.get(position);
                                    RecordDetailsActivity.start(activity, sessionsItemTitle.getText().toString(), meetingsBean);
                                }
                            });

                        } catch (Exception e) {
                            sessions_item_layout_recy.setVisibility(View.GONE);
                            sessions_item_layout.setVisibility(View.VISIBLE);
                        }
                    }
                });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sessions_item;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {

    }

    @OnClick({R.id.sessions_item_return})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sessions_item_return:
                finish();
                break;
        }
    }
}
