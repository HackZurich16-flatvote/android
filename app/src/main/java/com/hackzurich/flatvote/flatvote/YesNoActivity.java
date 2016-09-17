package com.hackzurich.flatvote.flatvote;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hackzurich.flatvote.flatvote.api.RestService;
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
    RestService restService;

    @BindView(R.id.carouselView)
    CarouselView carouselView;

    @BindView(R.id.title_text)
    TextView title_text;

    @BindView(R.id.description_text)
    TextView description_text;


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

    //    BaseApplication application = (BaseApplication) getApplication();
    //    item = application.getItem();
        
        if (item == null) {
            finish();
        }

        carouselView = (CarouselView) findViewById(R.id.carouselView);
        if (carouselView != null) {
            carouselView.setPageCount(item.getPictures().size());
            carouselView.setImageListener(imageListener);
        }

        title_text.setText(item.getTitle());
        description_text.setText(item.getDescription());

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
        String userName = ((BaseApplication) getApplication()).username;
        restService.getOfferings(userName, s, s1).subscribe(flatvoteMessageResponseResponse -> {
            for (Item item : flatvoteMessageResponseResponse.body().getItems()) {
                Log.d(this.getClass().getSimpleName(), item.getCity());
            }
        }, throwable -> {
            Log.d(this.getClass().getSimpleName(), "onError");
            Log.d(this.getClass().getSimpleName(), "onError", throwable);

        });
    }


}
