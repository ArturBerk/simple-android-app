package ru.ab.storex;

import io.reactivex.Observable;

/**
 * Created by Berk on 02.04.2017.
 */

public interface Store<TChange> {

    Observable<TChange> onChange();
    <T extends Store<TChange>> void dispatch(Action<T> action);

}
