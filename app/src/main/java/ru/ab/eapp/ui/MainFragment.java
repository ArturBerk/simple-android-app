package ru.ab.eapp.ui;


import android.animation.ObjectAnimator;
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

        final float elevation = view.getElevation();

        view.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                ViewAnimator.animate(view).custom((view1, value) -> {
                    view1.setElevation(value);
                }, view.getElevation(), 5.0f).duration(200).decelerate().start();
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                ViewAnimator.animate(view).custom((view1, value) -> {
                    view1.setElevation(value);
                }, view.getElevation(), elevation).duration(200).decelerate().start();
            }
            if (event.getAction() != MotionEvent.ACTION_DOWN) return false;

//            if (animator != null) {
//                animator.cancel();
//            }
//            animator = ObjectAnimator.ofFloat(view, "elevation", view.getElevation(), 30.0f);

            ripple.setScaleX(1.0f);
            ripple.setScaleY(1.0f);
            ripple.setAlpha(1.0f);
            ripple.animate()
                    .scaleX(1.5f)
                    .scaleY(1.5f)
                    .alpha(0.0f)
                    .setInterpolator(new DecelerateInterpolator())
                    .setDuration(1000)
                    .start();
            return false;
        });
    }

    private ObjectAnimator animator;

    @Override
    public void onStop() {
        model.dispose();
        super.onStop();
    }
}
