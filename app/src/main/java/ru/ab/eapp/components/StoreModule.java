package ru.ab.eapp.components;

import android.os.Handler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.ab.eapp.App;
import ru.ab.eapp.stores.TimerStore;
import ru.ab.eapp.utils.AndroidDispatcher;
import ru.ab.storex.Dispatcher;

/**
 * Created by Berk on 02.04.2017.
 */

@Module
public class StoreModule {

    private Dispatcher dispatcher;
    private App mApplication;

    public StoreModule(App application) {
        mApplication = application;
        dispatcher = new AndroidDispatcher(new Handler(mApplication.getMainLooper()));
    }

    @Provides
    @Singleton
    public TimerStore timerStore() {
        return new TimerStore(dispatcher);
    }

}
