package com.ostan.heretestapp.pojo;

import android.location.Location;

import com.here.android.mpa.common.GeoCoordinate;

/**
 * Created by marco on 18/12/2016.
 */

public class LocationWrapper {
    Location location;
    Item item;

    public static LocationWrapper getFromLocation(Location location){
        return new LocationWrapper(location);
    }

    public static LocationWrapper getFromItemObject(Item item){
        if(item == null || item.getPosition() == null || item.getPosition().size() != 2) {
            return null;
        }
        return new LocationWrapper(item);
    }

    public GeoCoordinate getGeoCoordinate(){
        GeoCoordinate result = new GeoCoordinate(location.getLatitude(), location.getLongitude());
        return result;
    }

    private LocationWrapper(Item item) {
        this.item = item;
        location = new Location("HEREAPI");
        location.setLatitude(item.getPosition().get(0));
        location.setLongitude(item.getPosition().get(1));

    }

    private LocationWrapper(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public Item getItem() {
        return item;
    }
}
