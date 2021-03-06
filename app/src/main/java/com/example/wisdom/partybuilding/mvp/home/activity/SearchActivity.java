package com.example.wisdom.partybuilding.mvp.home.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wisdom.partybuilding.R;
import com.example.wisdom.partybuilding.base.BaseActivity;
import com.example.wisdom.partybuilding.base.BasePresenter;
import com.example.wisdom.partybuilding.mvp.adapter.SearchAdapter;
import com.example.wisdom.partybuilding.mvp.bean.home.DynamicBean;
import com.example.wisdom.partybuilding.mvp.common.WebViewCurrencyActivity;
import com.example.wisdom.partybuilding.net.URLS;
import com.example.wisdom.partybuilding.utils.ToastUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/*
 * 首页——搜索
 * */
public class SearchActivity extends BaseActivity {


    @BindView(R.id.search_return)
    ImageView searchReturn;
    @BindView(R.id.search_search)
    EditText searchSearch;
    @BindView(R.id.search_recycler)
    RecyclerView searchRecycler;
    private InputMethodManager manager;//输入法管理器
    private SearchAdapter searchAdapter;
    private List<DynamicBean.NewsBean> newsBeans = new ArrayList<>();

    public static void start(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        hideSoftKeyboard(this);
//        KeyboardUtils.hideKeyboard();
        //搜索框
        manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        searchSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

//                android:focusable="false"


                //先隐藏键盘
                if (manager.isActive()) {
                    manager.hideSoftInputFromWindow(searchSearch.getApplicationWindowToken(), 0);
                }
                //自己需要的操作
                if (searchSearch.getText().toString().trim() != null && searchSearch.length() != 0) {


                    String param = searchSearch.getText().toString();
                    getdata(param);

                    hideSoftKeyboard(SearchActivity.this);
                } else {
                    ToastUtils.getInstance().showTextToast(SearchActivity.this, "没有数据");
                }
                return false;
            }
        });


        searchAdapter = new SearchAdapter(this, newsBeans);
        searchRecycler.setNestedScrollingEnabled(false);
        searchRecycler.setLayoutManager(new GridLayoutManager(this, 1));
        searchRecycler.setAdapter(searchAdapter);

        searchAdapter.setOnClickLinstener(new SearchAdapter.onClickLinstener() {
            @Override
            public void setOnClick(View view, int position) {
                if (newsBeans.get(position).getTypename().equals("党委新闻")) {
                    WebViewCurrencyActivity.start(activity, newsBeans.get(position).getTypename(), URLS.HOME_NEWS_DETAIL + newsBeans.get(position).getId(),newsBeans.get(position).getTitile());
                } else if (newsBeans.get(position).getTypename().equals("基层交流")) {
                    WebViewCurrencyActivity.start(activity, newsBeans.get(position).getTypename(), URLS.HOME_BASICLEVEL_DETAIL + newsBeans.get(position).getId(),newsBeans.get(position).getTitile());
                } else if (newsBeans.get(position).getTypename().equals("学习园地")) {
                    WebViewCurrencyActivity.start(activity, newsBeans.get(position).getTypename(), URLS.HOME_LEARNING_DETAIL + newsBeans.get(position).getId(),newsBeans.get(position).getTitile());
                } else {
                    ToastUtils.getInstance().showTextToast(activity, "暂无详情页");
                }
            }
        });
    }

    /**
     * 隐藏软键盘(只适用于Activity，不适用于Fragment)
     */
    public static void hideSoftKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {

    }

    @OnClick({R.id.search_return})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_return:
                finish();
                break;

        }
    }

    private void getdata(String param) {

        tipDialog.show();
        OkHttpUtils
                .get()
                .url(URLS.HOME_SEARCH)
                .addParams("param", param)
                .addParams("pageIndex", "1")
                .addParams("pageSize", "20")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "这是失败的方法1" + e.toString());
                        tipDialog.cancel();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        DynamicBean bean = gson.fromJson(response, DynamicBean.class);
                        newsBeans.addAll(bean.getNews());
                        searchAdapter.notifyDataSetChanged();
                        tipDialog.cancel();
                    }
                });
    }

}
