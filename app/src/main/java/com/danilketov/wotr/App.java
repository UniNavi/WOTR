package com.danilketov.wotr;

import android.app.Application;

import com.danilketov.wotr.di.AppComponent;
import com.danilketov.wotr.di.AppModule;
import com.danilketov.wotr.di.DaggerAppComponent;

public class App extends Application {

    private static AppComponent appComponent;
    private static App INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;

        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}

