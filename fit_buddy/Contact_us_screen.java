package com.example.fit_buddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Contact_us_screen extends AppCompatActivity {
    EditText subject,message;
    TextView name;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us_screen);
        name=(TextView) findViewById(R.id.et_your_name);
        subject=(EditText) findViewById(R.id.et_your_subject);
        message=(EditText) findViewById(R.id.et_your_message);
        back = (ImageView) findViewById(R.id.backbutton);
        Button proceed=(Button) findViewById(R.id.click_to_proceed_tbn);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact_us_screen.this.onBackPressed();
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sending_mail();
            }
        });
    }
    public void sending_mail()
    {

        String recipientlist= name.getText().toString();
        String[] recipient=recipientlist.split(",");

        String subjecthere=subject.getText().toString();
        String messagehere=message.getText().toString();

        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,recipient);
        intent.putExtra(Intent.EXTRA_SUBJECT,subjecthere);
        intent.putExtra(Intent.EXTRA_TEXT,messagehere);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"Choose an Email client"));

    }
}