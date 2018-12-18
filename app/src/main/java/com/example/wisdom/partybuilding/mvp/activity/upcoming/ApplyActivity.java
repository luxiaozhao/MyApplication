package com.example.wisdom.partybuilding.mvp.activity.upcoming;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bigkoo.pickerview.view.TimePickerView;
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

    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;
    private QMUIDialog.EditTextDialogBuilder sumInputDialog;
    public static void start(Context context) {
        Intent intent = new Intent(context, ApplyActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        applytalksituationse.setParentScrollview(apply_scrollview);
        applytalksituationse.setEditeText(applyTalkSituation);


        initsumInputDialogInputDialog();




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


    @OnClick({R.id.apply_return, R.id.apply_name, R.id.apply_application_ime, R.id.apply_party_branch, R.id.apply_party_group, R.id.apply_volunteer_book, R.id.apply_active, R.id.apply_active_yes_img, R.id.apply_active_yes, R.id.apply_active_no_img, R.id.apply_active_no, R.id.apply_culture1, R.id.apply_culture2, R.id.apply_withdraw, R.id.apply_transmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.apply_return:
                finish();
                break;
            case R.id.apply_name:
                if (sumInputDialog != null) {
                    sumInputDialog.show();
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
            case R.id.apply_active_yes_img:
                break;
            case R.id.apply_active_yes:
                break;
            case R.id.apply_active_no_img:
                break;
            case R.id.apply_active_no:
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
    private void initsumInputDialogInputDialog() {
        sumInputDialog = new QMUIDialog.EditTextDialogBuilder(this);
        sumInputDialog.setTitle("输入金额")
                .setPlaceholder("在此输入您的缴费金额")
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
                        CharSequence text = sumInputDialog.getEditText().getText();
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
