package com.ostan.heretestapp.screens.searchscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ostan.heretestapp.R;

public class SearchActivity extends AppCompatActivity {



    public static final String EXTRA_RUNNING_MODE = "runningMode";
    private ISearchActivityCallback callback;


    enum RunningMode{
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

        callback = new SearchPresenterImpl(this, findViewById(R.id.search_root_view));
        handleActionBar(this, determineRuningMode(getIntent()));
    }

    private RunningMode determineRuningMode(Intent intent){
        int runningModeCode = intent.getIntExtra(EXTRA_RUNNING_MODE, -1);
        return RunningMode.fromInt(runningModeCode);
    }


    private void handleActionBar(AppCompatActivity activity, RunningMode mode) {

        LayoutInflater inflater = LayoutInflater.from(activity);
        android.support.v7.app.ActionBar actionBar = activity.getSupportActionBar();
        View view;

        switch (mode){
            case addressSearch:{
                view = inflater.inflate(R.layout.action_bar_search_layout, null);
            }
            break;
            case placeSearch:{
                view = inflater.inflate(R.layout.action_bar_search_layout, null);
            }
            break;
            case routeSearch:{
                view = inflater.inflate(R.layout.action_bar_search_layout, null);
            }
            break;
            default:
            {
                view = inflater.inflate(R.layout.action_bar_search_layout, null);
            }
            break;
        }

        actionBar.setCustomView(view, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT));
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle("");

    }

}
