package com.example.wisdom.partybuilding.mvp.common;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.ShareUtils;
import com.example.wisdom.partybuilding.base.BaseActivity;
import com.example.wisdom.partybuilding.base.BasePresenter;
import com.example.wisdom.partybuilding.utils.ToastUtils;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.BindView;
import butterknife.OnClick;

public class WebViewCurrencyActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.user_icon)
    ImageView userIcon;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.user_share)
    ImageView userShare;

    private String url;
    private String title;
    private String titlename;


    public static void start(Context context, String title, String url,String titlename) {
        Intent intent = new Intent(context, WebViewCurrencyActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        intent.putExtra("titlename", titlename);

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

        titlename = getIntent().getStringExtra("titlename");

        if (titlename.equals("titile")){
            userShare.setVisibility(View.GONE);
        }else {
            userShare.setVisibility(View.VISIBLE);
        }

        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        if (TextUtils.isEmpty(title)) {
            tvTitle.setText("智慧党建");
        } else {
            tvTitle.setText(title);
        }

        initWebData(url);
    }


    private void initWebData(String html) {
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setDomStorageEnabled(true);//允许DCOM
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl(html);

    }

    @OnClick({R.id.user_icon, R.id.user_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_icon:
                finish();
                break;
            case R.id.user_share:
                actionShare(url);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 分享
     */
//    private void actionShare(final int id, final String biaoti, final String name) {
    private void actionShare(final String urls) {
        final int TAG_SHARE_WECHAT_FRIEND = 0;
        final int TAG_SHARE_WECHAT_MOMENT = 1;
        final int TAG_SHARE_WEIBO = 2;
        QMUIBottomSheet.BottomGridSheetBuilder builder = new QMUIBottomSheet.BottomGridSheetBuilder(this);
        builder
                .setButtonText("取消分享")
                .addItem(R.mipmap.icon_more_operation_share_friend, "微信好友", TAG_SHARE_WECHAT_FRIEND, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.icon_more_operation_share_moment, "微信朋友圈", TAG_SHARE_WECHAT_MOMENT, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.icon_more_operation_share_qq, "QQ", TAG_SHARE_WEIBO, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
//                .addItem(R.mipmap.icon_more_operation_share_weibo, "新浪微博", TAG_SHARE_CHAT, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .setOnSheetItemClickListener(new QMUIBottomSheet.BottomGridSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(QMUIBottomSheet dialog, View itemView) {
                        dialog.dismiss();
                        int tag = (int) itemView.getTag();
                        switch (tag) {
                            case TAG_SHARE_WECHAT_FRIEND:
                                sharedToFriends(urls,titlename);
                                break;
                            case TAG_SHARE_WECHAT_MOMENT:
                                sharedToMoment(urls,titlename);
                                break;
                            case TAG_SHARE_WEIBO:
                                sharedToQQ(urls,titlename);
                                break;
                        }
                    }


                }).build().show();
    }

    /**
     * 分享到微信
     */
    private void sharedToFriends(String biaoti,String content) {
        ShareUtils.shareWeb(activity, biaoti,  tvTitle.getText().toString(), content, SHARE_MEDIA.WEIXIN);
    }

    /**
     * 分享到朋友圈
     */
    private void sharedToMoment(String biaoti,String content) {
        ShareUtils.shareWeb(activity, biaoti,   tvTitle.getText().toString(), content, SHARE_MEDIA.WEIXIN_CIRCLE);
    }

    /**
     * 分享到QQ
     */
    private void sharedToQQ(String biaoti,String content) {
        ShareUtils.shareWeb(activity, biaoti,  tvTitle.getText().toString(), "分享了他的材料，请点击查看", SHARE_MEDIA.QQ);
    }

}
