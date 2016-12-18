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

    RunningMode runningMode;

    public static enum RunningMode {
        addressSearch,
        startPointSearch;


        public static RunningMode fromInt(int src) {
            if (src > RunningMode.values().length
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
        handleActionBar(this, runningMode);
    }

    private RunningMode getRuningMode(Intent intent) {
        RunningMode mode = (RunningMode) intent.getSerializableExtra(EXTRA_RUNNING_MODE);
        return mode;
    }


    private void handleActionBar(AppCompatActivity activity, RunningMode mode) {

        LayoutInflater inflater = LayoutInflater.from(activity);
        android.support.v7.app.ActionBar actionBar = activity.getSupportActionBar();
        View view;

        view = inflater.inflate(R.layout.action_bar_search_layout, null);

        actionBar.setCustomView(view, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT));
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle("");

        EditText et = (EditText) view.findViewById(R.id.address_et);

        switch (mode) {
            case addressSearch:
                et.setHint(getString(R.string.default_value_destination_address));
                break;
            case startPointSearch:
                et.setHint(getString(R.string.default_value_origin_address));
                break;
            default: {}
            break;
        }
        callback.setSearchField(et);


    }


    private void proccessIncomingIntent(Intent intent) {
        Location location;
        runningMode = getRuningMode(intent);
        switch (runningMode) {
            case startPointSearch:
            case addressSearch: {
                location = intent.getParcelableExtra(EXTRA_CURRENT_LOCATION);
                callback = new SearchPresenterImpl(this, findViewById(R.id.search_root_view), location);

            }
            break;
        }

    }

    protected void finishAndNotify(AbstractResponseItem item) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_SEARCH_RESULT, item);
        intent.putExtra(EXTRA_RUNNING_MODE, runningMode);
        setResult(RESULT_OK, intent);
        finish();
    }


}
