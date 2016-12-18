package com.ostan.heretestapp.screens.searchscreen;

import android.location.Location;
import android.view.View;
import android.widget.EditText;

import com.ostan.heretestapp.pojo.AbstractResponseItem;
import com.ostan.heretestapp.pojo.AddressSearchResponse;
import com.ostan.heretestapp.pojo.RoutesSearchResponse;
import com.ostan.heretestapp.screens.searchscreen.recycler.model.AbstractBaseSearchModel;
import com.ostan.heretestapp.screens.searchscreen.recycler.model.AddressSearchModel;
import com.ostan.heretestapp.screens.searchscreen.recycler.model.RoutesSearchModel;
import com.ostan.heretestapp.utils.HereApiConnector;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by marco on 17/12/2016.
 */

public class SearchPresenterImpl implements ISearchPresenter, ISearchCallback {


    SearchActivity activity;
    ISearchView view;
    Location currentLocation;
    Location destinationLocation;
    AbstractBaseSearchModel model;

    public SearchPresenterImpl(SearchActivity activity, View view, Location location) {
        this.activity = activity;
        this.view = new SearchViewImpl(this, view);
        this.model = new AddressSearchModel(this);
        this.currentLocation = location;
        onQuerryTextEdited("berliner");
    }

    public SearchPresenterImpl(SearchActivity activity, View view, Location location, Location destinationLocation) {
        this.activity = activity;
        this.view = new SearchViewImpl(this, view);
        this.model = new RoutesSearchModel(this);
        this.currentLocation = location;
        this.destinationLocation = destinationLocation;
        getOptionalRoutes();
    }


    private void getOptionalRoutes(){
        HereApiConnector connector = new HereApiConnector();
        Observable<RoutesSearchResponse> call = connector.searchRoutes(currentLocation, destinationLocation);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(model);
    }

    @Override
    public void onQuerryTextEdited(String currentQuerryString) {
        

        HereApiConnector connector = new HereApiConnector();
        Observable<AddressSearchResponse> call = connector.searchReactive(currentQuerryString, currentLocation);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(model);
    }

    @Override
    public void onResultClicked(AbstractResponseItem selectedResult) {
        activity.finishAndNotify(selectedResult);
    }


    @Override
    public void onNewResponseArrived(List<AbstractResponseItem> items) {
        for(AbstractResponseItem item: items){
            view.addAddressItem(items);
        }
    }

    @Override
    public void setSearchField(EditText editText) {
        this.view.activateQuerryChangesListening(editText);
    }
}
