package com.example.wisdom.partybuilding.start;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.wisdom.partybuilding.Main2Activity;
import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.mvp.common.MainActivity;

import java.util.ArrayList;
import java.util.List;

import easypermission.davidinchina.com.easylibrary.EasyPermission;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private ViewPager vp;
    private int []imageIdArray;//图片资源的数组
    private List<View> viewList;//图片资源的集合
    private ViewGroup vg;//放置圆点

    //最后一页的按钮
    private ImageButton ib_start;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);

        init();

        sharedPreferences = getSharedPreferences("info2", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String result = sharedPreferences.getString("ss", null);
        if (TextUtils.isEmpty(result)) {
            //使用editor保存数据
            editor.putString("ss", "1");
            //注意一定要提交数据，此步骤容易忘记
            editor.commit();
        }else {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        ib_start = (ImageButton) findViewById(R.id.guide_ib_start);
        ib_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity.this,MainActivity.class));
                finish();
            }
        });

        //加载ViewPager
        initViewPager();

    }

    /**
     * 加载图片ViewPager
     */
    private void initViewPager() {
        vp = (ViewPager) findViewById(R.id.guide_vp);
        //实例化图片资源
        imageIdArray = new int[]{R.mipmap.r1,R.mipmap.r2,R.mipmap.r3,R.mipmap.r4};
        viewList = new ArrayList<>();
        //获取一个Layout参数，设置为全屏
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        //循环创建View并加入到集合中
        int len = imageIdArray.length;
        for (int i = 0;i<len;i++){
            //new ImageView并设置全屏和图片资源
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setBackgroundResource(imageIdArray[i]);

            //将ImageView加入到集合中
            viewList.add(imageView);
        }

        //View集合初始化好后，设置Adapter
        vp.setAdapter(new GuidePageAdapter(viewList));
        //设置滑动监听
        vp.setOnPageChangeListener(this);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 滑动后的监听
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        //循环设置当前页的标记图
        int length = imageIdArray.length;
//        for (int i = 0;i<length;i++){
////       //     ivPointArray[position].setBackgroundResource(R.mipmap.a);
////            if (position != i){
////            //    ivPointArray[i].setBackgroundResource(R.mipmap.h);
////            }
////        }       login

        //判断是否是最后一页，若是则显示按钮
        if (position == imageIdArray.length - 1){
            ib_start.setVisibility(View.VISIBLE);
        }else {
            ib_start.setVisibility(View.GONE);
        }
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }


    /*
     * 动态权限
     * */
    private void init() {
        EasyPermission.with(this).code(101).request();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        EasyPermission.handleResult(this, requestCode, permissions, grantResults);//处理权限申请回调结果
    }
}

