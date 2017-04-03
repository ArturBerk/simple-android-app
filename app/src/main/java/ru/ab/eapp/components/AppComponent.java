package ru.ab.eapp.components;

import android.app.Application;

import org.robobinding.binder.BinderFactory;

import javax.inject.Singleton;

import dagger.Component;
import ru.ab.eapp.services.TimerService;
import ru.ab.eapp.ui.AppActivity;
import ru.ab.eapp.ui.MainFragment;
import ru.ab.eapp.vm.MainViewModel;

/**
 * Created by Berk on 11.03.2017.
 */
@Singleton
@Component(modules = {
        StoreModule.class,
        AppModule.class
})
public interface AppComponent {

    void inject(AppActivity mainActivity);
    void inject(MainFragment mainFragment);
    void inject(TimerService timerService);
    BinderFactory binderFactory();

    MainViewModel main();

}
