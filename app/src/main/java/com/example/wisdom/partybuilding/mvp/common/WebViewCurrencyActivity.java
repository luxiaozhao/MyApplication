package com.example.wisdom.partybuilding.mvp.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.base.BaseActivity;
import com.example.wisdom.partybuilding.base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebViewCurrencyActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.user_icon)
    ImageView userIcon;
    @BindView(R.id.webview)
    WebView webview;
    private String url;
    private String title;


    public static void start(Context context,String title,String url) {
        Intent intent = new Intent(context, WebViewCurrencyActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view_currency;
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

        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        if(TextUtils.isEmpty(title)){
            tvTitle.setText("智慧党建");
        }else{
            tvTitle.setText(title);
        }

        initWebData(url);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.user_icon)
    public void onViewClicked() {
        finish();
    }
    private void initWebData(String html) {
        WebSettings webSettings=webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setDomStorageEnabled(true);//允许DCOM


        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl(html);


    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_web_view_currency);
//    }
}
