package com.example.wisdom.partybuilding.mvp.home.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.base.BaseFragment;
import com.example.wisdom.partybuilding.base.IPresenter;
import com.example.wisdom.partybuilding.mvp.bean.Home_CarouselmapBean;
import com.example.wisdom.partybuilding.mvp.bean.NoticeBeanno1;
import com.example.wisdom.partybuilding.mvp.bean.SuccessBean;
import com.example.wisdom.partybuilding.mvp.bean.home.DynamicBean;
import com.example.wisdom.partybuilding.mvp.bean.home.HomeDynamicBean;
import com.example.wisdom.partybuilding.mvp.bean.home.SignInBean;
import com.example.wisdom.partybuilding.mvp.bean.login.AttestBean;
import com.example.wisdom.partybuilding.mvp.common.MainActivity;
import com.example.wisdom.partybuilding.mvp.common.WebViewCurrencyActivity;
import com.example.wisdom.partybuilding.mvp.home.activity.DynamicActivity;
import com.example.wisdom.partybuilding.mvp.home.activity.LoginActivity;
import com.example.wisdom.partybuilding.mvp.home.activity.NoticeAdapter;
import com.example.wisdom.partybuilding.mvp.home.activity.PartyAffairsActivity;
import com.example.wisdom.partybuilding.mvp.home.activity.PayPartyFeesActivity;
import com.example.wisdom.partybuilding.mvp.home.activity.SearchActivity;
import com.example.wisdom.partybuilding.mvp.home.activity.ThreeSessionsActivity;
import com.example.wisdom.partybuilding.mvp.home.activity.TidingsActivity;
import com.example.wisdom.partybuilding.mvp.home.adapter.FolderAdapter;
import com.example.wisdom.partybuilding.mvp.home.adapter.GlideImageLoader;
import com.example.wisdom.partybuilding.mvp.home.adapter.LandscapeAdapter;
import com.example.wisdom.partybuilding.net.Contants;
import com.example.wisdom.partybuilding.net.URLS;
import com.example.wisdom.partybuilding.utils.CommonUtil;
import com.example.wisdom.partybuilding.utils.DateUtils;
import com.example.wisdom.partybuilding.utils.ToastUtils;
import com.example.wisdom.partybuilding.zxing.activity.CaptureActivity;
import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.net.URLEncoder;
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

    @BindView(R.id.home_swiperefreshlayout)
    SwipeRefreshLayout homeSwiperefreshlayout;


    //打开扫描界面请求码
    private int REQUEST_CODE = 0x01;
    //扫描成功返回码
    private int RESULT_OK = 0xA1;


    Unbinder unbinder;
    private List<HomeDynamicBean.CarouselmapBean> beans = new ArrayList<>();
    private List<DynamicBean.NewsBean> bwws = new ArrayList<>();
    private List<Home_CarouselmapBean.CarouselmapBean> carousel_urllist = new ArrayList<>();
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
                WebViewCurrencyActivity.start(getActivity(), "党委新闻", URLS.HOME_NEWS_DETAIL + bwws.get(position).getId(), bwws.get(position).getTitile());
            }
        });

        homeSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getdata();

                if (homeSwiperefreshlayout.isRefreshing()) {//如果正在刷新
                    homeSwiperefreshlayout.setRefreshing(false);//取消刷新
                }
            }
        });


        homeSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getdata();
                ToastUtils.getInstance().showTextToast(getActivity(), "下啦刷新");
                if (homeSwiperefreshlayout.isRefreshing()) {//如果正在刷新
                    homeSwiperefreshlayout.setRefreshing(false);//取消刷新
                }
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

//                ScanItActivity.start(getActivity());

                //打开二维码扫描界面
                if (CommonUtil.isCameraCanUse()) {
                    Intent intent = new Intent(getActivity(), CaptureActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);
                } else {
                    ToastUtils.getInstance().showTextToast(getActivity(), "请打开此应用的摄像头权限！");
                }


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


//                downloadFile();
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
     * 首页——通知
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
                            NoticeBeanno1 noticeBeanno1 = gson.fromJson(response, NoticeBeanno1.class);
                            String title = noticeBeanno1.getTitle();
                            homeNoticeDetail.setText(title);
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


                        if (beans.size() < 5) {
                            List<HomeDynamicBean.CarouselmapBean> carouselmap = bean.getCarouselmap();
                            String functionname = carouselmap.get(0).getFunctionname();
                            if (functionname.length() == 0) {
                                mainRecycler.setVisibility(View.GONE);
                            } else {
                                mainRecycler.setVisibility(View.VISIBLE);
                            }


                            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
                            gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
                            mainRecycler.setLayoutManager(gridLayoutManager);
                        } else {
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
                                            WebViewCurrencyActivity.start(getActivity(), "中央精神", URLS.DYNAMICMODULE + "中央精神", "hide");
                                        } else if (beans.get(position).getFunctionname().equals("党组声音")) {
                                            WebViewCurrencyActivity.start(getActivity(), "党组声音", URLS.DYNAMICMODULE + "党组声音", "hide");
                                        } else if (beans.get(position).getFunctionname().equals("党委新闻")) {
                                            DynamicActivity.start(getActivity(), "党委新闻");
                                        } else if (beans.get(position).getFunctionname().equals("基层交流")) {
                                            DynamicActivity.start(getActivity(), "基层交流");
                                        } else if (beans.get(position).getFunctionname().equals("学习园地")) {
                                            DynamicActivity.start(getActivity(), "学习园地");
                                        } else if (beans.get(position).getFunctionname().equals("党务知识")) {
//                                            DynamicActivity.start(getActivity(), "党务知识");
                                            PartyAffairsActivity.start(getActivity(), "党务知识");
                                        } else if (beans.get(position).getFunctionname().equals("在线考试")) {
                                            WebViewCurrencyActivity.start(getActivity(), "在线考试", URLS.ONLINEEXAM + successBean.getSid() + "&pid=" + successBean.getPid(), "hide");
                                        } else if (beans.get(position).getFunctionname().equals("党费缴纳")) {
                                            PayPartyFeesActivity.start(getActivity());
                                        } else if (beans.get(position).getFunctionname().equals("三会一课")) {
                                            ThreeSessionsActivity.start(getActivity());
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
        if (resultCode == RESULT_OK) { //RESULT_OK = -1
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("qr_scan_result");
            try {
                String pid = successBean.getPid();
                String username = successBean.getUsername();
                long userid = successBean.getUserid();
                String encode = URLEncoder.encode(username, "utf-8");
                String strurl = scanResult + "&username=" + encode + "&userid=" + userid + "&pid=" + pid;
                Log.e("TAG", "strurl" + strurl);
                getScanningResult(strurl);

            } catch (Exception e) {
            }
        }
    }

    private void getScanningResult(String url) {

        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "这是失败的方法1" + e.toString());

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "response:" + response);


                        try {
                            Gson gson = new Gson();
                            SignInBean bean = gson.fromJson(response, SignInBean.class);

                            if (bean.getStatus().equals("fail")) {
                                showPopwindowMenuException(getActivity(), bean.getMsg());
                            } else {
                                String meetingname = bean.getMeetingname();
                                String meetingaddress = bean.getMeetingaddress();
                                long meetingtime = bean.getMeetingtime();
                                long meingtime = meetingtime / 1000;
                                showPopwindowMenu(getActivity(), meetingname, meetingaddress, meingtime + "");
                            }
                        } catch (Exception e) {
                            showPopwindowMenuException(getActivity(), null);

                        }

                    }
                });
    }

    private void showPopwindowMenu(Activity activity, String name, String site, String time) {

        View popView = View.inflate(getActivity(), R.layout.reset_pwd_sure, null);
        ImageView popwImg = (ImageView) popView.findViewById(R.id.popw_img);
        TextView popwImgName = (TextView) popView.findViewById(R.id.popw_img_name);
        ImageView popwClosed = (ImageView) popView.findViewById(R.id.popw_closed);
        TextView popwName = (TextView) popView.findViewById(R.id.popw_name);
        TextView popwTime = (TextView) popView.findViewById(R.id.popw_time);
        TextView popwPlace = (TextView) popView.findViewById(R.id.popw_place);
        TextView popwVerify = (TextView) popView.findViewById(R.id.popw_verify);
        try {
            popwName.setText(name);
            popwPlace.setText(site);

            String releasetimes = DateUtils.timesTwo(time);
            popwTime.setText(releasetimes);
        } catch (Exception e) {
        }

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        final PopupWindow popWindow = new PopupWindow(popView, width, height);
        popWindow.setContentView(popView);
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(false);// 设置允许在外点击消失
//        View.OnClickListener listener = new View.OnClickListener() {
//            public void onClick(View v) {
//                switch (v.getId()) {
//                    case R.id.popw_closed:
//                        Toast.makeText(getActivity(), "关闭", Toast.LENGTH_SHORT).show();
//                        popWindow.dismiss();
//                        break;
//                    case R.id.popw_verify:
//                        Toast.makeText(getActivity(), "确认", Toast.LENGTH_SHORT).show();
//                        popWindow.dismiss();
//                        break;
//                }
//                popWindow.dismiss();
//            }
//        };
//        popwClosed.setOnClickListener(listener);

        popwClosed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popWindow.dismiss();
            }
        });


        popwVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popWindow.dismiss();
            }
        });
        // 设置好参数之后再show
        popWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
    }

    private void showPopwindowMenuException(Activity activity, String msg) {

        View popView = View.inflate(getActivity(), R.layout.reset_pwd_sure_ex, null);
        ImageView popwImg = (ImageView) popView.findViewById(R.id.popw2_img);
        TextView popwImgName = (TextView) popView.findViewById(R.id.popw2_img_name);
        ImageView popwClosed = (ImageView) popView.findViewById(R.id.popw2_closed);
        TextView popwPlace = (TextView) popView.findViewById(R.id.popw2_place);
        TextView popwVerify = (TextView) popView.findViewById(R.id.popw_verify);
        if (msg != null && msg.length() != 0) {
            popwPlace.setText(msg);
        }

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        final PopupWindow popWindow = new PopupWindow(popView, width, height);
        popWindow.setContentView(popView);
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(false);// 设置允许在外点击消失

        popwClosed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dismiss();
            }
        });

        popwVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dismiss();
            }
        });
        // 设置好参数之后再show
        popWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
    }

}
