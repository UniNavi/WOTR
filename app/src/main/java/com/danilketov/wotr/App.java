package com.danilketov.wotr;

import android.app.Application;

import androidx.room.Room;

import com.danilketov.wotr.db.AppDatabase;
import com.danilketov.wotr.di.AppComponent;
import com.danilketov.wotr.di.DaggerAppComponent;
import com.danilketov.wotr.network.HttpClient;
import com.danilketov.wotr.repository.DataRepository;

public class App extends Application {

    private static HttpClient httpClient;
    private static AppDatabase appDatabase;
    private static DataRepository dataRepository;
    private static AppComponent appComponent;
    private static App INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;

        appComponent = DaggerAppComponent.create();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static DataRepository getDataRepository() {
        if (dataRepository == null) {
            dataRepository = new DataRepository();
        }

        return dataRepository;
    }

    public static HttpClient getHttpClient() {
        if (httpClient == null) {
            httpClient = new HttpClient();
        }
        return httpClient;
    }

    public static AppDatabase getAppDatabase() {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(INSTANCE, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        }
        return appDatabase;
    }
}

