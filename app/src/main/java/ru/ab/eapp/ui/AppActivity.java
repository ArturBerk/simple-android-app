package ru.ab.eapp.ui;

import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import ru.ab.eapp.App;
import ru.ab.eapp.R;
import ru.ab.eapp.vm.AppViewModel;

public class AppActivity extends AppCompatActivity {

    @Inject
    AppViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.component().inject(this);
        super.onCreate(savedInstanceState);

        setContentView(
                App.component().binderFactory()
                        .createViewBinder(this)
                        .inflateAndBind(R.layout.activity_app, model));

        hideActionBar();
    }

    private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_place, MainFragment.newInstance(), MainFragment.TAG)
                .commit();
    }

}
