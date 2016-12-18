package com.ostan.heretestapp.utils;

import android.location.Location;
import android.util.Log;

import com.ostan.heretestapp.pojo.APIResult;
import com.ostan.heretestapp.pojo.AutoSuggestResult;
import com.ostan.heretestapp.pojo.Item;
import com.ostan.heretestapp.pojo.AddressSearchResponse;
import com.ostan.heretestapp.pojo.RoutesSearchResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by marco on 17/12/2016.
 */

public class HereApiConnector {

    public static final String BASE_URL = "https://places.api.here.com";
    public static final String BASE_ROUTES_URL = "https://route.cit.api.here.com/";

    public static enum AddressResultTypes {
        address, place, category, chain;

        public static String getStringFromEnumsList(List<AddressResultTypes> src) {
            String result = src.get(0).toString();

            for (AddressResultTypes resultType : src) {
                if (src.indexOf(resultType) == 0) {
                    continue;
                }
                result += "," + resultType.toString();
            }

            return result;

        }
    }

    public void getAutosuggest(String querry, Location location, List<AddressResultTypes> resultTypes) {


        APIInterface testService = getAPIInterfaceInstance(BASE_URL);

        Call<APIResult<AutoSuggestResult>> call = testService.autoSuggest(querry,
                location.getLatitude() + "," + location.getLongitude(),
                AddressResultTypes.getStringFromEnumsList(resultTypes));


        call.enqueue(new Callback<APIResult<AutoSuggestResult>>() {
            @Override
            public void onResponse(Call<APIResult<AutoSuggestResult>> call, Response<APIResult<AutoSuggestResult>> response) {
                for (AutoSuggestResult suggestion : response.body().results) {
                    Log.i("Log", "Address received: " + suggestion.getTitle());
                }
            }

            @Override
            public void onFailure(Call<APIResult<AutoSuggestResult>> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }

    public void search(String querry, Location location) {
        APIInterface testService = getAPIInterfaceInstance(BASE_URL);

        Call<AddressSearchResponse> call = testService.search(querry,
                location.getLatitude() + "," + location.getLongitude(), 10);

        call.enqueue(new Callback<AddressSearchResponse>() {
            @Override
            public void onResponse(Call<AddressSearchResponse> call, Response<AddressSearchResponse> response) {
                for (Item suggestion : response.body().getResults().getItems()) {
                    Log.i("Log", "Address received: " + suggestion.getTitle());
                }
            }

            @Override
            public void onFailure(Call<AddressSearchResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    public Observable<RoutesSearchResponse> searchRoutes(Location start, Location destination){
        APIInterface testService = getReactiveAPIInterfaceInstance(BASE_ROUTES_URL);

        HashMap<String, String> requestData = new HashMap<>();
        requestData.put("waypoint0", "geo!"+start.getLatitude() + "," + start.getLongitude());
        requestData.put("waypoint1", "geo!"+destination.getLatitude() + "," + destination.getLongitude());
        requestData.put("destination_places", "true");
        requestData.put("mode", "fastest;publicTransport");
        requestData.put("alternatives", "3");
        requestData.put("mode", "fastest;publicTransport");
        requestData.put("routeattributes", "labels");

        return testService.searchRoutes(requestData);
    }



    public Observable<AddressSearchResponse> searchReactive(String querry, Location location) {


        APIInterface testService = getReactiveAPIInterfaceInstance(BASE_URL);

        return testService.searchReactive(querry,
                location.getLatitude() + "," + location.getLongitude(), 10);

    }

    private APIInterface getReactiveAPIInterfaceInstance(String base_url) {

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

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();

        APIInterface protocolInstance = retrofit.create(APIInterface.class);

        return protocolInstance;
    }

    private APIInterface getAPIInterfaceInstance(String baseUrlForRequest) {
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
                .baseUrl(baseUrlForRequest)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIInterface protocolInstance = retrofit.create(APIInterface.class);

        return protocolInstance;
    }


}
