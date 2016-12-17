package com.ostan.heretestapp.screens.mainscreen;

import android.app.Activity;
import android.location.Location;
import android.view.View;

/**
 * Created by marco on 16/12/2016.
 */

public class MapPresenterImpl implements IMapPresenter, IMapPresenterActivityCallback {

    MapActivity activity;
    IMapView view;

    public MapPresenterImpl(MapActivity activity, View view) {
        this.activity = activity;
        this.view = new MapViewImpl(activity, this, view);
    }

    @Override
    public void onNewAddressReceived() {

    }

    @Override
    public void onNewRouteReceived() {

    }

    @Override
    public void onActivityReady() {
        view.initializeMap();
        onCurrentLocationClicked();
    }

    @Override
    public void onNewLocationRecieved(Location location) {
        view.focusOnPoint(location);
        view.hideStatus();
    }

    @Override
    public void onAddressButtonClicked() {

    }

    @Override
    public void onCurrentLocationClicked() {
        view.showStatus("Finding your current Location");
        activity.determineCurrentLocation();
    }

    @Override
    public void onNearByClicked() {

    }

    @Override
    public void onDirectionsClicked() {

    }

    @Override
    public Activity getActivity() {
        return activity;
    }
}
