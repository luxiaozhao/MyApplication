package com.example.wisdom.partybuilding.mvp.home.activity;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.base.BaseActivity;
import com.example.wisdom.partybuilding.base.BasePresenter;
import com.example.wisdom.partybuilding.mvp.bean.home.MeetingBean;
import com.example.wisdom.partybuilding.utils.DateUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 * 记录详情
 * */
public class RecordDetailsActivity extends BaseActivity {
    @BindView(R.id.record_details_title)
    TextView recordDetailsTitle;
    @BindView(R.id.record_details_heading)
    TextView record_details_heading;
    @BindView(R.id.record_details_time)
    TextView recordDetailsTime;
    @BindView(R.id.record_details_host)
    TextView recordDetailsHost;
    @BindView(R.id.record_details_site)
    TextView recordDetailsSite;
    @BindView(R.id.record_details_notes)
    TextView recordDetailsNotes;
    @BindView(R.id.record_details_number_people)
    TextView recordDetailsNumberPeople;
    @BindView(R.id.record_details_actual_number)
    TextView recordDetailsActualNumber;
    @BindView(R.id.record_details_topic)
    TextView recordDetailsTopic;
    @BindView(R.id.record_details_detail)
    TextView recordDetailsDetail;
    @BindView(R.id.record_details_return)
    ImageView recordDetailsReturn;
    private MeetingBean.MeetingsBean yundanList;

    public static void start(Context context, String title, MeetingBean.MeetingsBean listdetail) {
        Intent intent = new Intent(context, RecordDetailsActivity.class);
        intent.putExtra("listdetail", title);
        intent.putExtra("listdetail", (MeetingBean.MeetingsBean) listdetail);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        yundanList = (MeetingBean.MeetingsBean) getIntent().getSerializableExtra("listdetail");

        //标题
        recordDetailsTitle.setText(yundanList.getMeetingtitle());
        //题目
        record_details_heading.setText(yundanList.getMeetingtitle());


        try {
            //会议时间
            long meetingtime = yundanList.getMeetingtime();
            long meetingtimes = meetingtime / 1000;
            String releasetimes = DateUtils.timesTwo(meetingtimes + "");
            recordDetailsTime.setText(Html.fromHtml("<font color=\'#0b0b0b\' style=\'15sp\'><strong>会议时间：</strong> </font><font color=\'#0b0b0b\'style=\'15sp\' >" + releasetimes + "</font>"));
        } catch (Exception e) {
        }

        //主持人
        String compere1 = yundanList.getCompere();
        recordDetailsHost.setText(Html.fromHtml("<font color=\'#0b0b0b\' style=\'15sp\'><strong> 主 持 人 ：</strong> </font><font color=\'#0b0b0b\'style=\'15sp\' >" + compere1 + "</font>"));

        //会议地点
        String meetingaddress = yundanList.getMeetingaddress();
        recordDetailsSite.setText(Html.fromHtml("<font color=\'#0b0b0b\' style=\'15sp\'><strong>会议地点：</strong> </font><font color=\'#0b0b0b\'style=\'15sp\' >" + meetingaddress + "</font>"));

        //记录人
        String takenote = yundanList.getTakenote();
        recordDetailsNotes.setText(Html.fromHtml("<font color=\'#0b0b0b\' style=\'15sp\'><strong> 记 录 人 ：</strong> </font><font color=\'#0b0b0b\'style=\'15sp\' >" + takenote + "</font>"));

        //应到人数
        int shouldnum = yundanList.getShouldnum();
        recordDetailsNumberPeople.setText(Html.fromHtml("<font color=\'#0b0b0b\' style=\'15sp\'><strong>应到人数：</strong> </font><font color=\'#0b0b0b\'style=\'15sp\' >" + shouldnum + "</font>"));

        //实到人数
        int realnum = yundanList.getRealnum();
        recordDetailsActualNumber.setText(Html.fromHtml("<font color=\'#0b0b0b\' style=\'15sp\'><strong>实到人数：</strong> </font><font color=\'#0b0b0b\'style=\'15sp\' >" + realnum + "</font>"));

        //会议议题
        String meetingtitle = yundanList.getMeetingtitle();
        recordDetailsTopic.setText(Html.fromHtml("<font color=\'#0b0b0b\' style=\'15sp\'><strong>会议议题：</strong> </font><font color=\'#0b0b0b\'style=\'15sp\' >" + meetingtitle + "</font>"));

        //会议内容
        String meetingcontent = yundanList.getMeetingcontent();
        recordDetailsDetail.setText(Html.fromHtml("<font color=\'#0b0b0b\' style=\'15sp\'><strong>会议内容：</strong> </font><font color=\'#0b0b0b\'style=\'15sp\' >" + meetingcontent + "</font>"));

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_record_details;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {

    }

    @OnClick({R.id.record_details_return})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.record_details_return:
                finish();
                break;
        }
    }


}
