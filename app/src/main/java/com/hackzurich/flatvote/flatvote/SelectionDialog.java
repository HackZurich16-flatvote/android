package com.hackzurich.flatvote.flatvote;

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

import com.hackzurich.flatvote.flatvote.api.model.Item;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

/**
 * Created by christof on 17.09.16.
 */

public class SelectionDialog extends DialogFragment {

    private Item item;
    private CarouselView carouselView;

    public void setItem(Item item) {
        this.item = item;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.selection_dialog, container,
                false);
        getDialog().setTitle("DialogFragment Tutorial");
        // Do something else
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        carouselView = (CarouselView) view.findViewById(R.id.carouselView);
        carouselView.setPageCount(item.getPictures().size());
        carouselView.setImageListener(imageListener);
    }



    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {

            String url = item.getPictures().get(position);
            Log.d(this.getClass().getSimpleName(), url);
//            Picasso.with(getActivity()).load(url).into(imageView);
        }
    };

    public void show(FragmentManager supportFragmentManager, String selectiondialog) {
        show(supportFragmentManager, selectiondialog);
    }
}
