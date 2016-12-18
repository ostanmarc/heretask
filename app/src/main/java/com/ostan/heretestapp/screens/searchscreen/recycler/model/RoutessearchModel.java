package com.ostan.heretestapp.screens.searchscreen.recycler.model;

import com.ostan.heretestapp.pojo.AbstractResponseItem;
import com.ostan.heretestapp.pojo.RoutesSearchResponse;
import com.ostan.heretestapp.screens.searchscreen.ISearchCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marco on 18/12/2016.
 */

public class RoutesSearchModel extends AbstractBaseSearchModel<RoutesSearchResponse> {
    public RoutesSearchModel(ISearchCallback presenter) {
        super(presenter);
    }

    @Override
    public void onNext(RoutesSearchResponse routesSearchResponse) {
        List<AbstractResponseItem> data = new ArrayList<>();
        data.addAll(routesSearchResponse.getResponse().getRoute());
        presenter.onNewResponseArrived(data);
    }
}
