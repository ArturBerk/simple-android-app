package ru.ab.eapp.utils;

import android.os.Handler;

import ru.ab.storex.Dispatcher;

/**
 * Created by Berk on 02.04.2017.
 */

public class AndroidDispatcher implements Dispatcher {

    private Handler handler;

    public AndroidDispatcher(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void dispatch(Runnable action) {
        this.handler.post(action);
    }
}
