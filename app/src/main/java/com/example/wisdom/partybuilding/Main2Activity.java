package com.example.wisdom.partybuilding;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ScrollView;

import com.example.wisdom.partybuilding.base.BaseActivity;
import com.example.wisdom.partybuilding.base.BasePresenter;
import com.example.wisdom.partybuilding.view.MyLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends BaseActivity {


    @BindView(R.id.et)
    EditText et;
    @BindView(R.id.se)
    MyLinearLayout se;
    @BindView(R.id.apply_group2)
    EditText applyGroup2;
    @BindView(R.id.apply_group1)
    EditText applyGroup1;
    @BindView(R.id.ssss)
    ScrollView sv;

    public static void start(Context context) {
        Intent intent = new Intent(context, Main2Activity.class);
        context.startActivity(intent);
    }
    @Override
    protected void initView() {

        ButterKnife.bind(this);
        se.setParentScrollview(sv);
        se.setEditeText(et);
    }

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
