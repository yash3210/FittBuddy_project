package com.example.fit_buddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.fit_buddy.CALCULATOR.BMR.BMR;
import com.example.fit_buddy.DataModel.DetailsListModel;
import com.example.fit_buddy.DataProvider.DataProvider;

import java.util.ArrayList;

public class Content extends AppCompatActivity implements ContentRecyclerViewAdaptor.ItemClickListener {
 private ContentRecyclerViewAdaptor adapter;
 ImageView feedback,backBUTTON;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        backBUTTON=(ImageView) findViewById(R.id.Backbtn) ;
        feedback=(ImageView) findViewById(R.id.feedbackbtn);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent feedbackpage=new Intent(Content.this,feedbacksendpage.class);
                startActivity(feedbackpage);
            }
        });

        backBUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Content.this.onBackPressed();
            }
        });

        DataProvider.fetchDetailList(this);
        Bundle bundle=getIntent().getExtras();
        DataProvider.fetchDetailList(this);

        String uniqueID = bundle.getString("UniqueID", "default");
        DetailsListModel dataModel = DataProvider.fetchDetailList(this);
        ArrayList<DetailsListModel.Details.Exercise> stepsArrayList=null;

        for (DetailsListModel.Details currentData: dataModel.details) {
            if(currentData.uniqueid.contentEquals(uniqueID)) {
                stepsArrayList=currentData.exercise;
                break;
            }
        }
        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if(stepsArrayList != null){
            adapter = new ContentRecyclerViewAdaptor(this, stepsArrayList);
            adapter.setClickListener(this);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
    }

}