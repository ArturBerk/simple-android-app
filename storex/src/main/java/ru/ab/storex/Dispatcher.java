package ru.ab.storex;

/**
 * Created by Berk on 02.04.2017.
 */

public interface Dispatcher {

    void dispatch(Runnable action);

}
