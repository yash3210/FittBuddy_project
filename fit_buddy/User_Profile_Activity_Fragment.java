package com.example.fit_buddy;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class User_Profile_Activity_Fragment extends Fragment {

    private FirebaseUser user;
    private DatabaseReference databaseReference;

    String FFULLNAME,EEMAIL,PPASSWORD,PPHONENO;


    FirebaseAuth auth;
    private String userID;
    TextView EMAIL;
    Button update,resetPassword;

    EditText USERNAME,PHONE_NO,PASSWORD;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_user_profile_fragment,container,false);


        USERNAME = (EditText) v.findViewById(R.id.username);
        EMAIL = (TextView) v.findViewById(R.id.email);
        PHONE_NO = (EditText) v.findViewById(R.id.phoneNumber);
        resetPassword = (Button) v.findViewById(R.id.resetpassword);
        //PASSWORD = (EditText) v.findViewById(R.id.password);
        //update = (Button) v.findViewById(R.id.updateUSERINFO);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        userID = user.getUid();

//        update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                user = FirebaseAuth.getInstance().getCurrentUser();
//                databaseReference = FirebaseDatabase.getInstance().getReference("users");
//
//                String updateEmail = EMAIL.getText().toString();
//                String updateusername = USERNAME.getText().toString();
//                String updatepassword = PASSWORD.getText().toString();
//                update();
//
//
//            }
//        });

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText resetMail=new EditText(v.getContext());
                final AlertDialog.Builder passwordresetDialog = new AlertDialog.Builder(v.getContext());
                passwordresetDialog.setTitle("Reset Password ?");
                passwordresetDialog.setMessage("enter your email to recieve reset link.");
                passwordresetDialog.setView(resetMail);

                passwordresetDialog.setPositiveButton("GET RESET LINK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //extract the email and send reset link

                        String mail= resetMail.getText().toString();

                        if (TextUtils.isEmpty(mail))
                        {
                            Toast.makeText(getActivity(), "Please Enter Email", Toast.LENGTH_LONG).show();
                        }

                        else {
                            auth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void avoid) {
                                    Toast.makeText(getActivity(), "Reset link sent to your email", Toast.LENGTH_LONG).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getActivity(), "error ! reset link is not sent"+e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });}
                    }
                });
                passwordresetDialog.show();
//                passwordresetDialog.setPositiveButton("no", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                    }
//                });

            }
        });


        databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile != null){
                    FFULLNAME = userProfile.fullname;
                    PPHONENO = userProfile.phoneNo;
                     //PPASSWORD = userProfile.password;
                     EEMAIL = userProfile.email;

                    USERNAME.setText(FFULLNAME);
                    EMAIL.setText(EEMAIL);
                    //PASSWORD.setText(PPASSWORD);
                    PHONE_NO.setText(PPHONENO);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return v;
    }

//    public void update(){
//
//        if (isNameChanged() || isPasswordChanged()) {
//            Toast.makeText(getActivity(), "Data has been updated", Toast.LENGTH_SHORT).show();
//
//        }
//        else
//        {
//            Toast.makeText(getActivity(), "Data is same and can not be updated", Toast.LENGTH_SHORT).show();
//        }
//
//    }
////
//    private boolean isPasswordChanged() {
//        if (!PPASSWORD.equals(PASSWORD.getText().toString())){
//
//            databaseReference.child(PPASSWORD).child("password").setValue(PASSWORD.getEditableText().toString());
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//    }
//    private boolean isNameChanged() {
//        if (!FFULLNAME.equals(USERNAME.getText().toString())){
//
//            databaseReference.child(FFULLNAME).child("fullname").setValue(USERNAME.getEditableText().toString());
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//    }
}