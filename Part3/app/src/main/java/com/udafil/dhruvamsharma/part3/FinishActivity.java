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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);


        mcardList = findViewById(R.id.card_list_rv);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mcardList.setLayoutManager(layoutManager);


        adapter = new CardAdapter(preparePaymentData());
        mcardList.setAdapter(adapter);






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


}
