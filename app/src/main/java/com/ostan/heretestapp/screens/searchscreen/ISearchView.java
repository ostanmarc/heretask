package com.ostan.heretestapp.screens.searchscreen;

import android.widget.EditText;

import com.ostan.heretestapp.pojo.AbstractResponseItem;

import java.util.List;

/**
 * Created by marco on 17/12/2016.
 */

public interface ISearchView {
    public void addAddressItem(List<AbstractResponseItem> item);
    public void activateQuerryChangesListening(EditText et);
}
