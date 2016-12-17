package com.ostan.heretestapp.utils;

import com.ostan.heretestapp.models.APIResult;
import com.ostan.heretestapp.models.AddressObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by marco on 17/12/2016.
 */

public interface APIProtocol {
    static final String PATH = "/places/v1/";
    static final String AUTOSUGGEST = "autosuggest/";

    @GET(PATH+AUTOSUGGEST)
    public Call<APIResult<AddressObject>> autoSuggest(@Query("q") String querry, @Query("at") String location, @Query("result_types") String result_types);

}
