package com.ostan.heretestapp.screens.searchscreen;

import android.view.View;

import com.ostan.heretestapp.models.AutoSuggestResult;

import java.util.List;

/**
 * Created by marco on 17/12/2016.
 */

public class SearchPresenterImpl implements ISearchPresenter, ISearchActivityCallback{


    SearchActivity activity;
    ISearchView view;

    public SearchPresenterImpl(SearchActivity activity, View view) {
        this.activity = activity;
        this.view = new SearchViewImpl(this, view);

    }

    @Override
    public void onQuerryTextEdited(String currentQuerryString) {

    }

    @Override
    public void onResultClicked(AutoSuggestResult selectedResult) {

    }

    @Override
    public void onResultReceived(List<AutoSuggestResult> result) {

    }
}
