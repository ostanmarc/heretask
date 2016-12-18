package com.ostan.heretestapp.utils;

import com.ostan.heretestapp.pojo.APIResult;
import com.ostan.heretestapp.pojo.AutoSuggestResult;
import com.ostan.heretestapp.pojo.AddressSearchResponse;
import com.ostan.heretestapp.pojo.RoutesSearchResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by marco on 17/12/2016.
 */

public interface APIInterface {

    final String PATH = "/places/v1/";
    final String AUTOSUGGEST = "autosuggest/";

    final String SEARCH = "/discover/search";

    final String ROUTES_PATH = "routing/7.2/";
    final String ROUTES_JSON = "calculateroute.json";


    @GET(PATH+AUTOSUGGEST)
    public Call<APIResult<AutoSuggestResult>> autoSuggest(@Query("q") String querry, @Query("at") String location, @Query("result_types") String result_types);


    @GET(PATH+SEARCH)
    public Call<AddressSearchResponse> search(@Query("q") String querry, @Query("at") String location, @Query("size") int size);

    @GET(PATH+SEARCH)
    public Observable<AddressSearchResponse> searchReactive(@Query("q") String querry, @Query("at") String location, @Query("size") int size);

    @GET(ROUTES_PATH + ROUTES_JSON)
    public Observable<RoutesSearchResponse> searchRoutes(@QueryMap HashMap<String, String> requestParams);

}
