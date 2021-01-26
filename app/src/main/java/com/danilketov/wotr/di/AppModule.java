package com.danilketov.wotr.di;

import android.content.Context;

import androidx.room.Room;

import com.danilketov.wotr.db.AppDatabase;
import com.danilketov.wotr.network.HttpClient;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context getContext() {
        return context;
    }

    @Provides
    @Singleton
    public HttpClient getHttpClient() {
        return new HttpClient();
    }

    @Provides
    @Singleton
    public AppDatabase providesAppDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
    }

    @Provides
    @Singleton
    Executor providesExecutor() {
        int numberOfCores = Runtime.getRuntime().availableProcessors();
        return Executors.newFixedThreadPool(numberOfCores);
    }
}
