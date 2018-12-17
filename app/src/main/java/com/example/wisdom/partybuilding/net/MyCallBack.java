package com.example.wisdom.partybuilding.net;


public interface MyCallBack<T> {
    void onSuccess(T t);
    void onFaile(String msg);
}
