package com.example.fit_buddy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.fit_buddy.CALCULATOR.CalculatorMainScreen;
import com.hsalf.smileyrating.SmileyRating;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class Main_Fragment extends AppCompatActivity {

    private ChipNavigationBar chipNavigationBar;
    private Fragment fragment = null;
    Button norate,rateus,exit;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);

        chipNavigationBar = findViewById(R.id.chipNavigation);

        chipNavigationBar.setItemSelected(R.id.home, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new Home_screen()).commit();

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.home:
                        fragment = new Home_screen();
                        break;
                    case R.id.cart:
                        fragment = new CalculatorMainScreen();
                        break;
                    case R.id.profile:
                        fragment = new User_Fragment();
                        break;

                }

                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                }
            }
        });
    }
    @Override
    public void onBackPressed() {

        showExitDialog();

    }
    private void showExitDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.exit_rating_emojies);
        norate = (Button) dialog.findViewById(R.id.NORATE);
        rateus = (Button) dialog.findViewById(R.id.RATE);
        exit = (Button) dialog.findViewById(R.id.EXIT);
        dialog.getWindow().setLayout(getWindowWidth()-50,LinearLayout.LayoutParams.WRAP_CONTENT);

        SmileyRating rating = dialog.findViewById(R.id.SmilyRating);
        rating.setSmileySelectedListener(new SmileyRating.OnSmileySelectedListener() {
            @Override
            public void onSmileySelected(SmileyRating.Type type) {
                switch (type){
                    case TERRIBLE:
                        break;
                    case BAD:
                        break;
                    case OKAY:
                        break;
                    case GOOD:
                        break;
                    case GREAT:
                        break;
                }
            }
        });

        norate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + "com.android.chrome")));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                }

            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                finish();
                System.exit(0);
                moveTaskToBack(false);
            }
        });

        dialog.show();

    }

    private int getWindowWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }
}