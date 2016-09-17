package com.hackzurich.flatvote.flatvote;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.hackzurich.flatvote.flatvote.api.RestService;
import com.hackzurich.flatvote.flatvote.api.model.Item;
import com.hackzurich.flatvote.flatvote.base.BaseApplication;
import com.hackzurich.flatvote.flatvote.utils.dagger.component.AppComponent;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by christof on 17.09.16.
 */
public class SelectFragment extends Fragment {


    @BindView(R.id.btn_search)
    AppCompatButton button1;

    @BindView(R.id.input_location1)
    EditText input_location1;

    @BindView(R.id.input_location2)
    EditText input_location2;

    @BindView(R.id.input_location3)
    EditText input_location3;

    @Inject
    RestService restService;


    public SelectFragment() {
        AppComponent.Holder.getAppComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.selectfragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button1.setOnClickListener(createClickListener());
    }

    private View.OnClickListener createClickListener() {
        return v -> {
            // TODO: 17.09.16 do search
            saveNamesFromInput();
            BaseApplication application = (BaseApplication) getActivity().getApplication();
            Location location = application.getLocation();
            String lat = String.valueOf(location.getLatitude());
//            String lat = String.valueOf(Constants.GPS_LAT_ZURICH);
            String lng = String.valueOf(location.getLongitude());
//            String lng = String.valueOf(Constants.GPS_LNG_ZURICH);
            String place = input_location1.getText().toString();
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.showWaitingScreen();
            String userName = ((BaseApplication) getActivity().getApplication()).username;
            restService.getOfferingsWithDistanceCalculation(userName, lat, lng, place).subscribe(
                    flatvoteMessageResponseResponse -> {
                        mainActivity.dismissWaitingScreen();

                        for (Item item : flatvoteMessageResponseResponse.body().getItems()) {
                            Log.d(this.getClass().getSimpleName(), item.getCity());
                        }

                        if (flatvoteMessageResponseResponse.body().getItems().size() > 0) {
                            showDialogForItem(flatvoteMessageResponseResponse.body().getItems());
                        }

                        // TODO: 17.09.16 work with those elements
                    }, throwable -> {
                        mainActivity.dismissWaitingScreen();
                        Log.d(this.getClass().getSimpleName(), "onError", throwable);
                        Toast.makeText(getActivity(), "An Error occurred - I'm so sorry", Toast.LENGTH_SHORT).show();

                    });
        };
    }

    private void saveNamesFromInput() {
        SharedPreferences pref = getActivity().getSharedPreferences(Constants.KEY_SHAREDPREFERENCES, Context.MODE_PRIVATE);
        pref.edit().putString(Constants.KEY_USERPREF, input_location1.getText().toString()).commit();
    }

    private void showDialogForItem(List<Item> item) {
        UglyGlobalHolderObject.getInstance().addItems(item);
        Intent intent = new Intent(getActivity(),YesNoActivity.class);
        startActivity(intent);
      //  SelectionDialog dialog = new SelectionDialog(item);
      //  dialog.show(getActivity().getSupportFragmentManager(), "selectiondialog");
    }
}

