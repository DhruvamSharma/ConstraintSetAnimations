package com.udafil.dhruvamsharma.part3;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;

public class FinishActivity extends AppCompatActivity {

    CardAdapter adapter;
    RecyclerView mcardList;
    LottieAnimationView submitButton;

    ConstraintLayout layout, finalLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        layout= findViewById(R.id.finish_root);
        finalLayout = findViewById(R.id.credit_card_root);

        mcardList = findViewById(R.id.card_list_rv);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mcardList.setLayoutManager(layoutManager);


        adapter = new CardAdapter(preparePaymentData());
        mcardList.setAdapter(adapter);

        //submitButton = findViewById(R.id.submit_button_animation);

        /*submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSubmitAnimation();
            }
        });*/

        findViewById(R.id.submit_button).setOnClickListener(view -> {


            View view1 = finalLayout;

            Button button = findViewById(R.id.submit_button);



            int startRadius = 0;
            int endRadius = (int) Math.hypot(view1.getWidth(), view1.getHeight());


            int cx = (int) (button.getX() + (button.getWidth()/2));
            int cy = (int) (button.getY())+ button.getHeight() + 56;

            Animator animator = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                animator = ViewAnimationUtils.createCircularReveal(view1, cx, cy, startRadius, endRadius);
            }

            if (animator != null) {
                animator.start();
            }
        });



    }

    private List<PaymentMethodModel> preparePaymentData() {

        ArrayList<PaymentMethodModel> list = new ArrayList<>();

        PaymentMethodModel methodModelOne = new PaymentMethodModel();
        methodModelOne.setCardNumber("5378 **** **** ****");
        methodModelOne.setPaymentMethod("MASTREO");
        list.add(methodModelOne);

        PaymentMethodModel methodModelTwo = new PaymentMethodModel();
        methodModelTwo.setPaymentMethod("PayTM");
        methodModelTwo.setCardNumber("8763 **** **** ****");
        list.add(methodModelTwo);

        PaymentMethodModel methodModelThree = new PaymentMethodModel();
        methodModelThree.setPaymentMethod("Amazon Pay");
        methodModelThree.setCardNumber("7343 **** **** ****");
        list.add(methodModelThree);

        return list;

    }

    private void startSubmitAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f).setDuration(2000);
        animator.addUpdateListener(valueAnimator -> submitButton.setProgress((Float) valueAnimator.getAnimatedValue()));

        if (submitButton.getProgress() == 0f) {
            animator.start();
        } else {
            submitButton.setProgress(0f);
        }
    }
}
