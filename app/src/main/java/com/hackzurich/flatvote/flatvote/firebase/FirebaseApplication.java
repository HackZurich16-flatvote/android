package com.hackzurich.flatvote.flatvote.firebase;
import com.firebase.client.Firebase;

/**
 * Created by longstone on 17/09/16.
 */
public class FirebaseApplication extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
