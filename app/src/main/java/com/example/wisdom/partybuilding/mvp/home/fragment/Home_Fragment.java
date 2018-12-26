package com.example.wisdom.partybuilding.mvp.home.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.WebActivity;
import com.example.wisdom.partybuilding.base.BaseFragment;
import com.example.wisdom.partybuilding.base.IPresenter;
import com.example.wisdom.partybuilding.mvp.bean.home.HomeDynamicBean;
import com.example.wisdom.partybuilding.mvp.home.adapter.FolderAdapter;
import com.example.wisdom.partybuilding.mvp.home.adapter.GlideImageLoader;
import com.example.wisdom.partybuilding.mvp.home.activity.NoticeAdapter;
import com.example.wisdom.partybuilding.mvp.home.activity.TidingsActivity;
import com.example.wisdom.partybuilding.mvp.common.MainActivity;
import com.example.wisdom.partybuilding.mvp.common.WebViewCurrencyActivity;
import com.example.wisdom.partybuilding.mvp.home.activity.DynamicActivity;
import com.example.wisdom.partybuilding.mvp.home.activity.PartyAffairsActivity;
import com.example.wisdom.partybuilding.mvp.home.activity.PayPartyFeesActivity;
import com.example.wisdom.partybuilding.mvp.home.activity.SearchActivity;
import com.example.wisdom.partybuilding.mvp.home.activity.LoginActivity;
import com.example.wisdom.partybuilding.mvp.bean.Home_CarouselmapBean;
import com.example.wisdom.partybuilding.mvp.bean.SuccessBean;
import com.example.wisdom.partybuilding.mvp.bean.home.DynamicBean;
import com.example.wisdom.partybuilding.mvp.bean.home.Home_noticeBean;
import com.example.wisdom.partybuilding.mvp.bean.login.AttestBean;
import com.example.wisdom.partybuilding.mvp.home.adapter.LandscapeAdapter;
import com.example.wisdom.partybuilding.net.Contants;
import com.example.wisdom.partybuilding.net.URLS;
import com.example.wisdom.partybuilding.utils.ToastUtils;
import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

public class Home_Fragment extends BaseFragment {
    @BindView(R.id.denglu)
    LinearLayout denglu;
    @BindView(R.id.home_search)
    LinearLayout homeSearch;
    @BindView(R.id.home_scanit)
    LinearLayout homeScanit;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.main_recycler)
    RecyclerView mainRecycler;
    @BindView(R.id.home_notice)
    LinearLayout homeNotice;
    @BindView(R.id.home_news_more)
    LinearLayout homeNewsMore;
    @BindView(R.id.home_news_recycler)
    RecyclerView homeNewsRecycler;

    @BindView(R.id.home_notice_detail)
    TextView homeNoticeDetail;

    @BindView(R.id.home_notice_detail_img)
    ImageView homeNoticeDetailImg;


    Unbinder unbinder;
    private List<HomeDynamicBean.CarouselmapBean> beans = new ArrayList<>();
    private List<DynamicBean.NewsBean> bwws = new ArrayList<>();
    private List<Home_CarouselmapBean.CarouselmapBean> carousel_urllist = new ArrayList<>();
        private FolderAdapter folderAdapter;
    private LandscapeAdapter landscapeAdapter;
    private NoticeAdapter folderAdapter1;
    private SuccessBean successBean = new SuccessBean();


    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initEventAndData() {
        getdata();
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        folderAdapter1 = new NoticeAdapter(getActivity(), bwws);
        homeNewsRecycler.setLayoutManager(linearLayoutManager1);
        homeNewsRecycler.setNestedScrollingEnabled(false);
        homeNewsRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        homeNewsRecycler.setAdapter(folderAdapter1);
        folderAdapter1.setOnClickLinstener(new NoticeAdapter.onClickLinstener() {
            @Override
            public void setOnClick(View view, int position) {
                WebViewCurrencyActivity.start(getActivity(), "党委新闻", URLS.HOME_NEWS_DETAIL + bwws.get(position).getId());
            }
        });

    }


    @Override
    protected int getLayoutId() {
        return R.layout.home_home_fragment_layout;
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    private void getdata() {
        tipDialog.show();
        getBanner();// 首页——轮播图
        getTidings();// 首页——新闻
        getNotice();// 首页——通知
        getDynamic();// 首页——动态
        tipDialog.cancel();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.denglu, R.id.home_search, R.id.home_scanit, R.id.banner, R.id.home_notice, R.id.home_news_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.denglu://登陆
                LoginActivity.start(getActivity());
//                getActivity().finish();
                break;
            case R.id.home_search://搜索框
                SearchActivity.start(getActivity());
                break;
            case R.id.home_scanit:
                ToastUtils.getInstance().showTextToast(getActivity(), "此功能暂不开发");
                break;
            case R.id.banner:
                break;
            case R.id.home_notice:

//view.officeapps.live.com/op/view.aspx?src=http://www.ncac.gov.cn/sitefiles/services/wcm/utils.aspx?type=Download&publishmentSystemID=470&channelID=574&contentID=20880

//                WebActivity.start(getActivity());//文件阅读  暂时不能正常使用

//                tipDialog.show();
//                Main3Activity.start(getActivity());//文件阅读  暂时不能正常使用


//                WebViewCurrencyActivity.start(getActivity(),"视频","https://pic.ibaotu.com/01/03/85/49e888piCpxw.mp4");

//                Main2Activity.start(getActivity());//视频播放

//                if (homeNoticeDetailImg.getVisibility() == View.VISIBLE) {
//                    ToastUtils.getInstance().showTextToast(getActivity(), "进入通知");
//                } else {
//                    ToastUtils.getInstance().showTextToast(getActivity(), "暂无通知");
//                }
                downloadFile();
                break;
            case R.id.home_news_more://党政新闻——更多
                TidingsActivity.start(getActivity());
                break;
        }
    }


    public void downloadFile() {

//        String url="http://vf2.mtime.cn/Video/2017/03/15/mp4/170315222409670447.mp4";
        String url = "http://www.ncac.gov.cn/sitefiles/services/wcm/utils.aspx?type=Download&publishmentSystemID=470&channelID=574&contentID=20880";

        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(),
                        "okHttpests.doc") {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "onError :" + e.getMessage());
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        //super.inProgress(progress, total, id);

                        Log.e("TAG", "inProgress" + (int) (100 * progress));
                    }

                    @Override
                    public void onResponse(File file, int id) {
                        Log.e("TAG", "onResponse :" + file.getAbsolutePath());


                        String finalUrl = "http://view.officeapps.live.com/op/view.aspx?src=" + file.getAbsolutePath() + "";
                        Log.e("tag_finalUrl", finalUrl + "");
//                        mWebView.loadUrl(finalUrl);

//                        WebActivity.start(getActivity(),finalUrl);

                    }
                });
    }

    public void downloadFile(String url, String houZuiName) {


        OkHttpUtils
                .get()
                .url(url)
                .build()
//                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), Conts.NEW_APP_NAME) {
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), houZuiName) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
//                        enterLoginActivity();
                        System.out.println("SplashActivity.onError=哈哈哈");
//                        ToastUtils.showToast("下载更新包失败");

                        Log.e("TAG", "下载更新包失败");
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
//                        dialog.setProgress((int) (100 * progress));
                    }

                    @Override
                    public void onResponse(File response, int id) {

                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_DEFAULT);
                        intent.setDataAndType(Uri.fromFile(response),
                                "application/vnd.android.package-archive");
                        startActivityForResult(intent, 0);
                    }
                });


    }


    /*
     * 轮播图
     * */
    private void getcarouselmapurl() {


        /**
         * 网络请求
         *      land
         */
//        HttpUtils.getInstance().get(URLS.HOME_CAROUSELMAP, null, new MyCallBack<Home_CarouselmapBean>() {
//            @Override
//            public void onSuccess(Home_CarouselmapBean s) {
//                Log.e("TAG", "11111111111qqqqqqqq:" + GsonUtil.GsonString(s));
//
//            }
//
//            @Override
//            public void onFaile(String msg) {
//                Log.e("TAG", "11111111111111msg:" + msg);
//            }
//        });


    }


    /*
     * 待办
     * */
    private void getnotification() {
        OkHttpUtils
                .get()
                .url(URLS.HOMENOTIFICATION)
                .addParams("sid", successBean.getSid())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        homeNoticeDetail.setText("暂无通知");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            Gson gson = new Gson();
                            Home_noticeBean bean = gson.fromJson(response, Home_noticeBean.class);
                            homeNoticeDetail.setText(bean.getTitle());
                            homeNoticeDetailImg.setVisibility(View.VISIBLE);
                        } catch (Exception e) {
                            homeNoticeDetail.setText("暂无通知");
                        }
                    }
                });
    }

    /*
     * 首页——轮播图
     * */
    private void getBanner() {


        OkHttpUtils
                .get()
                .url(URLS.HOME_CAROUSELMAP)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "这是失败的方法1" + e.toString());

                        List<String> strings = new ArrayList<>();
                        for (int i = 0; i < 3; i++) {
                            strings.add("ssss");
                        }


                        //        //设置图片加载器
                        banner.setImageLoader(new GlideImageLoader());
                        //        //设置图片集合
                        banner.setImages(strings);
                        //        //banner设置方法全部调用完毕时最后调用
//                        banner.setIndicatorGravity(BannerConfig.CENTER);
                        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                        banner.start();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
//                        {"carouselmap":
                        String responses = "{\"carouselmap\":" + response + "}";
//                        String responses = "{carouselmap:" + response + "}";
                        Home_CarouselmapBean bean = gson.fromJson(responses, Home_CarouselmapBean.class);
                        carousel_urllist.addAll(bean.getCarouselmap());

                        List<String> strings = new ArrayList<>();
                        for (int i = 0; i < carousel_urllist.size(); i++) {
                            String imgurl = carousel_urllist.get(i).getImgurl();
                            String s3 = imgurl.replaceAll("9998", "9999");
                            strings.add(s3);
                        }
                        //        //设置图片加载器
                        banner.setImageLoader(new GlideImageLoader());
                        //        //设置图片集合
                        banner.setImages(strings);
                        //        //banner设置方法全部调用完毕时最后调用
//                        banner.setIndicatorGravity(BannerConfig.CENTER);
                        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                        banner.start();


                    }
                });

    }

    /*
     * 首页——动态
     * */
    private void getDynamic() {
//        HOME_DYNAMIC_LIST

        OkHttpUtils
                .get()
                .url(URLS.HOME_DYNAMIC_LIST)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "这是失败的方法1" + e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        String responses = "{carouselmap:" + response + "}";
                        HomeDynamicBean bean = gson.fromJson(responses, HomeDynamicBean.class);
                        beans.addAll(bean.getCarouselmap());
//                        folderAdapter.notifyDataSetChanged();
//                        landscapeAdapter.notifyDataSetChanged();


                        if (beans.size()<5){
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
                            gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
                            mainRecycler.setLayoutManager(gridLayoutManager);
                        }else {
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                            gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
                            mainRecycler.setLayoutManager(gridLayoutManager);
                        }

                        //设置适配器
                        landscapeAdapter = new LandscapeAdapter(getActivity(), beans);
                        mainRecycler.setAdapter(landscapeAdapter);
                        landscapeAdapter.setOnClickLinstener(
                                new FolderAdapter.onClickLinstener() {
                                    @Override
                                    public void setOnClick(View view, int position) {

                                        if (beans.get(position).getFunctionname().equals("中央精神")) {
                                            WebViewCurrencyActivity.start(getActivity(), "中央精神", URLS.DYNAMICMODULE + "中央精神");
                                        } else if (beans.get(position).getFunctionname().equals("党组声音")) {
                                            WebViewCurrencyActivity.start(getActivity(), "党组声音", URLS.DYNAMICMODULE + "党组声音");
                                        } else if (beans.get(position).getFunctionname().equals("党委新闻")) {
                                            DynamicActivity.start(getActivity(), "党委新闻");
                                        } else if (beans.get(position).getFunctionname().equals("基层交流")) {
                                            DynamicActivity.start(getActivity(), "基层交流");
                                        } else if (beans.get(position).getFunctionname().equals("学习园地")) {
                                            DynamicActivity.start(getActivity(), "学习园地");
                                        } else if (beans.get(position).getFunctionname().equals("党务知识")) {
                                            PartyAffairsActivity.start(getActivity(), "党务知识");
                                        } else if (beans.get(position).getFunctionname().equals("在线考试")) {
                                            WebViewCurrencyActivity.start(getActivity(), "在线考试", URLS.ONLINEEXAM + successBean.getSid() + "&pid=" + successBean.getPid());
                                        } else if (beans.get(position).getFunctionname().equals("缴纳党费")) {
                                            PayPartyFeesActivity.start(getActivity());
                                        } else if (beans.get(position).getFunctionname().equals("三会一课")) {
                                            ToastUtils.getInstance().showTextToast(getActivity(), "暂无内容");
                                        }
                                    }
                                }
                        );

                    }
                });

    }

    /*
     * 首页——通知
     * */
    private void getNotice() {

        if (Hawk.contains(Contants.loginInformation)) {
            successBean = Hawk.get(Contants.loginInformation);
            OkHttpUtils
                    .get()
                    .url(URLS.REVERIFICATION)
                    .addParams("sid", successBean.getSid())
                    .addParams("pid", successBean.getPid())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            homeNoticeDetail.setText("暂无通知");
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Gson gson = new Gson();
                            AttestBean bean = gson.fromJson(response, AttestBean.class);
                            if (bean.isCode()) {
                                getnotification();
                            } else {
                                homeNoticeDetail.setText("暂无通知");
                            }
                        }
                    });
        } else {
            homeNoticeDetail.setText("暂无通知");
        }

    }


    /*
     * 首页——新闻
     * */
    private void getTidings() {
        OkHttpUtils
                .get()
                .url(URLS.HOME_DYNAMIC)
                .addParams("itemName", "党委新闻")
                .addParams("pageIndex", "1")
                .addParams("pageSize", "4")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "这是失败的方法1" + e.toString());

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        DynamicBean bean = gson.fromJson(response, DynamicBean.class);
                        bwws.addAll(bean.getNews());
                        folderAdapter1.notifyDataSetChanged();
                    }
                });

    }

}
