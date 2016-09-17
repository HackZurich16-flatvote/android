package com.hackzurich.flatvote.flatvote.utils.dagger.module;

import com.bumptech.glide.load.model.stream.HttpUrlGlideUrlLoader;
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
        DatabaseReference ad = vote.child("advertisementId");
        ad.setValue(Integer.toString(advertisementId));
        DatabaseReference no = vote.child("no");
        DatabaseReference yes = vote.child("yes");
        if(forYes){
            addUserIdToVote(yes);
        }else{
            addUserIdToVote(no);
        }
    }

    private void addUserIdToVote(DatabaseReference ref) {
        DatabaseReference push = ref.push();
        push.setValue(getCleanUserId());
    }

    private String getCleanUserId(){
        String userId =  UglyGlobalHolderObject.getInstance().getMap().get(UglyGlobalHolderObject.USER_ID);
        return userId.replace(".",",");
    }
}
