package com.hackzurich.flatvote.flatvote.utils.dagger.module;

import android.util.Log;

import com.bumptech.glide.load.model.stream.HttpUrlGlideUrlLoader;
import com.facebook.stetho.inspector.protocol.module.Database;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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

    public void downVote(Integer advertisementId, String voteKey) {
        if (voteKey == null) {
            initVote(false, advertisementId);
        } else {
            updateVote(false, voteKey);
        }
    }

    public void upVote(Integer advertisementId, String voteKey) {
        if (voteKey == null) {
            initVote(true, advertisementId);
        } else {
            updateVote(true, voteKey);
        }
    }

    private DatabaseReference updateVote(boolean yes, String voteKey) {
        DatabaseReference vote = mDatabase.child("votes").child(voteKey);

        if (yes) {
            vote.child("yes").push().setValue(getCleanUserId());
        } else {
            vote.child("no").push().setValue(getCleanUserId());
        }

        return mDatabase.child("votes");
    }

    private void initVote(boolean forYes, int advertisementId){
        DatabaseReference votes = mDatabase.child("votes");
        DatabaseReference vote = votes.push();

        Map<String, Object> values = new HashMap<>();
        values.put("advertisementId", advertisementId);

        mDatabase.child("friends").child(getCleanUserId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> yesUserIds = new ArrayList<>();
                List<String> noUserIds = new ArrayList<>();
                if (forYes) {
                    yesUserIds.add(getCleanUserId());
                } else {
                    noUserIds.add(getCleanUserId());
                }

                List<String> userIds = new ArrayList<>();
                userIds.add(getCleanUserId());
                values.put("yes", yesUserIds);
                values.put("no", noUserIds);
                values.put("votesRequired", dataSnapshot.getChildrenCount() + 1);

                vote.setValue(values);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("DB_ERROR",  databaseError.getDetails());
            }
        });
    }

    private String getCleanUserId(){
        String userId =  UglyGlobalHolderObject.getInstance().getMap().get(UglyGlobalHolderObject.USER_ID);
        return userId.replace(".",",");
    }
}
