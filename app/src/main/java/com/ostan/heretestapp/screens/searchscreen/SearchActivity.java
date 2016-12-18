package com.ostan.heretestapp.screens.searchscreen;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ostan.heretestapp.R;
import com.ostan.heretestapp.pojo.AbstractResponseItem;

import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {



    public static final String EXTRA_RUNNING_MODE = "runningMode";
    public static final String EXTRA_CURRENT_LOCATION = "currentLocation";
    public static final String EXTRA_DESTINATION_LOCATION = "destinationLocation";

    public static final String EXTRA_SEARCH_RESULT = "item";


    private ISearchCallback callback;

    private int resultCode;

    public static enum RunningMode{
        addressSearch,
        placeSearch,
        routeSearch;


        public static RunningMode fromInt(int src) {
            if(src >  RunningMode.values().length
                    || src <= 0) {
                return addressSearch;
            }
            return RunningMode.values()[src];
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);


        proccessIncomingIntent(getIntent());
        handleActionBar(this, determineRuningMode(getIntent()));
    }

    private RunningMode determineRuningMode(Intent intent){
       RunningMode mode = (RunningMode) intent.getSerializableExtra(EXTRA_RUNNING_MODE);
        return mode;
    }


    private void handleActionBar(AppCompatActivity activity, RunningMode mode) {

        LayoutInflater inflater = LayoutInflater.from(activity);
        android.support.v7.app.ActionBar actionBar = activity.getSupportActionBar();
        View view;


        switch (mode){
            case addressSearch:
            case placeSearch:
            default:
            {
                view = inflater.inflate(R.layout.action_bar_search_layout, null);
                actionBar.setCustomView(view, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT));
                actionBar.setDisplayShowCustomEnabled(true);
                actionBar.setTitle("");
                EditText et = (EditText) view.findViewById(R.id.address_et);
                callback.setSearchField(et);
            }
            break;
            case routeSearch:{
                actionBar.setTitle("Directions: ");
            }
            break;
        }






    }


    private void proccessIncomingIntent(Intent intent){
        Location location;
        switch (determineRuningMode(intent)){
            case addressSearch:{
                location = intent.getParcelableExtra(EXTRA_CURRENT_LOCATION);
                callback = new SearchPresenterImpl(this, findViewById(R.id.search_root_view), location);

            }
            break;
            case routeSearch:{
                location = intent.getParcelableExtra(EXTRA_CURRENT_LOCATION);
                Location destination = intent.getParcelableExtra(EXTRA_DESTINATION_LOCATION);
                callback = new SearchPresenterImpl(this, findViewById(R.id.search_root_view), location, destination);

            }
            break;
            case placeSearch: {

            }
            break;



        }

    }

    protected void finishAndNotify(AbstractResponseItem item){
        Intent intent = new Intent();
        intent.putExtra(EXTRA_SEARCH_RESULT, item);
        setResult(RESULT_OK, intent);
        finish();
    }




}
