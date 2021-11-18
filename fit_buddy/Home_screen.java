package com.example.fit_buddy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.accessibilityservice.GestureDescription;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fit_buddy.CALCULATOR.CalculatorMainScreen;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Home_screen extends Fragment {


    private int[] myImageList = new int[]{R.drawable.banner_1, R.mipmap.banner_calculator, R.mipmap.banner_3, R.mipmap.img_reminder};

    private static ViewPager mPager;
    private static int NUM_PAGES = 0;
    private static int currentPage = 0;
    private ArrayList<SlidingModel> imageModelArrayList;


    Button monday_diet, tuesday_diet, wednesday_diet, thursday_diet, friday_diet, saturday_diet, sunday_diet;
    TextView userprofile, comment;
    ImageButton yoga, back, biceps, chestexercise, legsexercise, shoulderexercise, tricepsexercise, cardioexercise, cyclingexercise, runningexercise, calculator;
    String UniId;
    ImageView more_options;
    SharedPreferences sharedPreferences;
    private Databasehelper db;


    public static final String filename = "login";
    public static final String checkuser = "username";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View v = inflater.inflate(R.layout.activity_home_screen, container, false);
        View inflate = inflater.inflate(R.layout.activity_home_screen, container, false);
        mPager = inflate.findViewById(R.id.pager);

        imageModelArrayList = populateList();

        mPager.setAdapter(new SlidingAdapter(getActivity(), imageModelArrayList));

        NUM_PAGES = imageModelArrayList.size();

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, false);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 4000, 7000);


        yoga=(ImageButton) inflate.findViewById(R.id.yoga);
        back=(ImageButton) inflate.findViewById(R.id.backs);
        biceps=(ImageButton) inflate.findViewById(R.id.bicep);
        chestexercise=(ImageButton) inflate.findViewById(R.id.chest);
        legsexercise=(ImageButton) inflate.findViewById(R.id.legs);
        shoulderexercise=(ImageButton) inflate.findViewById(R.id.shoulders);
        tricepsexercise=(ImageButton) inflate.findViewById(R.id.triceps);
        cardioexercise=(ImageButton) inflate.findViewById(R.id.cardio);
        cyclingexercise=(ImageButton) inflate.findViewById(R.id.cycling);
        runningexercise=(ImageButton) inflate.findViewById(R.id.running);
        //userprofile=(TextView) v.findViewById(R.id.hello_username);
        //comment=(TextView) v.findViewById(R.id.get_in_shape);
        monday_diet=(Button) inflate.findViewById(R.id.monday);
        tuesday_diet=(Button) inflate.findViewById(R.id.tuesday);
        wednesday_diet=(Button) inflate.findViewById(R.id.wednesday);
        thursday_diet=(Button) inflate.findViewById(R.id.thursday);
        friday_diet=(Button) inflate.findViewById(R.id.friday);
        saturday_diet=(Button) inflate.findViewById(R.id.saturday);
        sunday_diet=(Button) inflate.findViewById(R.id.sunday);
        //more_options = v.findViewById(R.id.menu_options);
        // calculator = (ImageButton) findViewById(R.id.calculatorbtn);



        //String str = getActivity().getIntent().getExtras().getString("up");



        //Bundle bundle1= getIntent().getExtras();




//        Intent intent= getActivity().getIntent();
//        String str = intent.getStringExtra("up");
//        if (intent.getExtras() == null)
//        {
//            userprofile.setText("Hey");
//            comment.setText("Welcome back");
//        }
//        if (str!=null)
//        {
//            userprofile.setText("Hello "+str);
//            comment.setText("Get in shape (👉ﾟヮﾟ)👉");




//             Boolean updatedata = db.updatedata(str);
//             if (updatedata==true) {
//                 db.updatedata(str);
//                 Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
//             }
//             else
//                 Toast.makeText(getApplicationContext(), "ajja bc", Toast.LENGTH_SHORT).show();



        //}




//        sharedPreferences = getSharedPreferences(filename, Context.MODE_PRIVATE);
//        if (sharedPreferences.contains(checkuser)){
//            userprofile.setText("Get in shape (👉ﾟヮﾟ)👉 " + sharedPreferences.getString(checkuser,""));
//        }



        yoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UniId= yoga.getTag().toString();
                sendtocontentscreen(UniId);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UniId= back.getTag().toString();
                sendtocontentscreen(UniId);
            }
        });
        biceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UniId= biceps.getTag().toString();
                sendtocontentscreen(UniId);
            }
        });
        chestexercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UniId= chestexercise.getTag().toString();
                sendtocontentscreen(UniId);
            }
        });
        legsexercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UniId= legsexercise.getTag().toString();
                sendtocontentscreen(UniId);
            }
        });
        shoulderexercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UniId= shoulderexercise.getTag().toString();
                sendtocontentscreen(UniId);
            }
        });
        tricepsexercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UniId= tricepsexercise.getTag().toString();
                sendtocontentscreen(UniId);
            }
        });
        cardioexercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UniId= cardioexercise.getTag().toString();
                sendtocontentscreen(UniId);
            }
        });
        cyclingexercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UniId= cyclingexercise.getTag().toString();
                sendtocontentscreen(UniId);
            }
        });
        runningexercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UniId= runningexercise.getTag().toString();
                sendtocontentscreen(UniId);
            }
        });
        monday_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UniId= monday_diet.getTag().toString();
                sendtocontentscreen(UniId);
            }
        });
        tuesday_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UniId= tuesday_diet.getTag().toString();
                sendtocontentscreen(UniId);
            }
        });
        wednesday_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UniId= wednesday_diet.getTag().toString();
                sendtocontentscreen(UniId);
            }
        });
        thursday_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UniId= thursday_diet.getTag().toString();
                sendtocontentscreen(UniId);
            }
        });
        friday_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UniId= friday_diet.getTag().toString();
                sendtocontentscreen(UniId);
            }
        });
        saturday_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UniId= saturday_diet.getTag().toString();
                sendtocontentscreen(UniId);
            }
        });
        sunday_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UniId= sunday_diet.getTag().toString();
                sendtocontentscreen(UniId);
            }
        });
//        calculator.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent calpage = new Intent(Home_screen.this, CalculatorMainScreen.class);
//                startActivity(calpage);
//            }
//        });
//        more_options.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Dialog moredialog = new Dialog(Home_screen.this);
//                moredialog.setContentView(R.layout.more_options_popup);
//                moredialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                TextView contactus= (TextView) moredialog.findViewById(R.id.contactushere);
//                TextView aboutus=(TextView) moredialog.findViewById(R.id.aboutushere);
//
//                contactus.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent contactscreen=new Intent(getActivity(),Contact_us_screen.class);
//                        startActivity(contactscreen);
//                        moredialog.dismiss();
//                    }
//                });
//                aboutus.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent aboutusscreen = new Intent(getActivity(),About_us_screen.class);
//                        startActivity(aboutusscreen);
//                        moredialog.dismiss();
//                    }
//                });
//                moredialog.show();
//            }
//        });
        return inflate;
        //return v;

    }

    public void sendtocontentscreen(String x)
    {
        Intent IntentContent= new Intent(getActivity(),Content.class);
        IntentContent.putExtra("UniqueID",x);
        startActivity(IntentContent);
    }
    private ArrayList<SlidingModel> populateList() {
        ArrayList<SlidingModel> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            SlidingModel imageModel = new SlidingModel();
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }
        return list;
    }
}

//    @SuppressLint("WrongConstant")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home_screen);
//
//}