package com.hackzurich.flatvote.flatvote.api;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by christof on 17.09.16.
 */
public interface ServiceDefinition {


    @GET("unknownPath")
    Observable<Response<Void>> getEntries(@Path("lat") String lat, @Path("lng") String lng, @Path("page") String page);
}
