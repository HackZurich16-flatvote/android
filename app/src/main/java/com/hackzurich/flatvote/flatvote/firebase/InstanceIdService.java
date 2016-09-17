package com.hackzurich.flatvote.flatvote.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.hackzurich.flatvote.flatvote.utils.dagger.component.AppComponent;
import com.hackzurich.flatvote.flatvote.utils.dagger.module.FirebaseService;

import javax.inject.Inject;

/**
 * Created by longstone on 17/09/16.
 */
public class InstanceIdService  extends FirebaseInstanceIdService {


    private static final String TAG = "InstanceIdService";
    @Inject
    protected FirebaseService firebaseService;

    InstanceIdService() {
        AppComponent.Holder.getAppComponent().inject(this);
    }


    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        firebaseService.writeNewUser(refreshedToken);
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.

    }
}
