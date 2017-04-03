package ru.ab.storex;

/**
 * Created by Berk on 02.04.2017.
 */

public interface Action<T extends Store> {


    void apply(T state);

}
