package com.example.fit_buddy;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Progress_button {
    ProgressBar progressBar;
    ConstraintLayout layout;
    CardView cardview;
    TextView textView;

    Progress_button(Context ct, View view)
    {
        progressBar = view.findViewById(R.id.progressBar);
        layout = view.findViewById(R.id.conslayout);
        textView = view.findViewById(R.id.textView3);
        cardview = view.findViewById(R.id.cardviewforcolor);
    }
    void buttonactivated()
    {

        progressBar.setVisibility(View.VISIBLE);
        textView.setText("Please Wait..");
    }
    void buttonstoped()
    {
        layout.setBackgroundColor(cardview.getResources().getColor(R.color.sea_foam_green));
        progressBar.setVisibility(View.GONE);
        textView.setText("SEND");
    }
}

