package com.ostan.heretestapp.screens.searchscreen;

import android.view.View;

import com.ostan.heretestapp.models.AutoSuggestResult;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by marco on 17/12/2016.
 */

public class SearchViewImpl implements ISearchView {

    private ISearchPresenter presenter;


    public SearchViewImpl(ISearchPresenter presenter, View rootView) {
        this.presenter = presenter;
        ButterKnife.bind(this, rootView);
    }

    @Override
    public void updateResults(List<AutoSuggestResult> results) {

    }
}
