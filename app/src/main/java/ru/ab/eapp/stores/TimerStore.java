package ru.ab.eapp.stores;

import ru.ab.storex.Action;
import ru.ab.storex.BaseStore;
import ru.ab.storex.Dispatcher;

/**
 * Created by Berk on 02.04.2017.
 */

public class TimerStore extends BaseStore<Boolean> {

    private int seconds;
    private boolean active;

    public TimerStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    public int getSeconds() {
        return seconds;
    }

    public boolean isActive() {
        return active;
    }

    public void start() {
        dispatch(new StartAction());
    }

    public void stop() {
        dispatch(new StopAction());
    }

    public void reset() {
        dispatch(new ResetAction());
    }

    public void update() {
        dispatch(new UpdateAction());
    }

    static class StartAction implements Action<TimerStore> {

        public void apply(TimerStore state) {
            state.active = true;
            state.onChange.onNext(state.active);
        }

    }

    static class StopAction implements Action<TimerStore> {

        public void apply(TimerStore state) {
            state.active = false;
            state.onChange.onNext(state.active);
        }

    }

    static class UpdateAction implements Action<TimerStore> {

        public void apply(TimerStore state) {
            state.seconds += 1;
            state.onChange.onNext(state.active);
        }

    }

    static class ResetAction implements Action<TimerStore> {

        public void apply(TimerStore state) {
            state.seconds = 0;
            state.active = false;
            state.onChange.onNext(state.active);
        }

    }
}
