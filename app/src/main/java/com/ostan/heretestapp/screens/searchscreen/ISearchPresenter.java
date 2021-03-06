package com.ostan.heretestapp.screens.searchscreen;

import com.ostan.heretestapp.pojo.AbstractResponseItem;

/**
 * Created by marco on 17/12/2016.
 */

public interface ISearchPresenter {
    public void onQuerryTextEdited(String currentQuerryString);
    public void onResultClicked(AbstractResponseItem selectedResult);
}
