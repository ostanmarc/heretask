package com.ostan.heretestapp.screens.mainscreen;


import android.app.FragmentManager;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapFragment;
import com.ostan.heretestapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by marco on 16/12/2016.
 */

public class MapViewImpl implements IMapView {

    private MapFragment mapFragment = null;
    private FragmentManager fragmentManager;
    private IMapPresenter presenter;


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

    @OnClick({R.id.addresstv_holder, R.id.address_tv})
    public void addressClicked(){
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
    public void focusOnPoint(Location location) {
        if (location == null) {
            // TODO notify all the interested components
            return;
        }
        map.setCenter(new GeoCoordinate(location.getLatitude(), location.getLongitude(), location.getAltitude()),
                Map.Animation.LINEAR);
    }

    @Override
    public void drawRoute() {

    }

    @Override
    public void showNearbyControll(boolean toShow) {

    }

    @Override
    public void setAddressLine(String addressLine) {

    }

    @Override
    public void showDirectionsTrigger(boolean toShow) {

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
