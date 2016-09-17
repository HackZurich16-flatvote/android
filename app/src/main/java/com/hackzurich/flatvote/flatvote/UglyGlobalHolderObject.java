package com.hackzurich.flatvote.flatvote;

import com.hackzurich.flatvote.flatvote.api.model.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by longstone on 17/09/16.
 */
public class UglyGlobalHolderObject {
    private final static HashMap<String,String> map = new HashMap<>();
    private final static Queue<Item> mItems = new LinkedBlockingDeque<>();
    private final static UglyGlobalHolderObject INSTANCE = new UglyGlobalHolderObject();


private UglyGlobalHolderObject(){
    // whop whop its aaaaaa singleton!!!
}
    public static UglyGlobalHolderObject getInstance() {
        return INSTANCE;
    }

    public HashMap<String,String> getMap(){
        return map;
    }

    public void addItems(List<Item> items){
        for (Item item : items) {
            mItems.add(item);
        }
    }

    public Item getNextItem(){
        return mItems.poll();
    }

    public int countItems(){
        return mItems.size();
    }

    public final static String USER_ID = "userId";
}
