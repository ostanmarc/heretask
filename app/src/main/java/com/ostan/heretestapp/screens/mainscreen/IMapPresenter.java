package com.ostan.heretestapp.screens.mainscreen;

import android.app.Activity;

/**
 * Created by marco on 16/12/2016.
 */

public interface IMapPresenter {
    /**
     * Notify presenter that address button has been clicked
     * */
    void onAddressButtonClicked();

    /**
     * Notify presenter that current currentLocation has been clicked
     * */
    void onCurrentLocationClicked();

    /**
     * Notify presenter that Nearby trigger has been clicked
     * */
    void onNearByClicked();

    /**
     * Notify presenter that Directions trigger has been clicked
     * */

    void onDirectionsClicked();


    /**
     * Activity getter from presenter
     * */
    Activity getActivity();
}
