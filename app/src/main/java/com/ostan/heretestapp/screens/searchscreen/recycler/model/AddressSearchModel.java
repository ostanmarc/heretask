package com.ostan.heretestapp.screens.searchscreen.recycler.model;

import android.util.Log;

import com.ostan.heretestapp.pojo.AbstractResponseItem;
import com.ostan.heretestapp.pojo.Item;
import com.ostan.heretestapp.pojo.AddressSearchResponse;
import com.ostan.heretestapp.screens.searchscreen.ISearchCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marco on 18/12/2016.
 */

public class AddressSearchModel extends AbstractBaseSearchModel<AddressSearchResponse> {

    public AddressSearchModel(ISearchCallback presenter) {
        super(presenter);
    }

    @Override
    public void onNext(AddressSearchResponse response) {

        List<AbstractResponseItem> data = new ArrayList<>();
        data.addAll(response.getResults().getItems());
        presenter.onNewResponseArrived(data);
        for (Item iterator: response.getResults().getItems()) {
            Log.i("LOG","ITEM ARRIVED:" + iterator.getTitle());
        }
    }
}
