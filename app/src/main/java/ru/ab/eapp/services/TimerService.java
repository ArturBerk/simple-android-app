package ru.ab.eapp.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import ru.ab.eapp.App;
import ru.ab.eapp.stores.TimerStore;

/**
 * Created by Berk on 02.04.2017.
 */

public class TimerService extends Service {

    private Disposable subscription;
    private ScheduledThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(1);
    private ScheduledFuture scheduled;
    @Inject
    TimerStore timerStore;

    @Override
    public void onCreate() {
        App.component().inject(this);
        super.onCreate();
        executorService = new ScheduledThreadPoolExecutor(1);
        subscription = timerStore.onChange().subscribe(av -> {
            Log.d("TimerService", timerStore.isActive() ? "true" : "false");
            if (timerStore.isActive() && scheduled == null) {
                scheduled = executorService.scheduleAtFixedRate(() -> {
                        updateTimer();
                }, 1000, 1000, TimeUnit.MILLISECONDS);
            } else if (!timerStore.isActive() && scheduled != null){
                scheduled.cancel(false);
                scheduled = null;
            }
        });
    }

    private void updateTimer() {
        timerStore.update();
    }

    @Override
    public void onDestroy() {
        if (subscription != null) subscription.dispose();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
