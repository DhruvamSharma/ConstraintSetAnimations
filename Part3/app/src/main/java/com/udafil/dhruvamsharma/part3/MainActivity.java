package com.udafil.dhruvamsharma.part3;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    LottieAnimationView animationView, heartAnimation, addressAnimation;
    ConstraintLayout creditCard, addressLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_scene_one);

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

        animationView = findViewById(R.id.lottieAnimationView);
        heartAnimation = findViewById(R.id.heartAnimation);
        addressAnimation = findViewById(R.id.select_address_animation);

        /*creditCard = findViewById(R.id.credit_card_layout);*/

        TextView textView = findViewById(R.id.previous_price_tv);
        textView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        addressLayout = findViewById(R.id.address_one);
        addressLayout.setOnClickListener(view -> selectAddressAnimation());


        startAnimations();
    }

    private void startAnimations() {

         Transition transition = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            transition = new ChangeBounds();
            transition.setInterpolator( new OvershootInterpolator());
        }

        final ConstraintSet constraintSetRoot = new ConstraintSet();
        constraintSetRoot.clone((ConstraintLayout) findViewById(R.id.root));

        final ConstraintSet constraintSetEnd = new ConstraintSet();
        constraintSetEnd.clone(this, R.layout.activity_main_scene_two);

        final ConstraintSet constraintSetFinish = new ConstraintSet();
        constraintSetFinish.clone(this, R.layout.activity_main_scene_three);

        Transition finalTransition = transition;
        findViewById(R.id.buy_button_btn).setOnClickListener(view -> {

            ConstraintSet constraintSet = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                TransitionManager.beginDelayedTransition((ViewGroup) findViewById(R.id.root), finalTransition);


                    constraintSet = constraintSetEnd;



                constraintSet.applyTo((ConstraintLayout) findViewById(R.id.root));

            }

        });

        findViewById(R.id.cancel_button_ib).setOnClickListener(view -> {

            ConstraintSet constraintSet = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                TransitionManager.beginDelayedTransition(findViewById(R.id.root),finalTransition);


                    constraintSet = constraintSetFinish;
                    //TODO
                    //startCheckAnimation();

                //constraintSet.applyTo(findViewById(R.id.root));

                Intent intent = new Intent(this, FinishActivity.class);
                startActivity(intent);

            }

        });

        findViewById(R.id.cancel_order_btn).setOnClickListener(view -> {

            ConstraintSet constraintSet;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                TransitionManager.beginDelayedTransition((ViewGroup) findViewById(R.id.root), finalTransition);
            }


            constraintSet = constraintSetRoot;



            constraintSet.applyTo((ConstraintLayout) findViewById(R.id.root));

        });


        findViewById(R.id.go_back_btn).setOnClickListener(view -> {

            ConstraintSet constraintSet = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                TransitionManager.beginDelayedTransition(findViewById(R.id.root), finalTransition);


                constraintSet = constraintSetRoot;


                constraintSet.applyTo(findViewById(R.id.root));

            }

        });

        //TODO
        /*creditCard.setOnClickListener(view -> {


            startLikeAnimation();

        });*/

    }



    private void startCheckAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f).setDuration(1000);
        animator.addUpdateListener(valueAnimator -> animationView.setProgress((Float) valueAnimator.getAnimatedValue()));

        if (animationView.getProgress() == 0f) {
            animator.start();
        } else {
            animationView.setProgress(0f);
        }
    }

    private void startLikeAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f).setDuration(1000);
        animator.addUpdateListener(valueAnimator -> heartAnimation.setProgress((Float) valueAnimator.getAnimatedValue()));

        if (heartAnimation.getProgress() == 0f) {
            animator.start();
        } else {
            heartAnimation.setProgress(0f);
        }
    }




    public void addCardDialog(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.MyDialogTheme);

        View view1 = View.inflate(this, R.layout.alert_dialog_layout, null);

        builder.setView(view1);

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();


    }


    private void selectAddressAnimation() {

        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f).setDuration(1000);
        animator.addUpdateListener(valueAnimator -> addressAnimation.setProgress((Float) valueAnimator.getAnimatedValue()));

        if (addressAnimation.getProgress() == 0f) {
            animator.start();
        } else {
            addressAnimation.setProgress(0f);
        }

    }

    public void selectAddress(View view) {

        selectAddressAnimation();
    }
}
