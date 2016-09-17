package com.hackzurich.flatvote.flatvote.api;

import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by christof on 17.09.16.
 */
public class Service {


    private final ServiceDefinition service;
    private Retrofit retrofit;

    public Service(Retrofit retrofit) {

        this.retrofit = retrofit;
        this.service = retrofit.create(ServiceDefinition.class);
    }


    public Observable<Response<Void>> register(String lat, String lng, String page) {
        return service.getEntries(lat, lng, page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

}
