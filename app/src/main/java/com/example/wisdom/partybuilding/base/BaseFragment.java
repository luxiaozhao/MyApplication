package com.example.wisdom.partybuilding.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends IPresenter> extends LazyFragment {
    protected View mRootView;
    protected P mPresenter;

    private boolean isPrepared;
    private boolean isFirst = true;
    private Unbinder mUnBinder = null;

    // 两次点击按钮之间的点击间隔不能少于500毫秒
    protected static final int MIN_CLICK_DELAY_TIME = 500;
    protected static long lastClickTime;

    public boolean isDoubleClick(){
        long curClickTime = System.currentTimeMillis();
        if((curClickTime - lastClickTime) < MIN_CLICK_DELAY_TIME) {
            return true;
        }
        lastClickTime = curClickTime;
        return false;
    }

    @Override
    protected void lazyLoad() {
        if (!isVisible || isPrepared) {
            return;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mPresenter == null) {
            mPresenter = getPresenter();
        }
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), null);
            mUnBinder = ButterKnife.bind(this, mRootView);
            initEventAndData();
            isPrepared = true;
            lazyLoad();
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }


    protected abstract void initEventAndData();

    protected abstract int getLayoutId();

    protected abstract P getPresenter();

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
    }
}
