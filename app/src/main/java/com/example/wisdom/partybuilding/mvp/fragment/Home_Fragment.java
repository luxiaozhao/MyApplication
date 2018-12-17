package com.example.wisdom.partybuilding.mvp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.base.BaseFragment;
import com.example.wisdom.partybuilding.base.IPresenter;
import com.example.wisdom.partybuilding.mvp.activity.adapter.home.FolderAdapter;
import com.example.wisdom.partybuilding.mvp.activity.adapter.home.GlideImageLoader;
import com.example.wisdom.partybuilding.mvp.activity.adapter.home.NoticeAdapter;
import com.example.wisdom.partybuilding.mvp.activity.adapter.home.TidingsActivity;
import com.example.wisdom.partybuilding.mvp.activity.commonactivity.MainActivity;
import com.example.wisdom.partybuilding.mvp.activity.commonactivity.WebViewCurrencyActivity;
import com.example.wisdom.partybuilding.mvp.activity.home.BaseWebActivity;
import com.example.wisdom.partybuilding.mvp.activity.home.PayPartyFeesActivity;
import com.example.wisdom.partybuilding.mvp.activity.home.SearchActivity;
import com.example.wisdom.partybuilding.mvp.activity.login.LoginActivity;
import com.example.wisdom.partybuilding.mvp.bean.Bean;
import com.example.wisdom.partybuilding.mvp.bean.Bww;
import com.example.wisdom.partybuilding.mvp.bean.Home_CarouselmapBean;
import com.example.wisdom.partybuilding.mvp.bean.SuccessBean;
import com.example.wisdom.partybuilding.mvp.bean.home.Home_noticeBean;
import com.example.wisdom.partybuilding.mvp.bean.login.AttestBean;
import com.example.wisdom.partybuilding.net.Contants;
import com.example.wisdom.partybuilding.net.URLS;
import com.example.wisdom.partybuilding.utils.ToastUtils;
import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

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
    private List<Bean> beans;
    private List<Bww> bwws = new ArrayList<>();
    private List<Home_CarouselmapBean.CarouselmapBean> carousel_urllist = new ArrayList<>();
    private FolderAdapter folderAdapter;
    private NoticeAdapter folderAdapter1;
    private SuccessBean successBean = new SuccessBean();

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initEventAndData() {
        getdata();

        getcarouselmapurl();
        getverification();

        folderAdapter = new FolderAdapter(getActivity(), beans);
//        new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL);
//        mainRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mainRecycler.setLayoutManager(manager);


        mainRecycler.setNestedScrollingEnabled(false);


        mainRecycler.setAdapter(folderAdapter);

        folderAdapter.setOnClickLinstener(new FolderAdapter.onClickLinstener() {
            @Override
            public void setOnClick(View view,final int position) {

                if (Hawk.contains(Contants.loginInformation)){
                   final SuccessBean successBean= Hawk.get(Contants.loginInformation);
                    OkHttpUtils
                            .get()
                            .url(URLS.REVERIFICATION)
                            .addParams("sid", successBean.getSid())
                            .addParams("pid", successBean.getPid())
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    Log.e("TAG", "这是失败的方法1" + e.toString());
                                    ToastUtils.getInstance().showTextToast(getActivity(),"请登陆后继续访问");

                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Gson gson = new Gson();
                                    AttestBean bean = gson.fromJson(response, AttestBean.class);
                                    if (bean.isCode()) {
//                                        WebViewCurrencyActivity.start(getActivity(), "个人资料", successBean.getSid());
                                        switch (position) {
                                            case 0:
//                                                http://192.168.1.199:9999/FHBE/mobile/mobileNews/mobileNews/listnews.ht?pageSize=5&pageIndex=2&sid=06478AC99947E8A1987536338B1BF4B8&itemName=中央精神
                                                WebViewCurrencyActivity.start(getActivity(),"中央精神",URLS.DYNAMICMODULE+"中央精神");

                                                break;
                                            case 1:
                                                WebViewCurrencyActivity.start(getActivity(),"党组声音",URLS.DYNAMICMODULE+"党组声音");

                                                break;
                                            case 2:

                                                WebViewCurrencyActivity.start(getActivity(),"党委新闻",URLS.DYNAMICMODULE+"党委新闻");
                                                break;
                                            case 3:

                                                WebViewCurrencyActivity.start(getActivity(),"基层交流",URLS.DYNAMICMODULE+"基层交流");
                                                break;
                                            case 4:
                                                WebViewCurrencyActivity.start(getActivity(),"学习园地",URLS.DYNAMICMODULE+"基层交流");

//                                                ToastUtils.getInstance().showTextToast(getActivity(),position+"");
//                                                WebViewCurrencyActivity.start(getActivity(),"党务知识",URLS.ONLINEEXAM+"?sid="+successBean.getSid());
//                                                ToastUtils.getInstance().showTextToast(getActivity(),position+"");
                                                break;
                                            case 5:
                                                ToastUtils.getInstance().showTextToast(getActivity(),position+"");
                                                WebViewCurrencyActivity.start(getActivity(),"党务知识",URLS.ONLINEEXAM+"?sid="+successBean.getSid());
                                                ToastUtils.getInstance().showTextToast(getActivity(),position+"");
                                                break;
                                            case 6:
//                        http://192.168.1.199:9999/FHBE/exam/course/appcourse.ht?sid=4C2FBBDF20296509EDAD89DD6858F705
                                                WebViewCurrencyActivity.start(getActivity(),"在线考试",URLS.ONLINEEXAM+"?sid="+successBean.getSid());
                                                ToastUtils.getInstance().showTextToast(getActivity(),position+"");
                                                break;
                                            case 7://缴纳党费

                                                PayPartyFeesActivity.start(getActivity());
                                                break;
                                            default:
                                                ToastUtils.getInstance().showTextToast(getActivity(),"没有对应的选项");
                                                break;
                                        }

                                    }else {
                                        ToastUtils.getInstance().showTextToast(getActivity(),bean.getMsg());
                                    }
                                }
                            });

                }else {
                    ToastUtils.getInstance().showTextToast(getActivity(),"请登陆后继续访问");
                }

//                BaseWebActivity.start(getActivity(), beans.get(position).getString());
            }
        });

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);  //     reverse  layout
//        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        folderAdapter1 = new NoticeAdapter(getActivity(), bwws);
        homeNewsRecycler.setLayoutManager(linearLayoutManager1);
//        homeNewsRecycler.setNestedScrollingEnabled(true);//是否滚动
        homeNewsRecycler.setNestedScrollingEnabled(false);

        homeNewsRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        homeNewsRecycler.setAdapter(folderAdapter1);

        folderAdapter1.setOnClickLinstener(new NoticeAdapter.onClickLinstener() {
            @Override
            public void setOnClick(View view, int position) {

//                TidingsActivity.start(getActivity());

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
        bwws.clear();
        for (int i = 0; i < 4; i++) {
            Bww bww = new Bww();
            bww.setName("按实际库存你上课经常看见和出口市场卡死啊飒飒阿萨飒飒啊飒飒");
            bww.setShijian("2018年12月12日");
            bwws.add(bww);
        }

        beans = new ArrayList<>();
        Bean bean1 = new Bean();
        bean1.setString("中央精神");
        bean1.setImg(R.mipmap.centralspirit);

        Bean bean2 = new Bean();
        bean2.setString("党组声音");
        bean2.setImg(R.mipmap.r44);//r44    r22

        Bean bean3 = new Bean();
        bean3.setString("党委新闻");
        bean3.setImg(R.mipmap.r22);//r55        r33

        Bean bean4 = new Bean();
        bean4.setString("基层交流");//grassroots_exchange
        bean4.setImg(R.mipmap.grassroots_exchange);

        Bean bean5 = new Bean();
        bean5.setString("学习园地");
        bean5.setImg(R.mipmap.g66);
//        http://119.80.161.8:9999/FHBE/mobile/mobileNews/mobileNews/search.ht?param=&pageSize=2&pageIndex=2
        Bean bean6 = new Bean();
        bean6.setString("党务知识");
        bean6.setImg(R.mipmap.r33);//r55 `   g66


        Bean bean7 = new Bean();
        bean7.setString("在线考试");
        bean7.setImg(R.mipmap.examination);//examination

        Bean bean8 = new Bean();
        bean8.setString("缴纳党费");
        bean8.setImg(R.mipmap.pay_party_fees);//pay_party_fees

        beans.add(bean1);
        beans.add(bean2);
        beans.add(bean3);
        beans.add(bean4);
        beans.add(bean5);
        beans.add(bean6);
        beans.add(bean7);
        beans.add(bean8);


        beans.add(bean1);
        beans.add(bean2);
        beans.add(bean3);
        beans.add(bean4);
        beans.add(bean5);
        beans.add(bean6);
        beans.add(bean7);
        beans.add(bean8);
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
                getActivity().finish();
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

                if (homeNoticeDetailImg.getVisibility()==View.VISIBLE){
                    ToastUtils.getInstance().showTextToast(getActivity(),"进入通知");
                }else {
                    ToastUtils.getInstance().showTextToast(getActivity(),"暂无通知");
                }

                break;
            case R.id.home_news_more://党政新闻——更多
                TidingsActivity.start(getActivity());
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        getverification();
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
                        String responses = "{carouselmap:" + response + "}";
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
     *   验证 通知
     * */
    private void getverification() {
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
     * 通知
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


}