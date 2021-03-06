package com.hackzurich.flatvote.flatvote;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hackzurich.flatvote.flatvote.api.RestService;
import com.hackzurich.flatvote.flatvote.api.model.Item;
import com.hackzurich.flatvote.flatvote.base.BaseApplication;
import com.hackzurich.flatvote.flatvote.utils.dagger.component.AppComponent;
import com.hackzurich.flatvote.flatvote.utils.dagger.module.FirebaseService;
import com.synnapps.carouselview.CarouselView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by christof on 17.09.16.
 */

public class YesNoActivity extends Activity {

    @Inject
    RestService restService;

    @Inject
    FirebaseService firebaseService;

    @BindView(R.id.carouselView)
    CarouselView carouselView;

    @BindView(R.id.title_text)
    TextView title_text;

    @BindView(R.id.price_text)
    TextView price_text;

    @BindView(R.id.roomnumber_text)
    TextView roomnumber_text;

    @BindView(R.id.travelTimeWork)
    TextView duration;

    @BindView(R.id.description_text)
    TextView description_text;

    @BindView(R.id.green_circle)
    View like;

    @BindView(R.id.red_circle)
    View dislike;


    private Item item;
    private Integer itemNumber = 0;

    public YesNoActivity() {
        super();
        item = UglyGlobalHolderObject.getInstance().getNextItem();
        AppComponent.Holder.getAppComponent().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yesno);
        ButterKnife.bind(this);


        if (item != null) {
            initView();
        }
    }

    private void initView() {
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        if (carouselView != null) {
            carouselView.setPageCount(item.getPictures().size());
            carouselView.setImageListener((position, imageView) ->
                    Glide.with(getApplication())
                            .load(item.getPictures().get(position))
                            .centerCrop()
                            .placeholder(R.mipmap.imgres)
                            .into(imageView));
        }

        title_text.setText(item.getTitle());
        price_text.setText("Preis: " + String.valueOf(item.getSellingPrice()) + " CHF");
        if (item.getNumberRooms() != null && item.getNumberRooms() <= 0.5) {
            roomnumber_text.setVisibility(View.GONE);
        } else {
            roomnumber_text.setText("Anzahl Räume: " + String.valueOf(item.getNumberRooms()));
            roomnumber_text.setVisibility(View.VISIBLE);
        }


        description_text.setText(item.getDescription());
        if (item.getTravelTimes().size() > 0) {
            duration.setText("\nReisezeit Ø " + item.getTravelTimes().get(0) + " Minuten");
        }

        Bundle extras = getIntent().getExtras();
        String voteKey = extras == null ? null : (String) extras.get(Constants.KEY_VOTE_ID);
        if (extras != null && extras.get(Constants.ITEM_NUMBER) != null) {
            this.itemNumber = (Integer) extras.get(Constants.ITEM_NUMBER);
        } else {
            this.itemNumber = 0;
        }

        like.setOnClickListener(v -> {
            firebaseService.upVote(item.getAdvertisementId(), voteKey);
            reload();
        });
        dislike.setOnClickListener(v -> {
            firebaseService.downVote(item.getAdvertisementId(), voteKey);
            reload();
        });
        //   initUIEffects();
    }

    private void reload() {
        // TODO: 18/09/16 bui load n more elements

        String userName = ((BaseApplication) getApplication()).username;
        SharedPreferences pref = this.getSharedPreferences(Constants.KEY_SHAREDPREFERENCES, MODE_PRIVATE);
        String preferredLocation = pref.getString(Constants.KEY_USERPREF, "Zuerich");

        if (this.itemNumber % 2 == 0) {
            restService.getOfferingsWithDistanceCalculation(userName, String.valueOf(Constants.GPS_LAT_ZURICH), String.valueOf(Constants.GPS_LNG_ZURICH), preferredLocation, this.itemNumber / 2 + 1).subscribe(flatvoteMessageResponseResponse -> {
                UglyGlobalHolderObject.getInstance().addItems(flatvoteMessageResponseResponse.body().getItems());
            }, throwable -> {
                Log.d("yesnoActivity", "onError", throwable);
            });
        }

        Intent intent = new Intent(this, YesNoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(Constants.ITEM_NUMBER, this.itemNumber + 1);
        startActivity(intent);
    }

    private void initUIEffects() {
        like.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    ImageView view = (ImageView) v;
                    //overlay is black with transparency of 0x77 (119)
                    view.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                    view.invalidate();
                    break;
                }
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL: {

                    ImageView view = (ImageView) v;
                    //clear the overlay
                    view.getDrawable().clearColorFilter();
                    view.invalidate();
                    break;
                }
            }

            return true;
        });


        dislike.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    ImageView view = (ImageView) v;
                    //overlay is black with transparency of 0x77 (119)
                    view.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                    view.invalidate();
                    break;
                }
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL: {
                    ImageView view = (ImageView) v;
                    //clear the overlay
                    view.getDrawable().clearColorFilter();
                    view.invalidate();
                    break;
                }
            }

            return true;
        });
    }

    private void loadData(String s, String s1) {
//                loadData("47.327060", "8.801356");
//        String userName = ((BaseApplication) getApplication()).username;
//        restService.getOfferings(userName, s, s1).subscribe(flatvoteMessageResponseResponse -> {
//            for (Item item : flatvoteMessageResponseResponse.body().getItems()) {
//                Log.d(this.getClass().getSimpleName(), item.getCity());
//            }
//        }, throwable -> {
//            Log.d(this.getClass().getSimpleName(), "onError");
//            Log.d(this.getClass().getSimpleName(), "onError", throwable);
//
//        });
    }


}
