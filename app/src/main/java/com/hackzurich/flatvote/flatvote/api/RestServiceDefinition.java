package com.hackzurich.flatvote.flatvote.api;

import com.hackzurich.flatvote.flatvote.api.model.FlatvoteMessageResponse;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by christof on 17.09.16.
 */
public interface RestServiceDefinition {


    @GET("realEstates/")
    Observable<Response<FlatvoteMessageResponse>> getEntries(@Query("longitude") String lat, @Query("latitude") String lng);


    @GET("realEstates/")
    Observable<Response<FlatvoteMessageResponse>> getEntriesForPlacename(@Query("longitude") String lat, @Query("latitude") String lng, @Query("place") String place, @Query("placeName") String placeName);
}
