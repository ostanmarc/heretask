package com.ostan.heretestapp.screens.mainscreen;

import com.ostan.heretestapp.pojo.LocationWrapper;


/**
 * Created by marco on 16/12/2016.
 */

public interface IMapPresenterActivityCallback {

    public static enum LocationType{
        currentLocation,
        searchedLocation
    }

    /**
     * Notify the presenter that activity is ready
     * */
    void onActivityReady();


    /**
     * Notify the presenter about new coordinates that were received*/

    void onNewLocationRecieved(LocationWrapper locationWrapper, LocationType type);
}
