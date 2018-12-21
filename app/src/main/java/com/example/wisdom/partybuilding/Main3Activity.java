package com.example.wisdom.partybuilding;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.wisdom.partybuilding.utils.ToastUtils;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main3Activity extends AppCompatActivity {

    @BindView(R.id.butoon1)
    Button butoon1;
    @BindView(R.id.butoon2)
    Button butoon2;
    @BindView(R.id.butoon3)
    Button butoon3;
    @BindView(R.id.butoon4)
    Button butoon4;
    private QMUITipDialog tipDialog;
    private Handler mHandler;

    public static void start(Context context) {
        Intent intent = new Intent(context, Main3Activity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);

        getHandler();
        tipDialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载")
                .create();


    }


    private void getHandler() {
        mHandler = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                //do something
                //每隔1s循环执行run方法
                mHandler.postDelayed(this, 5000);
            }
        };
    }

    @OnClick({R.id.butoon1, R.id.butoon2, R.id.butoon3, R.id.butoon4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.butoon1:
                try {

                    tipDialog.show();
                    tipDialog.show();
                    tipDialog.show();




                } catch (Exception e) {
                }
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        if (tipDialog!=null){
                            tipDialog.cancel();          //do something
                        }

                    }
                }, 5000);    //延时1s执行

                break;
            case R.id.butoon2:
                ToastUtils.getInstance().showTextToast(this,"aaaaaaaaa");

                break;
            case R.id.butoon3:
                ToastUtils.getInstance().showTextToast(this,"ssssss");
                break;
            case R.id.butoon4:
                tipDialog.cancel();
                ToastUtils.getInstance().showTextToast(this,"dddddddddddd");
                break;
        }
    }
}
