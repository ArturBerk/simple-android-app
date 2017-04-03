package ru.ab.eapp.components.modules;

import android.app.Application;

import org.robobinding.binder.BinderFactory;
import org.robobinding.binder.BinderFactoryBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.ab.eapp.App;
import ru.ab.eapp.stores.TimerStore;
import ru.ab.eapp.vm.AppViewModel;
import ru.ab.eapp.vm.MainViewModel;

/**
 * Created by Berk on 11.03.2017.
 */
@Module
public class AppModule {

    private App mApplication;
    private BinderFactory binderFactory;

    public AppModule(App application) {
        mApplication = application;
        binderFactory = new BinderFactoryBuilder().build();
    }

    @Provides
    @Singleton
    public Application providesApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    public BinderFactory providesBinderFactory() {
        return binderFactory;
    }

    @Provides
    public AppViewModel provideAppViewModel() {
        return new AppViewModel();
    }

    @Provides
    public MainViewModel provideMainViewModel(TimerStore store) {
        return new MainViewModel(store);
    }

}
