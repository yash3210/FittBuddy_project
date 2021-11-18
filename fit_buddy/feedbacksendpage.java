package com.example.fit_buddy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fit_buddy.CALCULATOR.BD.Blood_Donation;

public class feedbacksendpage extends AppCompatActivity {
    View view;
    ImageView backtohomescreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbacksendpage);
        view = findViewById(R.id.included_progressbutton);
        backtohomescreen=(ImageView) findViewById(R.id.feedback_to_homescreen);
        EditText fillfeedback=(EditText) findViewById(R.id.here_feedback);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userfeedback=fillfeedback.getText().toString();
                check(userfeedback);

            }
        });
        backtohomescreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feedbacksendpage.this.onBackPressed();
            }
        });
    }
    public void check(String userfeedback) {
        if (userfeedback.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill first", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Progress_button progress_button= new Progress_button(feedbacksendpage.this,view);
            progress_button.buttonactivated();
            Dialog feedback_dialog=new Dialog(feedbacksendpage.this);
            feedback_dialog.setContentView(R.layout.feedback_popup);
            feedback_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            feedback_dialog.show();
            Handler mhandler = new Handler();
            mhandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    final Handler handler  = new Handler();
                    final Runnable runnable=new Runnable() {
                        @Override
                        public void run() {
                            if (feedback_dialog.isShowing()){
                                Toast.makeText(getApplicationContext(), "Feedback send", Toast.LENGTH_SHORT).show();
                                feedback_dialog.dismiss();
                            }
                        }
                    };
                    feedback_dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            handler.removeCallbacks(runnable);
                            feedbacksendpage.this.onBackPressed();
                        }
                    });
                    handler.postDelayed(runnable, 500);
                    progress_button.buttonstoped();
                }
            },2500);
        }
    }

}