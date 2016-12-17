package com.ostan.heretestapp.utils;

import android.location.Location;
import android.util.Log;

import com.ostan.heretestapp.models.APIResult;
import com.ostan.heretestapp.models.AutoSuggestResult;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by marco on 17/12/2016.
 */

public class HereApiConnector {

    public static final String BASE_URL = "https://places.api.here.com";

    public static enum AddressResultTypes{
        address, place, category, chain;

        public static String getStringFromEnumsList(List<AddressResultTypes> src){
            String result = src.get(0).toString();

            for(AddressResultTypes resultType: src){
                if(src.indexOf(resultType) == 0){
                    continue;
                }
                result += ","+resultType.toString();
            }

            return result;

        }
    }

    public void testConnectionMethod(String querry, Location location, List<AddressResultTypes> resultTypes) {

        OkHttpClient.Builder httpClient =
                new OkHttpClient.Builder();

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addEncodedQueryParameter("app_id", "dMhiUEQLZQrHCL1huGIP")
                        .addEncodedQueryParameter("app_code", "4ENm1hNTdCT9NNS2J_ijCw")
                        .build();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIProtocol testService = retrofit.create(APIProtocol.class);
        Call<APIResult<AutoSuggestResult>> call = testService.autoSuggest(querry,
                location.getLatitude()+","+location.getLongitude(),
                AddressResultTypes.getStringFromEnumsList(resultTypes));



        call.enqueue(new Callback<APIResult<AutoSuggestResult>>() {
            @Override
            public void onResponse(Call<APIResult<AutoSuggestResult>> call, Response<APIResult<AutoSuggestResult>> response) {
                for(AutoSuggestResult suggestion : response.body().results){
                    Log.i("Log","Address received: "+ suggestion.getTitle());
                }
            }

            @Override
            public void onFailure(Call<APIResult<AutoSuggestResult>> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }


}
