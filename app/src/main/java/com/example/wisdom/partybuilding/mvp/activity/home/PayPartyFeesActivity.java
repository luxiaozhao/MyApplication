package com.example.wisdom.partybuilding.mvp.activity.home;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.util.TimeUtils;
import android.text.InputType;
import android.text.format.DateUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.base.BaseActivity;
import com.example.wisdom.partybuilding.base.BasePresenter;
import com.example.wisdom.partybuilding.utils.ToastUtils;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
* 交纳党费
* */
public class PayPartyFeesActivity extends BaseActivity {

    @BindView(R.id.expense_return)
    ImageView expenseReturn;
    @BindView(R.id.expense_detail)
    TextView expenseDetail;
    @BindView(R.id.expense_sum)
    TextView expenseSum;
    @BindView(R.id.expense_month)
    TextView expenseMonth;
    @BindView(R.id.match_weixin_check)
    CheckBox matchWeixinCheck;
    @BindView(R.id.match_zhifubao_check)
    CheckBox matchZhifubaoCheck;
    @BindView(R.id.match_confirm)
    TextView matchConfirm;


    private QMUIDialog.EditTextDialogBuilder sumInputDialog;
    private QMUIDialog.EditTextDialogBuilder summonthInputDialog;
    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;


    private TimePickerView timeStartSelecter;

    public static void start(Context context) {
        Intent intent = new Intent(context, PayPartyFeesActivity.class);
        context.startActivity(intent);
    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_pay_party_fees);
//    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        initsumInputDialogInputDialog();
        initStartTimeSelecter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_party_fees;
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
//
//    }

    @OnClick({R.id.expense_return, R.id.expense_detail, R.id.expense_sum, R.id.expense_month, R.id.match_weixin_check, R.id.match_zhifubao_check, R.id.match_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.expense_return:
                finish();
                break;
            case R.id.expense_detail://明细
                DetailActivity.start(this);
                break;
            case R.id.expense_sum:
                if (sumInputDialog != null) {
                    sumInputDialog.show();
                }
                break;
            case R.id.expense_month://
                if (timeStartSelecter != null) {
                    timeStartSelecter.show();
                }
                break;
            case R.id.match_weixin_check://微信选择

                if (matchWeixinCheck.isChecked()) {
                    matchZhifubaoCheck.setChecked(false);
                    matchWeixinCheck.setChecked(true);
                } else {
                    matchZhifubaoCheck.setChecked(false);
                    matchWeixinCheck.setChecked(false);
                }




                break;
            case R.id.match_zhifubao_check:

                if (matchZhifubaoCheck.isChecked()) {
                    matchZhifubaoCheck.setChecked(true);
                    matchWeixinCheck.setChecked(false);
                } else {
                    matchZhifubaoCheck.setChecked(false);
                    matchWeixinCheck.setChecked(false);
                }

                break;
            case R.id.match_confirm://确认缴费
                ConfirmPaymentActivity.start(this);
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
                            expenseSum.setText(text);
                            expenseSum.setTextColor(Color.parseColor("#0b0b0b"));
                        } else {
                            expenseSum.setHint("如：200.00");
                        }
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle);
    }


    /**
     * 缴费金额
     */
    /*
     *
     * 开始时间选择
     *
     * */
    private Long startdateMillis;

    private void initStartTimeSelecter() {

        timeStartSelecter = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View view) {
                startdateMillis = com.blankj.utilcode.util.TimeUtils.date2Millis(date);
                expenseMonth.setText(com.example.wisdom.partybuilding.utils.DateUtils.formatDate(date, com.example.wisdom.partybuilding.utils.DateUtils.yyyyMMDD));
                expenseMonth.setTextColor(Color.parseColor("#0b0b0b"));
            }
        })
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .build();
        Dialog mDialog = timeStartSelecter.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            timeStartSelecter.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }
    }


}
