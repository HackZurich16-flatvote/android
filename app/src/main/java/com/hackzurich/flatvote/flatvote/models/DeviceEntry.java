package com.hackzurich.flatvote.flatvote.models;

/**
 * Created by longstone on 17/09/16.
 */
public class DeviceEntry {
    private String username;
    private String id;

    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private DeviceEntry() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
