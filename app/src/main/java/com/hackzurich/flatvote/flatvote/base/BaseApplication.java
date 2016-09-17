package com.hackzurich.flatvote.flatvote.base;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.hackzurich.flatvote.flatvote.utils.dagger.component.AppComponent;

/**
 * Created by christof on 17.09.16.
 */
public class BaseApplication extends MultiDexApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        AppComponent.Holder.initialize(this);
    }
}
