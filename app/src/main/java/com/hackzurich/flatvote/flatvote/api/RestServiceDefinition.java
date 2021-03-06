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
    Observable<Response<Item>> getEntry(@Path("id") long id, @Query("place") String place);

    @GET("realEstates/{uid}?limit=2")
    Observable<Response<FlatvoteMessageResponse>> getEntries(@Path("uid") String uid, @Query("longitude") String lat, @Query("latitude") String lng, @Query("place") String place, @Query("page") Integer page);
}
