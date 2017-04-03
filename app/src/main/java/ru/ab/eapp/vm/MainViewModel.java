package ru.ab.eapp.vm;

import org.robobinding.annotation.PresentationModel;

import io.reactivex.disposables.Disposable;
import ru.ab.eapp.stores.TimerStore;

/**
 * Created by Berk on 01.04.2017.
 */

@PresentationModel
public class MainViewModel extends BaseViewModel implements Disposable {

    private int seconds = 0;
    private TimerStore timerStore;
    private Disposable subscription;

    public MainViewModel(TimerStore timerStore) {
        this.timerStore = timerStore;
        updateSeconds();
        subscription = this.timerStore.onChange().subscribe(aVoid -> {
            updateSeconds();
        });
    }

    private void updateSeconds() {
        setSeconds(this.timerStore.getSeconds());
    }

    public void toggle() {
        if (timerStore.isActive()) {
            timerStore.stop();
        } else {
            timerStore.start();
        }
    }

    // Return true to stop from executing click after long click
    public boolean reset() {
        timerStore.reset();
        return true;
    }

    public String getTime() {
        return String.format("%02d:%02d", seconds / 60, seconds % 60);
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
        fireChanged("time");
    }

    @Override
    public void dispose() {
        if (subscription != null) subscription.dispose();
    }

    @Override
    public boolean isDisposed() {
        return subscription == null;
    }
}
