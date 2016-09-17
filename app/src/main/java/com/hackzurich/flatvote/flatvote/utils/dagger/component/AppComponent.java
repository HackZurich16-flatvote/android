package com.hackzurich.flatvote.flatvote.utils.dagger.component;

import com.hackzurich.flatvote.flatvote.LoginFragment;
import com.hackzurich.flatvote.flatvote.SelectFragment;
import com.hackzurich.flatvote.flatvote.YesNoActivity;
import com.hackzurich.flatvote.flatvote.base.BaseApplication;
import com.hackzurich.flatvote.flatvote.utils.dagger.module.RestModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by christof on 17.09.16.
 */
@Singleton
@Component(modules = {
        RestModule.class
})
public interface AppComponent {


    void inject(LoginFragment loginFragment);

    void inject(SelectFragment selectFragment);

    void inject(YesNoActivity yesNoActivity);

    enum Holder {

        ;

        private static AppComponent appComponent;

        public static AppComponent getAppComponent() {
            return appComponent;
        }


        private static AppComponent createAppComponent(BaseApplication application) {

            return DaggerAppComponent
                    .builder()
                    .restModule(new RestModule())
                    .build();

        }

        public static void initialize(BaseApplication baseApplication) {
            if (appComponent == null) {
                appComponent = createAppComponent(baseApplication);
            }
        }
    }
}
