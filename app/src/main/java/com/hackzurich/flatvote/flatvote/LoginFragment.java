package com.hackzurich.flatvote.flatvote;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.fitness.data.Application;
import com.google.firebase.iid.FirebaseInstanceId;
import com.hackzurich.flatvote.flatvote.api.RestService;
import com.hackzurich.flatvote.flatvote.base.BaseApplication;
import com.hackzurich.flatvote.flatvote.utils.dagger.component.AppComponent;
import com.hackzurich.flatvote.flatvote.utils.dagger.module.FirebaseService;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by christof on 17.09.16.
 */
public class LoginFragment extends Fragment {

    @BindView(R.id.btn_login)
    protected AppCompatButton button1;
    @BindView(R.id.input_email)
    protected EditText email;


    @Inject
    RestService restService;
    @Inject
    FirebaseService firebaseService;


    public LoginFragment() {
        super();
        AppComponent.Holder.getAppComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loginfragment, container, false);
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
            // TODO: 17.09.16 do login
            Toast.makeText(LoginFragment.this.getActivity(),"fuuuubar",Toast.LENGTH_LONG).show();
            String loginName = email.getText().toString();
            ((BaseApplication) getActivity().getApplication()).username = loginName;
            UglyGlobalHashMap.getInstance().put(UglyGlobalHashMap.USER_ID,loginName);
            firebaseService.writeNewUser(loginName, FirebaseInstanceId.getInstance().getToken());
            ((MainActivity) getActivity()).replaceFragment(new SelectFragment());
        };
    }
}

