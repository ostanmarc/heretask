package com.ostan.heretestapp.screens.searchscreen;

import com.ostan.heretestapp.models.AddressObject;

import java.util.List;

/**
 * Created by marco on 17/12/2016.
 */

public interface ISearchActivityCallback {
    public void onResultReceived(List<AddressObject> result);
}