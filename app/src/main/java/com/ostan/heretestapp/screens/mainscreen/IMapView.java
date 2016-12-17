package com.ostan.heretestapp.screens.mainscreen;

import android.location.Location;

/**
 * Created by marco on 16/12/2016.
 */

public interface IMapView {
    /**
     * Show status UI element notifying the user that something is happening
     * @param  statusLine - text that user will see
     * */
    void showStatus(String statusLine);

    /**
     * Update status UI element notifying the user that something is happening
     * @param  statusLine - text that user will see
     * */
    void updateStatus(String statusLine);

    /**
     * Hide status UI element notifying the user that something is happening
     * */
    void hideStatus();

    /**
     * Focus Map on provided point
     * */
    //TODO add the obect to the signature and to the remark
    void focusOnPoint(Location location);

    /**
     * Draw provided route on the map
     * */
    void drawRoute();

    /**
     * Show/Hide nearby places search
     * @param  toShow - to hide or to show
     * */
    void showNearbyControll(boolean toShow);

    /**
     * Set the address line that map is focused on
     * @param  addressLine = text to be inserted
     * */
    void setAddressLine(String addressLine);

    /**
     * Show/Hide directions' search trigger
     * @param  toShow - to hide or to show
     * */
    void showDirectionsTrigger(boolean toShow);

    /**
     * Trigger the maop initialization
     * */
    void initializeMap();

}
