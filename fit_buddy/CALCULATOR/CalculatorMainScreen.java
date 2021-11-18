package com.example.fit_buddy.CALCULATOR;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fit_buddy.CALCULATOR.BD.Blood_Donation;
import com.example.fit_buddy.CALCULATOR.BFS.Body_Frame_Size;
import com.example.fit_buddy.CALCULATOR.BMR.BMR;
import com.example.fit_buddy.CALCULATOR.BS.Blood_Sugar;
import com.example.fit_buddy.CALCULATOR.CB.Calorie_Burn;
import com.example.fit_buddy.CALCULATOR.DCI.Daily_Calories_Intake;
import com.example.fit_buddy.CALCULATOR.HR.Heart_Rate;
import com.example.fit_buddy.CALCULATOR.KG.Kids_Growth;
import com.example.fit_buddy.R;


public class CalculatorMainScreen extends Fragment {
    CardView cvBMR,cvBD,cvBS,cvBFS,cvCB,cvHR,cvKG,cvDCI,cvBP,cvBF,cvBMI,cvCR,cvBV,cvDWI,cvIBW,cvBSA;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_calculator_main_screen,container,false);

       // setContentView(R.layout.activity_calculator_main_screen);
        cvBMR = (CardView) v.findViewById(R.id.bmrCALCULATOR);
        cvBD = (CardView) v.findViewById(R.id.bdCALCULATOR);
        cvBS = (CardView) v.findViewById(R.id.bsCALCULATOR);
        cvBFS = (CardView) v.findViewById(R.id.bfsCALCULATOR);
        cvCB = (CardView) v.findViewById(R.id.cbCALCULATOR);
        cvHR = (CardView) v.findViewById(R.id.hrCALCULATOR);
        cvKG = (CardView) v.findViewById(R.id.kgCALCULATOR);
        cvDCI = (CardView) v.findViewById(R.id.dciCALCULATOR);

        cvBP = (CardView) v.findViewById(R.id.bpCALCULATOR);
        cvBF = (CardView) v.findViewById(R.id.bfCALCULATOR);
        cvBMI = (CardView) v.findViewById(R.id.bmiCALCULATOR);
        cvCR = (CardView) v.findViewById(R.id.crCALCULATOR);
        cvBP = (CardView) v.findViewById(R.id.bpCALCULATOR);
        cvBV = (CardView) v.findViewById(R.id.bvCALCULATOR);
        cvDWI = (CardView) v.findViewById(R.id.dwiCALCULATOR);
        cvIBW = (CardView) v.findViewById(R.id.ibwCALCULATOR);
        cvBSA = (CardView) v.findViewById(R.id.bsaCALCULATOR);


        cvBMR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),BMR.class);
                startActivity(intent);
            }
        });
        cvBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Blood_Donation.class);
                startActivity(intent);
            }
        });
        cvBS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Blood_Sugar.class);
                startActivity(intent);
            }
        });
        cvBFS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Body_Frame_Size.class);
                startActivity(intent);
            }
        });
        cvCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Calorie_Burn.class);
                startActivity(intent);
            }
        });
        cvHR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Heart_Rate.class);
                startActivity(intent);
            }
        });
        cvKG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Kids_Growth.class);
                startActivity(intent);
            }
        });
        cvDCI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Daily_Calories_Intake.class);
                startActivity(intent);
            }
        });

        return v;


    }

//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//    }
}