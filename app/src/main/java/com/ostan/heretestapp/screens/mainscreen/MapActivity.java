package com.ostan.heretestapp.screens.mainscreen;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
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
import com.ostan.heretestapp.pojo.AbstractResponseItem;
import com.ostan.heretestapp.pojo.Item;
import com.ostan.heretestapp.pojo.LocationWrapper;
import com.ostan.heretestapp.screens.searchscreen.SearchActivity;
import com.ostan.heretestapp.utils.PermissionsHandler;
import com.ostan.heretestapp.utils.PositioningHandler;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ostan.heretestapp.screens.searchscreen.SearchActivity.EXTRA_RUNNING_MODE;

public class MapActivity extends AppCompatActivity implements PermissionsHandler.IPermissionDependent {


    private static final String LOG_TAG = MapActivity.class.getSimpleName();

    public static final int SEARCH_REQUEST_CODE = 1;

    public static final String[] REQUIRED_SDK_PERMISSIONS = new String[]{
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

        if (permissionsHandler != null) {
            permissionsHandler.onRequestPermissionsResult(requestCode, permissions, grantResults);
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

    public void determineCurrentLocation() {
        new PositioningHandler(this).requestCurrentLocation(new PositioningHandler.LocationRequester() {
            @Override
            public void onLocationReceive(Location loc) {
                if (loc != null) {
                    activityCallback.onNewLocationRecieved(LocationWrapper.getFromLocation(loc), IMapPresenterActivityCallback.LocationType.currentLocation);
                    Log.i("LOG", "LocationModel received:" + loc.getLatitude() + " : " + loc.getLongitude());
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            proccessReturnedIntent(data);
        }


    }

    private void handleActionBar(AppCompatActivity activity) {

        LayoutInflater inflater = LayoutInflater.from(activity);

        android.support.v7.app.ActionBar actionBar = activity.getSupportActionBar();

        View view = inflater.inflate(R.layout.action_bar_map_layout, null);
        actionBar.setCustomView(view, new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT));
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle("");

    }

    private Intent generateRouteSearchIntent(Location currentLocation, Location destination) {
        Intent intent = generateIntentCurrentLocation(currentLocation, SearchActivity.RunningMode.startPointSearch);
        intent.putExtra(SearchActivity.EXTRA_DESTINATION_LOCATION, destination);
        return intent;
    }

    private Intent generateIntentCurrentLocation(Location loc, SearchActivity.RunningMode mode) {
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        intent.putExtra(EXTRA_RUNNING_MODE, mode);
        intent.putExtra(SearchActivity.EXTRA_CURRENT_LOCATION, loc);
        return intent;
    }

    public void fireAddressSearch(Location location, SearchActivity.RunningMode runingMode) {
        startActivityForResult(generateIntentCurrentLocation(location, runingMode), SEARCH_REQUEST_CODE);
    }

    private void proccessReturnedIntent(Intent returnedIntent) {
        if (returnedIntent == null) {
            return;
        }

        AbstractResponseItem abstractItem = (AbstractResponseItem) returnedIntent.getSerializableExtra(SearchActivity.EXTRA_SEARCH_RESULT);

        if (abstractItem instanceof Item) {
            Item item = (Item) abstractItem;
            if (item == null) {
                return;
            }

            SearchActivity.RunningMode mode = (SearchActivity.RunningMode) returnedIntent.getSerializableExtra(EXTRA_RUNNING_MODE);
            IMapPresenterActivityCallback.LocationType type =
                    (mode == SearchActivity.RunningMode.startPointSearch) ?
                            IMapPresenterActivityCallback.LocationType.currentLocation
                            : IMapPresenterActivityCallback.LocationType.searchedLocation;


            activityCallback.onNewLocationRecieved(LocationWrapper.getFromItemObject(item), type);
        }

    }
}
