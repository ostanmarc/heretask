package com.ostan.heretestapp.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by marco on 16/12/2016.
 */

public class PositioningHandler implements PermissionsHandler.IPermissionDependent {

    Activity activity;
    LocationRequester requester;

    public PositioningHandler(Activity activity) {
        this.activity = activity;
    }

    public void requestCurrentLocation(LocationRequester requester) {
        PermissionsHandler handler = new PermissionsHandler(this);
        this.requester = requester;
        handler.checkPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION});
    }

    @Override
    public void requiredPermissionsMissing() {
        requester.onLocationReceive(null);
        return;
    }

    @Override
    public void permissionsGranted() {
        try {
            LocationManager locationManager = (LocationManager)
                    activity.getSystemService(Context.LOCATION_SERVICE);
            LocationChangesListener listener = new LocationChangesListener(locationManager, requester);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 1, listener);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Activity getActivity() {
        return activity;
    }


    @Override
    public String[] getRequiredPermissions() {
        return new String[0];
    }


    private class LocationChangesListener implements LocationListener {

        LocationManager manager;
        LocationRequester requester;

        public LocationChangesListener(LocationManager manager, LocationRequester requester) {
            this.manager = manager;
            this.requester = requester;
        }

        @Override
        public void onLocationChanged(Location loc) {

            try {
                manager.removeUpdates(this);
            } catch (SecurityException e) {
                e.printStackTrace();
            }

            String longitude = "Longitude: " + loc.getLongitude();
            String latitude = "Latitude: " + loc.getLatitude();
            requester.onLocationReceive(loc);

        }

        @Override
        public void onProviderDisabled(String provider) {
            requester.onLocationReceive(null);
        }

        @Override
        public void onProviderEnabled(String provider) {
            requester.onLocationReceive(null);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            requester.onLocationReceive(null);
        }
    }


    public interface LocationRequester {
        void onLocationReceive(Location loc);
    }

}
