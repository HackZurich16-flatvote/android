package com.hackzurich.flatvote.flatvote.base;

import android.app.Application;
import android.location.Location;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDexApplication;

import com.hackzurich.flatvote.flatvote.api.model.Item;
import com.hackzurich.flatvote.flatvote.utils.dagger.component.AppComponent;

/**
 * Created by christof on 17.09.16.
 */
public class BaseApplication extends MultiDexApplication {


    private Location location;
    private Item item;


    @Override
    public void onCreate() {
        super.onCreate();
        AppComponent.Holder.initialize(this);
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Nullable
    public Location getLocation() {
        return location;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
