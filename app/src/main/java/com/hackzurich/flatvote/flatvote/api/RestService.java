package com.hackzurich.flatvote.flatvote.api;

import com.hackzurich.flatvote.flatvote.api.model.FlatvoteMessageResponse;

import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by christof on 17.09.16.
 */
public class RestService {


    private final RestServiceDefinition service;

    public RestService(Retrofit retrofit) {
        this.service = retrofit.create(RestServiceDefinition.class);
    }


    public Observable<Response<FlatvoteMessageResponse>> getOfferings(String lat, String lng) {
        return service.getEntries(lat, lng).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Response<FlatvoteMessageResponse>> getOfferingsWithDistanceCalculation(String lat, String lng, String place, String placeName) {
        return service.getEntriesForPlacename(lat, lng, place, placeName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

}
