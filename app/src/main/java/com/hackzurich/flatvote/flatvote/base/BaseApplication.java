package com.hackzurich.flatvote.flatvote.base;

import android.app.Application;

import com.hackzurich.flatvote.flatvote.utils.dagger.component.AppComponent;

/**
 * Created by christof on 17.09.16.
 */
public class BaseApplication extends Application{


    @Override
    public void onCreate() {
        super.onCreate();
        AppComponent.Holder.initialize(this);

    }
}
