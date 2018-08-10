package com.udafil.dhruvamsharma.constraintsetanimationspart1;

import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_start);

        addAnimationOperations();
    }

    private void addAnimationOperations() {
        final boolean[] set = {false};
        final ConstraintSet constraintSetStart = new ConstraintSet();
        constraintSetStart.clone((ConstraintLayout) findViewById(R.id.root));

        final ConstraintSet constraintSetEnd = new ConstraintSet();
        constraintSetEnd.clone(this, R.layout.activity_main_end);

        findViewById(R.id.mainTextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    ConstraintSet constraintSet;
                    TransitionManager.beginDelayedTransition((ViewGroup) findViewById(R.id.root));
                    if(set[0]) {
                        constraintSet = constraintSetEnd;
                    } else  {
                        constraintSet = constraintSetStart;
                    }

                    constraintSet.applyTo((ConstraintLayout) findViewById(R.id.root));
                    set[0] = !set[0];
                }
            }
        });
    }
}
