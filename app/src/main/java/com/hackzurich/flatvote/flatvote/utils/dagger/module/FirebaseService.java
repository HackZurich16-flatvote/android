package com.hackzurich.flatvote.flatvote.utils.dagger.module;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hackzurich.flatvote.flatvote.UglyGlobalHolderObject;
import com.hackzurich.flatvote.flatvote.models.DeviceEntry;

/**
 * Created by longstone on 17/09/16.
 */
public class FirebaseService {
    private DatabaseReference mDatabase;

    public FirebaseService() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void writeNewUser(String userId, String token) {
        DeviceEntry user = new DeviceEntry(userId, token);
        String userIdCleaned = userId.replace(".",",");
        mDatabase.child("users").child(userIdCleaned).setValue(user);
    }

    public void writeNewUser(String token){
       String userId =  UglyGlobalHolderObject.getInstance().getMap().get(UglyGlobalHolderObject.USER_ID);
        if(userId == null){
            return;
        }
        writeNewUser(userId,token);
    }

    public void downVote(Integer advertisementId) {
        // TODO micha
    }

    public void upVote(Integer advertisementId) {
        // TODO micha
    }
}
