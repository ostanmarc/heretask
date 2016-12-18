package com.ostan.heretestapp.screens.mainscreen;

import android.app.Activity;
import android.location.Location;
import android.util.Log;
import android.view.View;

import com.here.android.mpa.common.GeoCoordinate;
import com.ostan.heretestapp.pojo.LocationWrapper;
import com.ostan.heretestapp.pojo.Route;

/**
 * Created by marco on 16/12/2016.
 */

public class MapPresenterImpl implements IMapPresenter, IMapPresenterActivityCallback {

    MapActivity activity;
    IMapView view;
    Location currentLocation;
    Location destinationLocation;

    enum MapScreenState{
        Default, // No current currentLocation found controlls disabled
        Located, // Current currentLocation found
        AddressSelected, // Destination point selected
        RouteSelected// Route for the ride is selected
    }

    MapScreenState state;

    public MapPresenterImpl(MapActivity activity, View view) {
        this.activity = activity;
        this.view = new MapViewImpl(activity, this, view);
        this.state = MapScreenState.Default;

    }


    @Override
    public void onNewRouteReceived(Route route) {
        view.drawRoute(route);
    }

    @Override
    public void onActivityReady() {
        view.initializeMap();
        onCurrentLocationClicked();
    }

    @Override
    public void onNewLocationRecieved(LocationWrapper locationWrapper, LocationType type) {

        determineScreenState(type);
        GeoCoordinate coordinate = locationWrapper.getGeoCoordinate();

        if(type == LocationType.currentLocation) {
            currentLocation = locationWrapper.getLocation();
        } else {
            destinationLocation = locationWrapper.getLocation();
            view.setAddressLine(locationWrapper.getItem().getTitle());
        }

        view.focusOnPoint(coordinate);
        view.hideStatus();
        view.addMarkerToTheMap(locationWrapper, type);


    }

    @Override
    public void onAddressButtonClicked() {

        if(state == MapScreenState.Default) {
            // I want to prevent address search before the current currentLocation received
            // in order to provde more precise search results
            return;
        }
        activity.fireAddressSearch(currentLocation);
    }

    @Override
    public void onCurrentLocationClicked() {
        view.showStatus("Finding your current LocationModel");
        activity.determineCurrentLocation();
    }

    @Override
    public void onNearByClicked() {

    }

    @Override
    public void onDirectionsClicked() {
        activity.fireRouteSearch(currentLocation, destinationLocation);
    }

    @Override
    public Activity getActivity() {
        return activity;
    }

    private void determineScreenState(LocationType type){

        MapScreenState newState = state;
        switch (type){
            case currentLocation:
                // Receiving the new currentLocation when already selected address should not move the screen into state
                // as if no address was selected
                if(state == MapScreenState.AddressSelected) {
                    break;
                }
                newState = MapScreenState.Located;
                break;
            case searchedLocation:

                // Moving from default state to address selected is not allowed
                if(state == MapScreenState.Default) {
                    break;
                }
                newState = MapScreenState.AddressSelected;
                break;
        }
        actAccordingToScreenState(state, newState);
        state = newState;
    }

    private void actAccordingToScreenState(MapScreenState oldState, MapScreenState newState){
        if(newState == oldState) {
            return;
        }
        if (oldState == MapScreenState.Default) {
            // we gonna turn on some controlls
            if(newState == MapScreenState.Located) {
                // TODO TURN ON THE NEARBY AND WHERE TO
                view.showNearbyControll(true);
                view.activateSearchControll(true);

            } else {
                // newState is AddressSelected
                Log.e("Map Screen", "Error in state machine, trying to get from default to address selected state");
            }
        }
        if(oldState == MapScreenState.Located){
            // We have our current address
            if (newState == MapScreenState.AddressSelected) {
                view.showDirectionsTrigger(true);
                view.showNearbyControll(false);
                // TODO TURN OFF THE NEARBY and TURN ON ROUTE
            }

        }
        if (oldState == MapScreenState.AddressSelected) {
            if(newState == MapScreenState.RouteSelected) {
                // TODO add rellevant logics here
            }
        }

        if(oldState == MapScreenState.RouteSelected){
            if(newState == MapScreenState.AddressSelected) {
                // TODO Remove route from Map
            }
        }

    }
}
