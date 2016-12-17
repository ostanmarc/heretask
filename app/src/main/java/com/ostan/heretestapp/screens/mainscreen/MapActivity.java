package com.ostan.heretestapp.screens.mainscreen;

import android.Manifest;
import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ostan.heretestapp.R;
import com.ostan.heretestapp.utils.HereApiConnector;
import com.ostan.heretestapp.utils.PermissionsHandler;
import com.ostan.heretestapp.utils.PositioningHandler;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapActivity extends AppCompatActivity implements PermissionsHandler.IPermissionDependent{


    private static final String LOG_TAG = MapActivity.class.getSimpleName();

    public static final String[] REQUIRED_SDK_PERMISSIONS = new String[] {
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    PermissionsHandler permissionsHandler;
    IMapPresenterActivityCallback activityCallback;

    @BindView(R.id.root_view)
    RelativeLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        handleActionBar(this);
        ButterKnife.bind(this);
        // Check for the permissions each time we load the app
        permissionsHandler = new PermissionsHandler(this);
        permissionsHandler.checkPermissions();
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(permissionsHandler != null) {
            permissionsHandler.onRequestPermissionsResult(requestCode,permissions,grantResults);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void requiredPermissionsMissing() {
        Toast.makeText(this,
                "In order to function, I need the permissions I asked for.. \n" +
                        "You can restart me and provide me with a permissions", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void permissionsGranted() {
        activityCallback = new MapPresenterImpl(this, rootView);
        activityCallback.onActivityReady();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public String[] getRequiredPermissions() {
        return REQUIRED_SDK_PERMISSIONS;
    }

    public void determineCurrentLocation(){
        new PositioningHandler(this).requestCurrentLocation(new PositioningHandler.LocationRequester() {
            @Override
            public void onLocationReceive(Location loc) {
                activityCallback.onNewLocationRecieved(loc);
                HereApiConnector connector = new HereApiConnector();


                try {
                    Log.i("LOG","Location received:" + loc.getLongitude()+" : "+loc.getLatitude());
                    connector.testConnectionMethod("brande", loc,
                            new ArrayList<HereApiConnector.AddressResultTypes>(Arrays.asList(HereApiConnector.AddressResultTypes.address,
                                    HereApiConnector.AddressResultTypes.place)));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void handleActionBar(AppCompatActivity activity) {

        LayoutInflater inflater = LayoutInflater.from(activity);

        android.support.v7.app.ActionBar actionBar = activity.getSupportActionBar();

        View view = inflater.inflate(R.layout.action_bar_map_layout, null);
        actionBar.setCustomView(view, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT));
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle("");

    }
}
