package com.ostan.heretestapp.screens.searchscreen;

import android.widget.EditText;

import com.ostan.heretestapp.pojo.AbstractResponseItem;

import java.util.List;

/**
 * Created by marco on 17/12/2016.
 */

public interface ISearchCallback {
    public void onNewResponseArrived(List<AbstractResponseItem> items);
    public void setSearchField(EditText editText);
}
