package com.example.wisdom.partybuilding.base;

public class BasePresenter<V extends IView> implements IPresenter<V> {

    public V mView;


    public BasePresenter(V view) {
        mView = view;
    }

    public IView getView(){
        return mView;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
