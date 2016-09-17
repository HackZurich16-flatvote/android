package com.hackzurich.flatvote.flatvote.api;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by christof on 17.09.16.
 */
public interface ServiceDefinition {


    @GET("/realEstates")
    Observable<Response<Void>> getEntries(@Path("longitude") String lat, @Path("latitude") String lng);
}
