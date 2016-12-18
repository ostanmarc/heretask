package com.ostan.heretestapp.screens.mainscreen;

import com.here.android.mpa.common.GeoCoordinate;
import com.ostan.heretestapp.pojo.LocationWrapper;
import com.ostan.heretestapp.pojo.Route;

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
    void focusOnPoint(GeoCoordinate location);


    /**
     * Focus Map on provided point
     * */
    void addMarkerToTheMap(LocationWrapper locationWrapper, IMapPresenterActivityCallback.LocationType type);

    /**
     * Draw provided route on the map
     * */
    void drawRoute(Route route);

    /**
     * Show/Hide nearby places search
     * @param  toShow - to hide or to show
     * */
    void showNearbyControll(boolean toShow);

    /**
     * Activate\Deactivate address search
     * @param  shouldActivate - to hide or to show
     * */
    void activateSearchControll(boolean shouldActivate);


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
