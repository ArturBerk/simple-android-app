package ru.ab.eapp;

import android.app.Application;
import android.content.Intent;

import ru.ab.eapp.components.AppComponent;
import ru.ab.eapp.components.AppModule;
import ru.ab.eapp.components.DaggerAppComponent;
import ru.ab.eapp.components.StoreModule;
import ru.ab.eapp.services.TimerService;

/**
 * Created by Berk on 11.03.2017.
 */
public class App extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .storeModule(new StoreModule(this))
                .build();
        startTimerService();
    }

    private void startTimerService() {
        Intent intent = new Intent(this, TimerService.class);
        startService(intent);
    }

    public static AppComponent component() {
        return appComponent;
    }
}
