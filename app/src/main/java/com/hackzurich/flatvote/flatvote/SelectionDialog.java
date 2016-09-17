package com.hackzurich.flatvote.flatvote;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hackzurich.flatvote.flatvote.api.model.Item;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.List;

/**
 * Created by christof on 17.09.16.
 */

public class SelectionDialog extends android.support.v4.app.DialogFragment {

    private Item item;
    private CarouselView carouselView;

    public SelectionDialog(){
        super();
    }

    // i know what i'm doing! well maybe
    @SuppressLint("ValidFragment")
    public SelectionDialog(Item item) {
        super();
        setItem(item);
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.selection_dialog, container,
                false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        carouselView = (CarouselView) view.findViewById(R.id.carouselView);
        if (carouselView != null) {
            carouselView.setPageCount(item.getPictures().size());
            carouselView.setImageListener(imageListener);
        }
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Glide.with(getActivity())
                    .load(item.getPictures().get(position))
                    .centerCrop()
                    .placeholder(R.mipmap.imgres)
                    .into(imageView);
        }
    };

}
