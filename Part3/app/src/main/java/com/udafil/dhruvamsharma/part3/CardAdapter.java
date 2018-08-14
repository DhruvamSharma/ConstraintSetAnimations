package com.udafil.dhruvamsharma.part3;

import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.List;
import java.util.zip.Inflater;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardHolder> {

    List<PaymentMethodModel> mData;

    public CardAdapter(List<PaymentMethodModel> data) {
        mData = data;
    }


    @NonNull
    @Override
    public CardHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.creditcard_layout, viewGroup, false);

        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardHolder cardHolder, int i) {
        cardHolder.paymentMethod.setText(mData.get(i).getPaymentMethod());
        cardHolder.cardNumber.setText(mData.get(i).getCardNumber());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class CardHolder extends RecyclerView.ViewHolder {

        TextView cardNumber, paymentMethod;
        LottieAnimationView animationView;

        public CardHolder(@NonNull View itemView) {
            super(itemView);

            cardNumber = itemView.findViewById(R.id.creditcard_layout_number_tv);
            paymentMethod = itemView.findViewById(R.id.creditcard_layout_visa_tv);
            animationView = itemView.findViewById(R.id.heartAnimation);

            itemView.setOnClickListener(view -> startCheckAnimation());
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
    }

}
