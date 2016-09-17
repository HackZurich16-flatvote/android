package com.hackzurich.flatvote.flatvote;

import android.util.SparseBooleanArray;

import java.util.HashMap;

/**
 * Created by longstone on 17/09/16.
 */
public class UglyGlobalHashMap {
    private final static HashMap<String,String> instance = new HashMap<>();

    public static HashMap<String,String> getInstance() {
        return instance;
    }

    public final static String USER_ID = "userId";
}
