package com.example.fit_buddy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class User_Fragment extends Fragment {
    FragmentManager fragmentManager1;
    FragmentTransaction fragmentTransaction1;
    CardView cvFirst, cvSecond;
    TextView mtv, ptv, userChoose,usernameDisplay;


    String Username;
    private FirebaseUser user;
    private DatabaseReference databaseReference;
    private String USERNAME;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user_,container,false);

        cvFirst = (CardView) v.findViewById(R.id.FirstActivity);
        cvSecond = (CardView) v.findViewById(R.id.SecondActivity);
        mtv = (TextView) v.findViewById(R.id.moerTV);
        ptv = (TextView) v.findViewById(R.id.profileTV);
        usernameDisplay = (TextView) v.findViewById(R.id.usernamehere);
        userChoose = (TextView) v.findViewById(R.id.UserCHOOSE);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        USERNAME = user.getUid();
    databaseReference.child(USERNAME).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile != null){
                    Username = userProfile.fullname;

                    usernameDisplay.setText("Welcome " + Username);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        cvFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager1 = getActivity().getSupportFragmentManager();
                fragmentTransaction1 = fragmentManager1.beginTransaction();
                fragmentTransaction1.replace(R.id.Activity_here, new User_Profile_Activity_Fragment());
                fragmentTransaction1.commit();
                ptv.setBackgroundResource(R.drawable.custom_rounded_boxes2);
                mtv.setBackgroundResource(R.drawable.custom_rounded_boxes);
                userChoose.setVisibility(View.GONE);
            }
        });

        cvSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager1 = getActivity().getSupportFragmentManager();
                fragmentTransaction1 = fragmentManager1.beginTransaction();
                fragmentTransaction1.replace(R.id.Activity_here, new Setting_Activity_Fragment());
                fragmentTransaction1.commit();
                mtv.setBackgroundResource(R.drawable.custom_rounded_boxes2);
                ptv.setBackgroundResource(R.drawable.custom_rounded_boxes);
                userChoose.setVisibility(View.GONE);
            }
        });
        return v;
    }

}