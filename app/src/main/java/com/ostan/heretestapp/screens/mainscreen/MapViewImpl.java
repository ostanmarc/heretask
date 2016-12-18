package com.ostan.heretestapp.screens.mainscreen;


import android.app.FragmentManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapFragment;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapRoute;
import com.here.android.mpa.routing.RouteManager;
import com.here.android.mpa.routing.RouteOptions;
import com.here.android.mpa.routing.RoutePlan;
import com.here.android.mpa.routing.RouteResult;
import com.ostan.heretestapp.R;
import com.ostan.heretestapp.pojo.LocationWrapper;
import com.ostan.heretestapp.pojo.Route;
import com.ostan.heretestapp.pojo.Waypoint;
import com.ostan.heretestapp.utils.ImagesHandler;

import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//import com.here.android.mpa.mapping.MapObject;

/**
 * Created by marco on 16/12/2016.
 */

public class MapViewImpl implements IMapView {

    private MapFragment mapFragment = null;
    private FragmentManager fragmentManager;
    private IMapPresenter presenter;

    private boolean isAddressTriggerActive = false;

    private final double DEFAULT_ZOOM_LEVEL = 17;

    // map embedded in the map fragment
    private Map map = null;

    @BindView(R.id.status_bar_holder)
    LinearLayout statusHolder;
    @BindView(R.id.status_update_tv)
    TextView statusTv;

    @BindView(R.id.address_tv)
    TextView addressLine;

    @BindView(R.id.addresstv_holder)
    RelativeLayout address_loder;

    @BindView(R.id.navigation_trigger)
    ImageButton navigationTrigger;

    @BindDrawable(R.drawable.ic_current_location)
    Drawable currentLocationIcon;

    @BindDrawable(R.drawable.ic_searched_location)
    Drawable searchedLocationIcon;


    @OnClick({R.id.addresstv_holder, R.id.address_tv})
    public void addressClicked(){
        if(!isAddressTriggerActive) {
            return;
        }
        presenter.onAddressButtonClicked();
    }

    @OnClick(R.id.navigation_trigger)
    public void navigationClicked(){
        presenter.onDirectionsClicked();
    }

    public MapViewImpl(AppCompatActivity activity, IMapPresenter presenter, View view) {
        this.fragmentManager = activity.getFragmentManager();

        ButterKnife.bind(this, activity);
        this.presenter = presenter;
    }

    // Markers references to prevent multiple marker creations
    MapMarker currentLocationMarker;
    MapMarker searchedLocationMarker;

    private RouteManager.Listener routeManagerListener = new RouteManager.Listener() {
        public void onCalculateRouteFinished(RouteManager.Error errorCode,
                                             List<RouteResult> result) {

            if (errorCode == RouteManager.Error.NONE && result.get(0).getRoute() != null) {
                // create a map route object and place it on the map
                MapRoute mapRoute = new MapRoute(result.get(0).getRoute());
                map.addMapObject(mapRoute);

                // Get the bounding box containing the route and zoom in (no animation)
                GeoBoundingBox gbb = result.get(0).getRoute().getBoundingBox();
                map.zoomTo(gbb, Map.Animation.NONE, Map.MOVE_PRESERVE_ORIENTATION);

//                textViewResult.setText(String.format("Route calculated with %d maneuvers.",
//                        result.get(0).getRoute().getManeuvers().size()));
            } else {
//                textViewResult.setText(
//                        String.format("Route calculation failed: %s", errorCode.toString()));
            }
        }

        public void onProgress(int percentage) {
            updateStatus(String.format("... %d percent done ...", percentage));
        }
    };

    @Override
    public void showStatus(String statusLine) {
        statusHolder.setVisibility(View.VISIBLE);
        statusTv.setText(statusLine);
    }

    @Override
    public void updateStatus(String statusLine) {
        statusTv.setText(statusLine);
    }

    @Override
    public void hideStatus() {
        statusHolder.setVisibility(View.GONE);
    }

    @Override
    public void focusOnPoint(GeoCoordinate coordinate) {
        if (coordinate == null) {
            // TODO notify all the interested components
            return;
        }
        map.setCenter(coordinate, Map.Animation.NONE);
        map.setZoomLevel(DEFAULT_ZOOM_LEVEL);
    }

    @Override
    public void addMarkerToTheMap(LocationWrapper location, IMapPresenterActivityCallback.LocationType type) {

        MapMarker object = new MapMarker();
        object.setCoordinate(location.getGeoCoordinate());

        Image image = new Image();
        switch (type){
            case currentLocation: {
                image.setBitmap(ImagesHandler.getBitmapFromVectorDrawable(navigationTrigger.getContext(),R.drawable.ic_current_location));
                if (currentLocationMarker != null) {
                    map.removeMapObject(currentLocationMarker);
                }
                currentLocationMarker = object;

            }
            break;
            case searchedLocation: {
                image.setBitmap(ImagesHandler.getBitmapFromVectorDrawable(navigationTrigger.getContext(),R.drawable.ic_searched_location));
                if (searchedLocationMarker != null) {
                    map.removeMapObject(searchedLocationMarker);
                }
                searchedLocationMarker = object;
                searchedLocationMarker.setDescription(location.getItem().getTitle());


            }
            break;
        }
        object.setIcon(image);
        map.addMapObject(object);
    }

    @Override
    public void drawRoute(Route route) {
        // 2. Initialize RouteManager
        RouteManager routeManager = new RouteManager();



        // 3. Select routing options
        RoutePlan routePlan = new RoutePlan();



        RouteOptions routeOptions = new RouteOptions();

        routeOptions.setTransportMode(RouteOptions.TransportMode.CAR);
        routeOptions.setRouteType(RouteOptions.Type.FASTEST);
        routePlan.setRouteOptions(routeOptions);

        for (Waypoint waypoint : route.getWaypoint()) {
            routePlan.addWaypoint(new GeoCoordinate(waypoint.getMappedPosition().getLatitude(),
                    waypoint.getMappedPosition().getLongitude()));
        }

        // 5. Retrieve Routing information via RouteManagerEventListener
        RouteManager.Error error = routeManager.calculateRoute(routePlan, routeManagerListener);
        if (error != RouteManager.Error.NONE) {
            Toast.makeText(addressLine.getContext(),
                    "Route calculation failed with: " + error.toString(), Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public void showNearbyControll(boolean toShow) {

    }

    @Override
    public void activateSearchControll(boolean shouldActivate) {
        isAddressTriggerActive = shouldActivate;
    }

    @Override
    public void setAddressLine(String addressText) {
        addressLine.setText(addressText);
    }

    @Override
    public void showDirectionsTrigger(boolean toShow) {
        navigationTrigger.setVisibility(toShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void initializeMap() {
        initialize();
    }


    public void initialize() {

        // Search for the map fragment to finish setup by calling init().
        mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.mapfragment);
        mapFragment.init(new OnEngineInitListener() {
            @Override
            public void onEngineInitializationCompleted(OnEngineInitListener.Error error) {
                if (error == OnEngineInitListener.Error.NONE) {
                    // retrieve a reference of the map from the map fragment
                    map = mapFragment.getMap();

                    map.setMapScheme(Map.Scheme.NORMAL_DAY);
                    // Set the map center to the Vancouver region (no animation)
                    map.setCenter(new GeoCoordinate(49.196261, -123.004773, 0.0),
                            Map.Animation.NONE);
                    // Set the zoom level to the average between min and max
                    map.setZoomLevel((map.getMaxZoomLevel() + map.getMinZoomLevel()) / 2);
                } else {
                    Log.e(this.getClass().getSimpleName(), "Cannot initialize MapFragment (" + error + ")");
                }
            }
        });


    }




}
