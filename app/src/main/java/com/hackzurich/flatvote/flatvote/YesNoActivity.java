package com.hackzurich.flatvote.flatvote;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hackzurich.flatvote.flatvote.api.Service;
import com.hackzurich.flatvote.flatvote.api.model.Item;
import com.hackzurich.flatvote.flatvote.base.BaseApplication;
import com.hackzurich.flatvote.flatvote.utils.dagger.component.AppComponent;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by christof on 17.09.16.
 */

public class YesNoActivity extends Activity{


    @Inject
    Service service;

    @BindView(R.id.carouselView)
    CarouselView carouselView;
    private Item item;

    public YesNoActivity() {
        super();
        AppComponent.Holder.getAppComponent().inject(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yesno);
        ButterKnife.bind(this);

        BaseApplication application = (BaseApplication) getApplication();
        item = application.getItem();
        
        if (item == null) {
            finish();
        }
        
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(item.getPictures().size());
        carouselView.setImageListener(imageListener);

    }


    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            String url = item.getPictures().get(position);
            loadPictureIntoImageView(url, imageView);

        }
    };

    private void loadPictureIntoImageView(String url, ImageView imageView) {
        Glide
                .with(this)
                .load(url)
                .centerCrop()
                .placeholder(R.layout.loading_spinner_view)
                .crossFade()
                .into(imageView);
    }

    private void loadData(String s, String s1) {
//                loadData("47.327060", "8.801356");
        service.register(s, s1).subscribe(flatvoteMessageResponseResponse -> {
            for (Item item : flatvoteMessageResponseResponse.body().getItems()) {
                Log.d(this.getClass().getSimpleName(), item.getCity());


            }
        }, throwable -> {
            Log.d(this.getClass().getSimpleName(), "onError");
            Log.d(this.getClass().getSimpleName(), "onError", throwable);

        });
    }


}
