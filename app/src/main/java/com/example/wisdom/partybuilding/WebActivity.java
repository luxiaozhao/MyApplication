package com.example.wisdom.partybuilding;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.wisdom.partybuilding.mvp.home.activity.TidingsActivity;
import com.example.wisdom.partybuilding.utils.BitmapUtils;
import com.tencent.smtt.sdk.TbsReaderView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;

import java.io.File;
import java.text.SimpleDateFormat;

import org.xutils.x;


public class WebActivity extends AppCompatActivity implements TbsReaderView.ReaderCallback {


    ProgressBar web_bar;
    TbsReaderView mTbsReaderView;
    RelativeLayout mRelativeLayout;

    public static void start(Context context, String fileurl) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("fileurl", fileurl);
        context.startActivity(intent);
    }

    // 文件的下载路径
    private static final String BASE_URL = "http://www.ncac.gov.cn/sitefiles/services/wcm/utils.aspx?type=Download&publishmentSystemID=470&channelID=574&contentID=20880";
    //    private static final String BASE_URL = "http://192.168.200.15:8088/xmgl//userfiles/upload/files/20180803143620366.doc";
//    private static final String BASE_URL = "http://192.168.200.15:8088/xmgl//userfiles/upload/files/201808061834294755.pdf";

    // 文件的存储路径
    private String BASE_PATH = Environment.getExternalStorageDirectory().toString();
    private String docName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        // 在代码中添加布局，这个我也不知道什么原因，网上很多人都说在布局文件中加载会出错

        mTbsReaderView = new TbsReaderView(this, this);
        mRelativeLayout = findViewById(R.id.tbsView);
        mRelativeLayout.addView(mTbsReaderView, new RelativeLayout.LayoutParams(-1, -1));

        // 日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new java.util.Date());
//        Log.d("success", "===" + date);

        // 给存储的文件添加后缀名，
        String fileType = getFileType(BASE_URL);
        BASE_PATH = BASE_PATH + date + "." + fileType;

        initDoc();

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
        }
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        web_bar = (ProgressBar) findViewById(R.id.web_bar);

        try {
            web_bar.getProgressDrawable().setColorFilter(Color.RED,
                    android.graphics.PorterDuff.Mode.SRC_IN);
        } catch (Exception e) {
        }

    }

    private void downloadFile(final String url, final String path) {
        RequestParams requestParams = new RequestParams(url);
        requestParams.setSaveFilePath(path);
        // 下载完成后自动为文件命名
        requestParams.setAutoRename(true);
        x.http().get(requestParams, new Callback.CacheCallback<File>() {
            @Override
            public boolean onCache(File result) {
                return false;
            }

            @Override
            public void onSuccess(File result) {
                Log.d("success", "下载成功" + path);
                // 下载成功后加载文件
                displayFile(BASE_PATH, docName);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("success", "下载失败" + ex.getMessage().toString());
                Log.d("success", "下载失败" + BASE_PATH+"-------"+docName);
                Toast.makeText(WebActivity.this, "文件下载失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTbsReaderView.onStop();
    }

    private void initDoc() {
        int i = BASE_URL.lastIndexOf("/");
        docName = BASE_URL.substring(i, BASE_URL.length());
        Log.d("print", "---substring---" + docName);
        // 下载文件
        downloadFile(BASE_URL, BASE_PATH);


    }


    @Override
    public void onCallBackAction(Integer integer, Object o, Object o1) {
        Log.d("call", "==================+++++====-=-=++" + integer);
    }

    private void displayFile(String filePath, String fileName) {

        //增加下面一句解决没有TbsReaderTemp文件夹存在导致加载文件失败
        String bsReaderTemp = BASE_PATH;
        File bsReaderTempFile = new File(bsReaderTemp);
        if (!bsReaderTempFile.exists()) {
            Log.d("print", "准备创建/TbsReaderTemp！！");
            boolean mkdir = bsReaderTempFile.mkdir();
            if (!mkdir) {
                Log.d("print", "创建/TbsReaderTemp失败！！！！！");
            }
        }
        Bundle bundle = new Bundle();
        bundle.putString("filePath", filePath);
        bundle.putString("tempPath", BASE_PATH);
        boolean result = mTbsReaderView.preOpen(getFileType(fileName), false);
        Log.d("print", "查看文档---" + result);
        if (result) {
            mTbsReaderView.openFile(bundle);
        } else {

        }
    }

    /**
     * 后缀名的判断
     *
     * @param paramString
     * @return
     */
    private String getFileType(String paramString) {
        String str = "";

        if (TextUtils.isEmpty(paramString)) {
            Log.d("print", "paramString---->null");
            return str;
        }
        Log.d("print", "paramString:" + paramString);
        int i = paramString.lastIndexOf('.');
        if (i <= -1) {
            Log.d("print", "i <= -1");
            return str;
        }

        str = paramString.substring(i + 1);
        Log.d("print", "paramString.substring(i + 1)------>" + str);
        return str;
    }


}