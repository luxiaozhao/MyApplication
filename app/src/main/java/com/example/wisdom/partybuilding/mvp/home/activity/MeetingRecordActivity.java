package com.example.wisdom.partybuilding.mvp.home.activity;

import android.content.Context;
import android.content.Intent;
import android.text.InputType;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.base.BaseActivity;
import com.example.wisdom.partybuilding.base.BasePresenter;
import com.example.wisdom.partybuilding.mvp.bean.home.MeetingBean;
import com.example.wisdom.partybuilding.utils.DateUtils;
import com.example.wisdom.partybuilding.utils.ToastUtils;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 * 会议记录
 * */
public class MeetingRecordActivity extends BaseActivity {

    @BindView(R.id.meeting_record_return)
    ImageView meetingRecordReturn;
    @BindView(R.id.meeting_record_name)
    TextView meetingRecordName;
    @BindView(R.id.meeting_record_name_img)
    ImageView meetingRecordNameImg;
    @BindView(R.id.meeting_record_time)
    TextView meetingRecordTime;
    @BindView(R.id.meeting_record_time_img)
    ImageView meetingRecordTimeImg;
    @BindView(R.id.meeting_record_host)
    TextView meetingRecordHost;
    @BindView(R.id.meeting_record_host_img)
    ImageView meetingRecordHostImg;
    @BindView(R.id.meeting_record_location)
    TextView meetingRecordLocation;
    @BindView(R.id.meeting_record_location_img)
    ImageView meetingRecordLocationImg;
    @BindView(R.id.meeting_record_recorder)
    TextView meetingRecordRecorder;
    @BindView(R.id.meeting_record_recorder_img)
    ImageView meetingRecordRecorderImg;
    @BindView(R.id.meeting_record_shouldnum)
    TextView meetingRecordShouldnum;
    @BindView(R.id.meeting_record_shouldnum_img)
    ImageView meetingRecordShouldnumImg;
    @BindView(R.id.meeting_record_realnum)
    TextView meetingRecordRealnum;
    @BindView(R.id.meeting_record_realnum_img)
    ImageView meetingRecordRealnumImg;
    @BindView(R.id.meeting_record_meetingtitle)
    TextView meetingRecordMeetingtitle;
    @BindView(R.id.meeting_record_meetingtitle_img)
    ImageView meetingRecordMeetingtitleImg;
    @BindView(R.id.meeting_record_meetingcontent)
    TextView meetingRecordMeetingcontent;
    @BindView(R.id.meeting_record_takenote)
    TextView meetingRecordTakenote;
    @BindView(R.id.meeting_record_takenote_img)
    ImageView meetingRecordTakenoteImg;
    @BindView(R.id.meeting_record_break)
    TextView meetingRecordBreak;
    @BindView(R.id.meeting_record_save)
    TextView meetingRecordSave;
    private MeetingBean.MeetingsBean yundanList;

    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;
    private QMUIDialog.EditTextDialogBuilder applyNameInputDialog;
    private QMUIDialog.EditTextDialogBuilder HostNameInputDialog;

    public static void start(Context context, MeetingBean.MeetingsBean listdetail) {
        Intent intent = new Intent(context, MeetingRecordActivity.class);
        intent.putExtra("listdetail", (MeetingBean.MeetingsBean) listdetail);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
//        ButterKnife.bind(this);
        ButterKnife.bind(this);
        yundanList = (MeetingBean.MeetingsBean) getIntent().getSerializableExtra("listdetail");


        //标题        meetingname
        if (yundanList.getMeetingname() != null && yundanList.getMeetingname().length() != 0) {
            meetingRecordName.setText(yundanList.getMeetingname());
        }


        //会议时间
        if (yundanList.getMeetingtime() != 0) {
            try {
                long meetingtime = yundanList.getMeetingtime();
                long meetingtimes = meetingtime / 1000;
                String releasetimes = DateUtils.timesTwo(meetingtimes + "");
                meetingRecordTime.setText(releasetimes);
            } catch (Exception e) {
            }
        }

        //主持人
        if (yundanList.getCompere() != null && yundanList.getCompere().length() != 0) {
            meetingRecordHost.setText(yundanList.getCompere());
        }

        //会议地点
        if (yundanList.getMeetingaddress() != null && yundanList.getMeetingaddress().length() != 0) {
            meetingRecordLocation.setText(yundanList.getMeetingaddress());
        }

        //记录人
        if (yundanList.getTakenote() != null && yundanList.getTakenote().length() != 0) {
            meetingRecordRecorder.setText(yundanList.getTakenote());
        }

        //应到人数
        if (yundanList.getShouldnum() != 0) {
            meetingRecordShouldnum.setText(yundanList.getShouldnum() + "");
        }

        //实到人数
        if (yundanList.getRealnum() != 0) {
            meetingRecordRealnum.setText(yundanList.getRealnum() + "");
        }

        //会议议题
        if (yundanList.getMeetingtitle() != null && yundanList.getMeetingtitle().length() != 0) {
            meetingRecordMeetingtitle.setText(yundanList.getMeetingtitle());
        }

        //会议内容
        if (yundanList.getMeetingcontent() != null && yundanList.getMeetingcontent().length() != 0) {
            meetingRecordMeetingcontent.setText(yundanList.getMeetingcontent());
        }

        //发布人
        if (yundanList.getTakenote() != null && yundanList.getTakenote().length() != 0) {
            meetingRecordTakenote.setText(yundanList.getTakenote());
        }






        TitleNameInputDialog(); //  姓名
        HostNameInputDialog();  //  主持人
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_meeting_record;
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
//        ButterKnife.bind(this);
//    }

    @OnClick({R.id.meeting_record_return, R.id.meeting_record_break, R.id.meeting_record_save,R.id.meeting_record_name_img,R.id.meeting_record_host_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.meeting_record_return:
                finish();
                break;
            case R.id.meeting_record_break:
                ToastUtils.getInstance().showTextToast(activity, "刷新");
                break;
            case R.id.meeting_record_save:
                ToastUtils.getInstance().showTextToast(activity, "保存");
                finish();
                break;
            case R.id.meeting_record_name_img:  //  标题
                if (applyNameInputDialog != null) {
                    applyNameInputDialog.show();
                }
                    break;
            case R.id.meeting_record_host_img:
                if (HostNameInputDialog != null) {
                    HostNameInputDialog.show();
                }
                break;
        }
    }

    /**
     *  标题
     */
    private void TitleNameInputDialog() {
        applyNameInputDialog = new QMUIDialog.EditTextDialogBuilder(this);
        applyNameInputDialog.setTitle("输入姓名")
                .setPlaceholder("在此输入您的姓名")
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        CharSequence text = applyNameInputDialog.getEditText().getText();
                        if (text != null && text.length() > 0) {
                            meetingRecordName.setText(text);
                        } else {
                            meetingRecordName.setHint("");
                        }
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle);
    }


    /**
     *  主持人
     */
    private void HostNameInputDialog() {
        HostNameInputDialog = new QMUIDialog.EditTextDialogBuilder(this);
        HostNameInputDialog.setTitle("输入姓名")
                .setPlaceholder("在此输入您的姓名")
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        CharSequence text = HostNameInputDialog.getEditText().getText();
                        if (text != null && text.length() > 0) {
                            meetingRecordHost.setText(text);
                        } else {
                            meetingRecordHost.setHint("");
                        }
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle);
    }


}
