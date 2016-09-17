package com.hackzurich.flatvote.flatvote.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by longstone on 17/09/16.
 */
@IgnoreExtraProperties
public class DeviceEntry {
    private String username;
    private String notificationToken;

    @SuppressWarnings("unused")
    public DeviceEntry() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public DeviceEntry(String userId, String token) {
        this.username=userId;
        this.notificationToken = token;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNotificationToken() {
        return notificationToken;
    }

    public void setNotificationToken(String notificationToken) {
        this.notificationToken = notificationToken;
    }


}
