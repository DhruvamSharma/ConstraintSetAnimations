package com.udafil.dhruvamsharma.part2;

import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addAnimations();
    }

    private void addAnimations() {

        final boolean[] set = {false};

        final ConstraintSet constraintSetRoot = new ConstraintSet();
        constraintSetRoot.clone((ConstraintLayout) findViewById(R.id.root));

        final ConstraintSet constraintSetStart = new ConstraintSet();
        constraintSetStart.clone(this, R.layout.activity_main_java_focus);

        final ConstraintSet constraintSetFinish = new ConstraintSet();
        constraintSetFinish.clone(this, R.layout.activity_main_kotlin_focus);

        Transition transition = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            transition = new ChangeBounds();
            transition.setInterpolator(new OvershootInterpolator());
        }

        final Transition finalTransition = transition;
        findViewById(R.id.java_image_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

                    ConstraintSet constraintSet;
                    TextView tView  = findViewById(R.id.java_image_tv);
                    TransitionManager.beginDelayedTransition((ViewGroup) findViewById(R.id.root), finalTransition);

                    if(set[0]) {
                        constraintSet = constraintSetStart;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            tView.setTextAppearance(R.style.TextAppearance_AppCompat_Display3);
                        }
                    } else {
                        constraintSet = constraintSetRoot;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            tView.setTextAppearance(R.style.TextAppearance_AppCompat_Headline);
                        }
                    }

                    constraintSet.applyTo((ConstraintLayout) findViewById(R.id.root));
                    set[0] = !set[0];


                }
            }
        });


        final Transition finalTransition1 = transition;
        findViewById(R.id.kotlin_image_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConstraintSet constraintSet;
                TextView tView  = findViewById(R.id.kotlin_image_tv);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

                    if(set[0]) {
                        constraintSet = constraintSetFinish;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            tView.setTextAppearance(R.style.TextAppearance_AppCompat_Display3);
                        }
                    } else {
                        constraintSet = constraintSetRoot;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            tView.setTextAppearance(R.style.TextAppearance_AppCompat_Headline);
                        }
                    }

                    constraintSet.applyTo((ConstraintLayout) findViewById(R.id.root));
                    set[0] = !set[0];
                    TransitionManager.beginDelayedTransition((ViewGroup) findViewById(R.id.root), finalTransition1);

                }

            }
        });

    }
}
