package com.example.wisdom.partybuilding.mvp.upcoming.activity;

import android.content.Context;
import android.content.Intent;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.base.BaseActivity;
import com.example.wisdom.partybuilding.base.BasePresenter;
import com.example.wisdom.partybuilding.view.MyLinearLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 * 党员申请
 * */
public class ApplyActivity extends BaseActivity {

    @BindView(R.id.apply_scrollview)
    ScrollView apply_scrollview;
    @BindView(R.id.apply_return)
    ImageView applyReturn;
    @BindView(R.id.apply_name)
    TextView applyName;
    @BindView(R.id.apply_application_ime)
    TextView applyApplicationIme;
    @BindView(R.id.apply_party_branch)
    TextView applyPartyBranch;
    @BindView(R.id.apply_party_group)
    TextView applyPartyGroup;
    @BindView(R.id.apply_volunteer_book)
    LinearLayout applyVolunteerBook;
    @BindView(R.id.apply_talk_situation)
    EditText applyTalkSituation;
    @BindView(R.id.apply_group)
    EditText applyGroup;
    @BindView(R.id.apply_branch)
    EditText applyBranch;
    @BindView(R.id.apply_active)
    TextView applyActive;
    @BindView(R.id.apply_party_committee)
    EditText applyPartyCommittee;
    @BindView(R.id.apply_active_yes_img)
    ImageView applyActiveYesImg;
    @BindView(R.id.apply_active_yes)
    LinearLayout applyActiveYes;
    @BindView(R.id.apply_active_no_img)
    ImageView applyActiveNoImg;
    @BindView(R.id.apply_active_no)
    LinearLayout applyActiveNo;
    @BindView(R.id.apply_culture1)
    TextView applyCulture1;
    @BindView(R.id.apply_culture2)
    TextView applyCulture2;
    @BindView(R.id.apply_withdraw)
    TextView applyWithdraw;
    @BindView(R.id.apply_transmit)
    TextView applyTransmit;

    @BindView(R.id.apply_talk_situation_se)
    MyLinearLayout applytalksituationse;



    boolean aBoolean_yes = true;
    boolean aBoolean_no = true;
    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;
    private QMUIDialog.EditTextDialogBuilder applyNameInputDialog;
    private QMUIDialog.EditTextDialogBuilder InputDialog;
//
//    private QMUIDialog.EditTextDialogBuilder InputDialog;
//
//    private QMUIDialog.EditTextDialogBuilder InputDialog;
//
//    private QMUIDialog.EditTextDialogBuilder InputDialog;
//
//    private QMUIDialog.EditTextDialogBuilder InputDialog;
//
//    private QMUIDialog.EditTextDialogBuilder InputDialog;
//
//    private QMUIDialog.EditTextDialogBuilder InputDialog;
//


    public static void start(Context context) {
        Intent intent = new Intent(context, ApplyActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        applytalksituationse.setParentScrollview(apply_scrollview);
        applytalksituationse.setEditeText(applyTalkSituation);


        initapplyNameInputDialogInputDialogInputDialog();//姓名

//
//        init           applyName            InputDialogInputDialogInputDialog();//姓名
//        init           applyName            InputDialogInputDialogInputDialog();//姓名
//        init           applyName            InputDialogInputDialogInputDialog();//姓名
//        init           applyName            InputDialogInputDialogInputDialog();//姓名
//        init           applyName            InputDialogInputDialogInputDialog();//姓名
//        init           applyName            InputDialogInputDialogInputDialog();//姓名
//        init           applyName            InputDialogInputDialogInputDialog();//姓名
//        init           applyName            InputDialogInputDialogInputDialog();//姓名
//        init           applyName            InputDialogInputDialogInputDialog();//姓名
//        init           applyName            InputDialogInputDialogInputDialog();//姓名
//        init           applyName            InputDialogInputDialogInputDialog();//姓名
//        init           applyName            InputDialogInputDialogInputDialog();//姓名
//        init           applyName            InputDialogInputDialogInputDialog();//姓名
//        init           applyName            InputDialogInputDialogInputDialog();//姓名
//        init           applyName            InputDialogInputDialogInputDialog();//姓名
//

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {

    }


    @OnClick({R.id.apply_return, R.id.apply_name, R.id.apply_application_ime, R.id.apply_party_branch, R.id.apply_party_group, R.id.apply_volunteer_book, R.id.apply_active, R.id.apply_active_yes, R.id.apply_active_no, R.id.apply_culture1, R.id.apply_culture2, R.id.apply_withdraw, R.id.apply_transmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.apply_return:
                finish();
                break;
            case R.id.apply_name:
                if (applyNameInputDialog != null) {
                    applyNameInputDialog.show();
                }
                break;
            case R.id.apply_application_ime:
                break;
            case R.id.apply_party_branch:
                break;
            case R.id.apply_party_group:
                break;
            case R.id.apply_volunteer_book:
                break;
            case R.id.apply_active:
                break;
            case R.id.apply_active_yes:
                if (aBoolean_yes) {
                    applyActiveYesImg.setImageResource(R.mipmap.rd_xuanzhong);
                    aBoolean_yes = false;
                }
//                else {
//                    applyActiveYesImg.setImageResource(R.mipmap.rd_wanxuanzhong);
//
//
//                }
                aBoolean_no = true;
                applyActiveNoImg.setImageResource(R.mipmap.rd_wanxuanzhong);
                break;
            case R.id.apply_active_no:
                if (aBoolean_no) {
                    applyActiveNoImg.setImageResource(R.mipmap.rd_xuanzhong);
                    aBoolean_no = false;
                }
//                else {
//
//                    applyActiveNoImg.setImageResource(R.mipmap.rd_wanxuanzhong);
//
//                }
                aBoolean_yes = true;
                applyActiveYesImg.setImageResource(R.mipmap.rd_wanxuanzhong);
                break;
            case R.id.apply_culture1:
                break;
            case R.id.apply_culture2:
                break;
            case R.id.apply_withdraw:
                break;
            case R.id.apply_transmit:
                break;
        }
    }

    /**
     * 缴费金额
     */
    private void initapplyNameInputDialogInputDialogInputDialog() {
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
                            applyName.setText(text);
                        } else {
                            applyName.setHint("");
                        }
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle);
    }


}
