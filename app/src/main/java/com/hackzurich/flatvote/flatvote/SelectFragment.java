package com.hackzurich.flatvote.flatvote;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackzurich.flatvote.flatvote.api.RestService;
import com.hackzurich.flatvote.flatvote.api.model.Item;
import com.hackzurich.flatvote.flatvote.base.BaseApplication;
import com.hackzurich.flatvote.flatvote.utils.dagger.component.AppComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by christof on 17.09.16.
 */
public class SelectFragment extends Fragment {



    @BindView(R.id.btn_search)
    AppCompatButton button1;

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
            BaseApplication application = (BaseApplication) getActivity().getApplication();
            Location location = application.getLocation();
            restService.getOfferings(String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude())).subscribe(flatvoteMessageResponseResponse -> {
                for (Item item : flatvoteMessageResponseResponse.body().getItems()) {
                    Log.d(this.getClass().getSimpleName(), item.getCity());
                }

                if (flatvoteMessageResponseResponse.body().getItems().size() > 0) {
                    showDialogForItem(flatvoteMessageResponseResponse.body().getItems().get(0));
                }
            }, throwable -> {
                Log.d(this.getClass().getSimpleName(), "onError");
                Log.d(this.getClass().getSimpleName(), "onError", throwable);

            });
        };
    }

    private void showDialogForItem(Item item) {
        SelectionDialog dialog = new SelectionDialog();
        dialog.setItem(item);
        dialog.show(getActivity().getSupportFragmentManager(), "selectiondialog");
    }
}

