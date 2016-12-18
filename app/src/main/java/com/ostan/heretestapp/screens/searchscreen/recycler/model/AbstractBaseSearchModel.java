package com.ostan.heretestapp.screens.searchscreen.recycler.model;

import com.ostan.heretestapp.screens.searchscreen.ISearchCallback;

import rx.Observer;

/**
 * Created by marco on 18/12/2016.
 */

public abstract class AbstractBaseSearchModel<T> implements Observer<T> {

    protected ISearchCallback presenter;

    public AbstractBaseSearchModel(ISearchCallback presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

}
