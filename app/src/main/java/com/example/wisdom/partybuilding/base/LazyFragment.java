package com.example.wisdom.partybuilding.base;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yangjingsong on 16/8/9.
 */
public abstract class LazyFragment extends Fragment {
    // 标志位，标志Fragment已经初始化完成。
    protected boolean isPrepared = false;
    protected boolean isVisible;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isPrepared = true;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    @Override
    public void onDestroyView() {
        isPrepared = false;
        super.onDestroyView();
    }

    protected abstract void lazyLoad();

    protected void onInvisible() {

    }
}
