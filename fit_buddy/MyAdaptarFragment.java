package com.example.fit_buddy;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.fit_buddy.CALCULATOR.CalculatorMainScreen;

public class MyAdaptarFragment extends FragmentPagerAdapter {

    public MyAdaptarFragment(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Home_screen();
            case 1:
                return new CalculatorMainScreen();
            case 2:
                return new User_Fragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
