package com.hackzurich.flatvote.flatvote.api;

import com.hackzurich.flatvote.flatvote.api.model.FlatvoteMessageResponse;
import com.hackzurich.flatvote.flatvote.api.model.Item;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by christof on 17.09.16.
 */
public interface RestServiceDefinition {


    @GET("realEstates/{id}")
    Response<Item> getEntry(@Path("id") long id);

    @GET("realEstates/{uid}")
    Observable<Response<FlatvoteMessageResponse>> getEntries(@Path("uid") String uid, @Query("longitude") String lat, @Query("latitude") String lng);


    @GET("realEstates/{uid}")
    Observable<Response<FlatvoteMessageResponse>> getEntriesForPlacename(@Path("uid") String uid, @Query("longitude") String lat, @Query("latitude") String lng, @Query("place") String place);
}
