package ru.ab.eapp.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.github.florent37.viewanimator.ViewAnimator;

import javax.inject.Inject;

import ru.ab.eapp.App;
import ru.ab.eapp.R;
import ru.ab.eapp.vm.MainViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    public static final String TAG = "Main";
    public static final String MODEL_KEY = "Model";

    MainViewModel model;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        model = App.component().main();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return App.component().binderFactory()
                .createViewBinder(this.getContext())
                .inflateAndBind(R.layout.fragment_main, model);
        //return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        final View view = getView().findViewById(R.id.button);
        final View ripple = getView().findViewById(R.id.ripple);
        ripple.setAlpha(0);

        view.setOnTouchListener((v, event) -> {
            if (event.getAction() != MotionEvent.ACTION_DOWN) return false;
            ViewAnimator
                    .animate(ripple)
                    .scale(0.6f, 1.0f)
                    .alpha(1.0f, 0.0f)
                    .interpolator(new DecelerateInterpolator())
                    .duration(1000)
                    .start();
            return false;
        });
    }

    @Override
    public void onStop() {
        model.dispose();
        super.onStop();
    }
}
