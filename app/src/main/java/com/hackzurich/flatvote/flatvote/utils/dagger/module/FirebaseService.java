package com.hackzurich.flatvote.flatvote.utils.dagger.module;

import com.bumptech.glide.load.model.stream.HttpUrlGlideUrlLoader;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hackzurich.flatvote.flatvote.UglyGlobalHolderObject;
import com.hackzurich.flatvote.flatvote.models.DeviceEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        mDatabase.child("users").child(getCleanUserId()).setValue(user);
    }

    public void writeNewUser(String token){
       String userId =  UglyGlobalHolderObject.getInstance().getMap().get(UglyGlobalHolderObject.USER_ID);
        if(userId == null){
            return;
        }
        writeNewUser(userId,token);
    }

    public void downVote(Integer advertisementId) {
        initVote(false,advertisementId);
    }

    public void upVote(Integer advertisementId) {
       initVote(true,advertisementId);
    }

    private void initVote(boolean forYes, int advertisementId){
        DatabaseReference votes = mDatabase.child("votes");
        DatabaseReference vote = votes.push();

        Map<String, Object> values = new HashMap<>();
        values.put("advertisementId", advertisementId);
        String field = forYes ? "yes" : "no";
        List<String> userIds = new ArrayList<>();
        userIds.add(getCleanUserId());
        values.put(field, userIds);

        vote.setValue(values);
    }

    private String getCleanUserId(){
        String userId =  UglyGlobalHolderObject.getInstance().getMap().get(UglyGlobalHolderObject.USER_ID);
        return userId.replace(".",",");
    }
}
