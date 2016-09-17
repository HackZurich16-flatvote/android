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
import com.hackzurich.flatvote.flatvote.api.model.FlatvoteMessageResponse;
import com.hackzurich.flatvote.flatvote.api.model.Item;
import com.hackzurich.flatvote.flatvote.base.BaseApplication;
import com.hackzurich.flatvote.flatvote.utils.dagger.component.AppComponent;
import com.hackzurich.flatvote.flatvote.utils.dagger.module.FirebaseService;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;
import rx.functions.Action1;

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

    @BindView(R.id.description_text)
    TextView description_text;

    @BindView(R.id.green_circle)
    View like;

    @BindView(R.id.red_circle)
    View dislike;


    private Item item;

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

        Bundle extras = getIntent().getExtras();
        String itemid = null;
        if (extras != null) {
            Log.i("dd", "Extra:" + extras.getString(Constants.KEY_ADVERTISMENT));
            itemid = extras.getString(Constants.KEY_ADVERTISMENT);
        }


        if (itemid != null && itemid.length() > 0) {
            fetchItemFromServer(itemid);
        } else {
            if (item != null) {
                initView();
            }
        }
    }

    private void fetchItemFromServer(String itemid) {
        SharedPreferences pref = this.getSharedPreferences(Constants.KEY_SHAREDPREFERENCES, MODE_PRIVATE);
        String selectedPlace = pref.getString(Constants.KEY_USERPREF, "Zuerich");
        restService.getOffering(Long.valueOf(itemid), selectedPlace).subscribe(itemResponse -> {
                    item = itemResponse.body();
                    initView();
                }, throwable -> {
                    // TODO: 18.09.16 handle failed call
                }
        );
    }

    private void initView() {
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        if (carouselView != null) {
            carouselView.setPageCount(item.getPictures().size());
            carouselView.setImageListener(imageListener);
        }

        title_text.setText(item.getTitle());
        description_text.setText(item.getDescription());

        like.setOnClickListener(v -> {
            firebaseService.downVote(item.getAdvertisementId());
            reload();
        });
        dislike.setOnClickListener(v -> {
            firebaseService.upVote(item.getAdvertisementId());
            reload();
        });
        initUIEffects();
    }

    private void reload() {
        // TODO: 18/09/16 bui load n more elements

        String userName = ((BaseApplication) getApplication()).username;
        SharedPreferences pref = this.getSharedPreferences(Constants.KEY_SHAREDPREFERENCES, MODE_PRIVATE);
        String preferredLocatipon = pref.getString(Constants.KEY_USERPREF, "Zuerich");


        restService.getOfferingsWithDistanceCalculation(userName, String.valueOf(Constants.GPS_LAT_ZURICH), String.valueOf(Constants.GPS_LNG_ZURICH), preferredLocatipon).subscribe(new Action1<Response<FlatvoteMessageResponse>>() {
            @Override
            public void call(Response<FlatvoteMessageResponse> flatvoteMessageResponseResponse) {
                UglyGlobalHolderObject.getInstance().addItems(flatvoteMessageResponseResponse.body().getItems());
            }
        });


        Intent intent = new Intent(this, YesNoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
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

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Glide.with(getApplication())
                    .load(item.getPictures().get(position))
                    .centerCrop()
                    .placeholder(R.mipmap.imgres)
                    .into(imageView);
        }
    };

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
