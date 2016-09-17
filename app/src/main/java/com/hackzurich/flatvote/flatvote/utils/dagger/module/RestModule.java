package com.hackzurich.flatvote.flatvote.utils.dagger.module;

import android.support.annotation.NonNull;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hackzurich.flatvote.flatvote.BuildConfig;
import com.hackzurich.flatvote.flatvote.api.RestService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by christof on 17.09.16.
 */
@Module
public class RestModule {


    @Provides
    @Singleton
    public RestService provideRestService(Retrofit retrofit) {
        return new RestService(retrofit);
    }

    @Provides
    @Singleton
    public FirebaseService provideFirebaseService() {
        return new FirebaseService();
    }


    @Provides
    @Singleton
    @NonNull
    public Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl("https://peaceful-island-82409.herokuapp.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    @NonNull
    public Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    @NonNull
    public OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return new OkHttpClient.Builder().addInterceptor(interceptor).addNetworkInterceptor(new StethoInterceptor()).build();
    }
}
