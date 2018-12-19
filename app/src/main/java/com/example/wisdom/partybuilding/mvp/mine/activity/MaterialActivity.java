package com.example.wisdom.partybuilding.mvp.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.text.InputType;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.base.BaseActivity;
import com.example.wisdom.partybuilding.base.BasePresenter;
import com.example.wisdom.partybuilding.utils.ToastUtils;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 * 个人界面
 * */
public class MaterialActivity extends BaseActivity {

    @BindView(R.id.material_return)
    ImageView materialReturn;
    @BindView(R.id.material_save)
    TextView materialSave;
    @BindView(R.id.material_name)
    TextView materialName;
    @BindView(R.id.material_sex)
    TextView materialSex;
    @BindView(R.id.material_identity_number)
    TextView materialIdentityNumber;
    @BindView(R.id.material_born)
    TextView materialBorn;
    @BindView(R.id.material_ethnic)
    TextView materialEthnic;
    @BindView(R.id.material_birthplace)
    TextView materialBirthplace;
    @BindView(R.id.material_fixed_line)
    TextView materialFixedLine;
    @BindView(R.id.material_mobilephone)
    TextView materialMobilephone;
    @BindView(R.id.material_postbox)
    TextView materialPostbox;
    @BindView(R.id.material_address)
    TextView materialAddress;
    @BindView(R.id.material_degree)
    TextView materialDegree;
    @BindView(R.id.material_education)
    TextView materialEducation;
    @BindView(R.id.material_unit)
    TextView materialUnit;
    @BindView(R.id.material_job_position)
    TextView materialJobPosition;

    private QMUIDialog.EditTextDialogBuilder nameInputDialog;
    private QMUIDialog.EditTextDialogBuilder identitynumberInputDialog;
    private QMUIDialog.EditTextDialogBuilder positionInputDialog;
    private QMUIDialog.EditTextDialogBuilder unitInputDialog;
    private QMUIDialog.EditTextDialogBuilder addressInputDialog;
    private QMUIDialog.EditTextDialogBuilder postboxInputDialog;
    private QMUIDialog.EditTextDialogBuilder    mobilephoneInputDialog;
    private QMUIDialog.EditTextDialogBuilder    fixedlineInputDialog;
    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    public static void start(Context context) {
        Intent intent = new Intent(context, MaterialActivity.class);
        context.startActivity(intent);
    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_material);
//
//        ButterKnife.bind(this);
//        initChNameInputDialog();//中文选择器
//    }


    @Override
    protected void initView() {
        ButterKnife.bind(this);
        initNameInputDialog();//姓名选择器
        initIdentitynumberInputDialog();//身份证选择器
        initfixedlineInputDialog();//联系电话输入框
        initmobilephoneInputDialog();//手机号码输入框
        initpostboxInputDialog();//邮箱输入框
        initaddressInputDialog();//家庭住址输入框
        initunitInputDialog();//单位输入框
        initpositionInputDialog();//工作岗位



    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_material;
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
//        initChNameInputDialog();//中文选择器
//        ButterKnife.bind(this);
//
//    }

    @OnClick({R.id.material_return, R.id.material_save, R.id.material_name, R.id.material_sex, R.id.material_identity_number, R.id.material_born, R.id.material_ethnic, R.id.material_birthplace, R.id.material_fixed_line, R.id.material_mobilephone, R.id.material_postbox, R.id.material_address, R.id.material_degree, R.id.material_education, R.id.material_unit, R.id.material_job_position})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.material_return:
                finish();
                break;
            case R.id.material_save:
                ToastUtils.getInstance().showTextToast(this, "保存成功");
                finish();
                break;
            case R.id.material_name://姓名
                if (nameInputDialog != null) {
                    nameInputDialog.show();
                }
                break;
            case R.id.material_sex:
                ToastUtils.getInstance().showTextToast(this, "性别");
                break;
            case R.id.material_identity_number://身份证号
                if (identitynumberInputDialog != null) {
                    identitynumberInputDialog.show();
                }
                break;
            case R.id.material_born:
                ToastUtils.getInstance().showTextToast(this, "出生日期");
                break;
            case R.id.material_ethnic:
                ToastUtils.getInstance().showTextToast(this, "民族");
                break;
            case R.id.material_birthplace:
                ToastUtils.getInstance().showTextToast(this, "籍贯");
                break;
            case R.id.material_fixed_line://联系电话
                if (fixedlineInputDialog!=null){
                    fixedlineInputDialog.show();
                }
                break;
            case R.id.material_mobilephone://手机号码

                if (mobilephoneInputDialog != null) {
                    mobilephoneInputDialog.show();
                }
                break;
            case R.id.material_postbox://邮箱
                if (postboxInputDialog != null) {
                    postboxInputDialog.show();
                }
                break;
            case R.id.material_address://家庭住址
                if (addressInputDialog != null) {
                    addressInputDialog.show();
                }
                break;
            case R.id.material_degree:
                ToastUtils.getInstance().showTextToast(this, "学位");
                break;
            case R.id.material_education:
                ToastUtils.getInstance().showTextToast(this, "学历");
                break;
            case R.id.material_unit://单位
                if (unitInputDialog != null) {
                    unitInputDialog.show();
                }
                break;
            case R.id.material_job_position:    //工作岗位
                if (positionInputDialog != null) {
                    positionInputDialog.show();
                }
                break;
        }
    }

    /**
     * 姓名输入框
     */

    private void initNameInputDialog() {
        nameInputDialog = new QMUIDialog.EditTextDialogBuilder(this);
        nameInputDialog.setTitle("输入名字")
                .setPlaceholder("在此输入您的名字")
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
                        CharSequence text = nameInputDialog.getEditText().getText();
                        if (text != null && text.length() > 0) {
                            materialName.setText(text);
                        } else {
                            materialName.setHint("姓名");
                        }
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle);
    }

    /**
     * 身份证号输入框
     */

    private void initIdentitynumberInputDialog() {
        identitynumberInputDialog = new QMUIDialog.EditTextDialogBuilder(this);
        identitynumberInputDialog.setTitle("输入身份证号")
                .setPlaceholder("在此输入您的身份证号")
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
                        CharSequence text = identitynumberInputDialog.getEditText().getText();
                        if (text != null && text.length() > 0) {
                            materialName.setText(text);
                        } else {
                            materialName.setHint("身份证号");
                        }
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle);
    }
    /**
     * 联系电话输入框
     */
    private void initfixedlineInputDialog() {
        fixedlineInputDialog = new QMUIDialog.EditTextDialogBuilder(this);
        fixedlineInputDialog.setTitle("输入联系电话")
                .setPlaceholder("在此输入您的联系电话")
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
                        CharSequence text = fixedlineInputDialog.getEditText().getText();
                        if (text != null && text.length() > 0) {
                            materialName.setText(text);
                        } else {
                            materialName.setHint("联系电话");
                        }
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle);
    }

    /**
     * 手机号码输入框
     */

    private void initmobilephoneInputDialog() {
        mobilephoneInputDialog = new QMUIDialog.EditTextDialogBuilder(this);
        mobilephoneInputDialog.setTitle("输入手机号")
                .setPlaceholder("在此输入您的手机号")
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
                        CharSequence text = mobilephoneInputDialog.getEditText().getText();
                        if (text != null && text.length() > 0) {
                            materialName.setText(text);
                        } else {
                            materialName.setHint("手机号");
                        }
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle);
    }

    /**
     * 邮箱输入框
     */

    private void initpostboxInputDialog() {
        postboxInputDialog = new QMUIDialog.EditTextDialogBuilder(this);
        postboxInputDialog.setTitle("输入电子邮箱")
                .setPlaceholder("在此输入您的电子邮箱")
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
                        CharSequence text = postboxInputDialog.getEditText().getText();
                        if (text != null && text.length() > 0) {
                            materialName.setText(text);
                        } else {
                            materialName.setHint("电子邮箱");
                        }
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle);
    }


    /**
     * 家庭住址输入框
     */

    private void initaddressInputDialog() {
        addressInputDialog = new QMUIDialog.EditTextDialogBuilder(this);
        addressInputDialog.setTitle("输入家庭住址")
                .setPlaceholder("在此输入您的家庭住址")
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
                        CharSequence text = addressInputDialog.getEditText().getText();
                        if (text != null && text.length() > 0) {
                            materialName.setText(text);
                        } else {
                            materialName.setHint("家庭住址");
                        }
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle);
    }


    /**
     * 单位输入框
     */

    private void initunitInputDialog() {
        unitInputDialog = new QMUIDialog.EditTextDialogBuilder(this);
        unitInputDialog.setTitle("输入工作单位")
                .setPlaceholder("在此输入您的工作单位")
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
                        CharSequence text = unitInputDialog.getEditText().getText();
                        if (text != null && text.length() > 0) {
                            materialName.setText(text);
                        } else {
                            materialName.setHint("工作单位");
                        }
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle);
    }

    /**
     * 工作岗位输入框
     */

    private void initpositionInputDialog() {
        positionInputDialog = new QMUIDialog.EditTextDialogBuilder(this);
        positionInputDialog.setTitle("输入工作岗位")
                .setPlaceholder("在此输入您的工作岗位")
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
                        CharSequence text = positionInputDialog.getEditText().getText();
                        if (text != null && text.length() > 0) {
                            materialName.setText(text);
                        } else {
                            materialName.setHint("工作岗位");
                        }
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle);
    }

}
