package ru.ab.storex;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by Berk on 02.04.2017.
 */

public class BaseStore<TChange> implements Store<TChange> {

    protected Subject<TChange> onChange = PublishSubject.create();
    private Dispatcher dispatcher;

    public BaseStore(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public <T extends Store<TChange>> void dispatch(Action<T> action) {
        dispatcher.dispatch(new ActionRunnable<>(action));
    }

    @Override
    public Observable<TChange> onChange() {
        return onChange;
    }

    private class ActionRunnable<T extends Store<?>> implements Runnable {
        private Action<T> action;

        public ActionRunnable(Action<T> action) {
            this.action = action;
        }

        @Override
        public void run() {
            this.action.apply((T)BaseStore.this);
        }
    }
}
