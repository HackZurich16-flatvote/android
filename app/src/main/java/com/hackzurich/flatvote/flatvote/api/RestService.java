package com.hackzurich.flatvote.flatvote.api;

import com.hackzurich.flatvote.flatvote.api.model.FlatvoteMessageResponse;
import com.hackzurich.flatvote.flatvote.api.model.Item;

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

    public Response<Item> getOffering(long id, String place) {
        return service.getEntry(id, place);
    }

    public Observable<Response<FlatvoteMessageResponse>> getOfferings(String uid, String lat, String lng) {
        return service.getEntries(uid, lat, lng).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Response<FlatvoteMessageResponse>> getOfferingsWithDistanceCalculation(String uid, String lat, String lng, String place) {
        return service.getEntriesForPlacename(uid, lat, lng, place).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

}
