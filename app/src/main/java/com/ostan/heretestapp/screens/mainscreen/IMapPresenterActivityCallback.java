package com.ostan.heretestapp.screens.mainscreen;

import android.location.Location;

/**
 * Created by marco on 16/12/2016.
 */

public interface IMapPresenterActivityCallback {
    /**
     * Called when new address received
     * */
    void onNewAddressReceived();

    /**
     * Called when new route received
     * */
    void onNewRouteReceived();

    /**
     * Notify the presenter that activity is ready
     * */
    void onActivityReady();


    /**
     * Notify the presenter about new coordinates that were received*/

    void onNewLocationRecieved(Location location);
}
