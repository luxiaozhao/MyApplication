package com.example.wisdom.partybuilding;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.wisdom.partybuilding.base.BaseActivity;
import com.example.wisdom.partybuilding.base.BasePresenter;

import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class Main2Activity extends BaseActivity {
    private JZVideoPlayerStandard mJC;
    private SensorManager sensorManager;
    private JZVideoPlayer.JZAutoFullscreenListener jzAutoFullscreenListener;


//    @BindView(R.id.et)
//    EditText et;
//    @BindView(R.id.se)
//    MyLinearLayout se;
//    @BindView(R.id.apply_group2)
//    EditText applyGroup2;
//    @BindView(R.id.apply_group1)
//    EditText applyGroup1;
//    @BindView(R.id.ssss)
//    ScrollView sv;    科工 wisdom  PartyBuilding

    public static void start(Context context) {
        Intent intent = new Intent(context, Main2Activity.class);
        context.startActivity(intent);
    }
    @Override
    protected void initView() {

        ButterKnife.bind(this);

//          调用系统自己的播放器，需要用户自己选择
//        String video = "http://ssb-video.oss-cn-qingdao.aliyuncs.com/Video_1003_20161027140007.mp4";
//        Intent openVideo = new Intent(Intent.ACTION_VIEW);
//        openVideo.setDataAndType(Uri.parse(video), "video/*");
//        startActivity(openVideo);



//        se.setParentScrollview(sv);
//        se.setEditeText(et);



        ImageView android_img = (ImageView) findViewById(R.id.android_img);
        android_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

            mJC = (JZVideoPlayerStandard) findViewById(R.id.mJC);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        jzAutoFullscreenListener = new JZVideoPlayer.JZAutoFullscreenListener();
        mJC.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        JZVideoPlayer.setVideoImageDisplayType(JZVideoPlayer.VIDEO_IMAGE_DISPLAY_TYPE_FILL_PARENT);


        mJC.TOOL_BAR_EXIST = false;
        mJC.setUp("http://ssb-video.oss-cn-qingdao.aliyuncs.com/Video_1003_20161027140007.mp4"
                , JZVideoPlayerStandard.SCROLL_AXIS_HORIZONTAL, "");



//        Glide.with(getApplicationContext()).load("http://p0.so.qhmsg.com/bdr/_240_/t01c10808f98a39bd4f.jpg")
//                .into(mJC.thumbImageView);
//        7播放比例,可以设置为16:9,4:3
        mJC.widthRatio = 16;
        mJC.heightRatio = 9;

//        8设置全屏播放
        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;  //横向
        JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;  //纵向

        mJC.startButton.performClick();

    }
    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }


    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(jzAutoFullscreenListener);
        JZVideoPlayer.releaseAllVideos();
    }


    @Override
    protected void onResume() {
        super.onResume();
//        播放器重力感应
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(jzAutoFullscreenListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }



//    @Override
//    protected void onResume() {
//        super.onResume();
//        finish();
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
//    @BindView(R.id.apply_group3)
//    EditText applyGroup3;
//    @BindView(R.id.apply_group2)
//    EditText applyGroup2;
//    @BindView(R.id.apply_group1)
//    EditText applyGroup1;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main2);
////    }
//
//    public static void start(Context context) {
//        Intent intent = new Intent(context, Main2Activity.class);
//        context.startActivity(intent);
//    }
//
//    @Override
//    protected void initView() {
//        ButterKnife.bind(this);
//
//        applyGroup3.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                //触摸的是EditText并且当前EditText可以滚动则将事件交给EditText处理；否则将事件交由其父类处理
//                if ((view.getId() == R.id.apply_group3 && canVerticalScroll(applyGroup3))) {
//                    view.getParent().requestDisallowInterceptTouchEvent(true);
//                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//                        view.getParent().requestDisallowInterceptTouchEvent(false);
//                    }
//                }
//                return false;
//            }
//        });
//
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_main2;
//    }
//
//    @Override
//    protected BasePresenter getPresenter() {
//        return null;
//    }
//
//    @Override
//    protected void initDataAndEvent() {
//
//    }
//
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        // TODO: add setContentView(...) invocation
////        ButterKnife.bind(this);
////    }
//
////    @OnClick({R.id.apply_group2, R.id.apply_group1})
////    public void onViewClicked(View view) {
////        switch (view.getId()) {
////
////            case R.id.apply_group2:
////                break;
////            case R.id.apply_group1:
////                break;
////        }
////    }
//
//
//    /**
//     * EditText竖直方向是否可以滚动
//     *
//     * @param editText 需要判断的EditText
//     * @return true：可以滚动   false：不可以滚动
//     */
//    private boolean canVerticalScroll(EditText editText) {
//        //滚动的距离
//        int scrollY = editText.getScrollY();
//        //控件内容的总高度
//        int scrollRange = editText.getLayout().getHeight();
//        //控件实际显示的高度
//        int scrollExtent = editText.getHeight() - editText.getCompoundPaddingTop() - editText.getCompoundPaddingBottom();
//        //控件内容总高度与实际显示高度的差值
//        int scrollDifference = scrollRange - scrollExtent;
//
//        if (scrollDifference == 0) {
//            return false;
//        }
//
//        return (scrollY > 0) || (scrollY < scrollDifference - 1);
//    }
//}
