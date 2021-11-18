package com.example.fit_buddy.CALCULATOR.CB;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.fit_buddy.R;
import com.example.fit_buddy.TypefaceManager;

import java.io.PrintStream;
import java.util.ArrayList;

public class Calorie_Burn extends AppCompatActivity {
    String TAG = getClass().getSimpleName();
   // AdView adView;
    ArrayAdapter<String> adapter_distance;
    ArrayAdapter<String> adapter_runwalk;
    ArrayAdapter<String> adapter_weight;
    ArrayList<String> arraylist_distance = new ArrayList<>();
    ArrayList<String> arraylist_runwalk = new ArrayList<>();
    ArrayList<String> arraylist_weigth = new ArrayList<>();
    float calories_burn;
    float distance;
    String distanceunit;
    EditText et_distance;
    EditText et_weight;
    //GlobalFunction globalFunction;
    ImageView iv_back;
    ListView listViewWeight;
    ListView listViewdistance;
    ListView listViewrunwalk;
    private PopupWindow popupWindowWeight;
    private PopupWindow popupWindowdistance;
    private PopupWindow popupWindowrunwalk;
    String runwalkunit;
    //SharedPreferenceManager sharedPreferenceManager;
    String tips;
    float total_calories_burn;
    float total_distance;
    TextView tv_cal_burn;
    TextView tv_distance_unit;
    TextView tv_runwalk;
    TextView tv_runwalk_unit;
    TextView tv_search_burn_calories;
    TextView tv_weightunit;
    TypefaceManager typefaceManager;
    float weight;
    String weightunit;

//
//    public void attachBaseContext(Context context) {
//        super.attachBaseContext(uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper.wrap(context));
//    }


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_calorie_burn);
//        this.sharedPreferenceManager = new SharedPreferenceManager(this);
//        this.globalFunction = new GlobalFunction(this);
//        this.typefaceManager = new TypefaceManager(getAssets(), this);
//        this.globalFunction.set_locale_language();
//        this.globalFunction.sendAnalyticsData(this.TAG, this.TAG);
        this.iv_back = (ImageView) findViewById(R.id.iv_back);
        this.tv_cal_burn = (TextView) findViewById(R.id.tv_cal_burn);
        this.et_weight = (EditText) findViewById(R.id.et_weight);
        this.et_distance = (EditText) findViewById(R.id.et_distance);
        this.tv_runwalk = (TextView) findViewById(R.id.tv_runwalk);
        this.tv_weightunit = (TextView) findViewById(R.id.tv_weightunit);
        this.tv_distance_unit = (TextView) findViewById(R.id.tv_distance_unit);
        this.tv_runwalk_unit = (TextView) findViewById(R.id.tv_runwalk_unit);
        this.tv_search_burn_calories = (TextView) findViewById(R.id.tv_search_burn_calories);
//        this.adView = (AdView) findViewById(R.id.adView);
//        AdView mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
//        this.et_weight.setTypeface(this.typefaceManager.getLight());
//        this.et_distance.setTypeface(this.typefaceManager.getLight());
//        this.tv_runwalk.setTypeface(this.typefaceManager.getLight());
//        this.tv_weightunit.setTypeface(this.typefaceManager.getLight());
//        this.tv_distance_unit.setTypeface(this.typefaceManager.getLight());
//        this.tv_runwalk_unit.setTypeface(this.typefaceManager.getLight());
//        this.tv_search_burn_calories.setTypeface(this.typefaceManager.getBold());
//        this.tv_cal_burn.setTypeface(this.typefaceManager.getBold());
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().addFlags(67108864);
        }
        this.iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Calorie_Burn.this.onBackPressed();
            }
        });
        this.arraylist_weigth.clear();
        this.arraylist_weigth.add(getString(R.string.kg));
        this.arraylist_weigth.add(getString(R.string.lbs));
        this.adapter_weight = new ArrayAdapter<>(this, R.layout.spinner_item, R.id.text1, this.arraylist_weigth);
        this.arraylist_distance.clear();
        this.arraylist_distance.add(getString(R.string.miles));
        this.arraylist_distance.add(getString(R.string.km));
        this.adapter_distance = new ArrayAdapter<>(this, R.layout.spinner_item, R.id.text1, this.arraylist_distance);
        this.arraylist_runwalk.clear();
        this.arraylist_runwalk.add(getString(R.string.Running));
        this.arraylist_runwalk.add(getString(R.string.Walking));
        this.adapter_runwalk = new ArrayAdapter<>(this, R.layout.spinner_item, R.id.text1, this.arraylist_runwalk);
        this.tv_search_burn_calories.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Calorie_Burn.this.et_weight.getText().toString().trim().equals("") || Calorie_Burn.this.et_weight.getText().toString().trim().equals(".")) {
                    Calorie_Burn.this.et_weight.setError(Calorie_Burn.this.getString(R.string.Enter_Weight));
                } else if (Calorie_Burn.this.et_distance.getText().toString().trim().equals("")) {
                    Calorie_Burn.this.et_distance.setError(Calorie_Burn.this.getString(R.string.Enter_Distance_You_Run));
                } else {
                    Calorie_Burn.this.weight = Float.parseFloat(Calorie_Burn.this.et_weight.getText().toString());
                    Calorie_Burn.this.distance = Float.parseFloat(Calorie_Burn.this.et_distance.getText().toString());
                    Calorie_Burn.this.weightunit = Calorie_Burn.this.tv_weightunit.getText().toString();
                    Calorie_Burn.this.distanceunit = Calorie_Burn.this.tv_distance_unit.getText().toString();
                    Calorie_Burn.this.runwalkunit = Calorie_Burn.this.tv_runwalk_unit.getText().toString();
                    int random = ((int) (Math.random() * 2.0d)) + 1;
                    PrintStream printStream = System.out;
                    StringBuilder sb = new StringBuilder();
                    sb.append("random_number==>");
                    sb.append(random);
                    printStream.println(sb.toString());
                        Calorie_Burn.this.calculate();

                }
            }
        });
        this.tv_weightunit.setOnClickListener(showPopupWindow_Weight());
        this.tv_distance_unit.setOnClickListener(showPopupWindow_distance());
        this.tv_runwalk_unit.setOnClickListener(showPopupWindow_runwalk());
    }


    public void calculate() {
        String str = "inserted_weight";
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("inserted_weight");
            sb.append(this.weight);
            Log.d(str, sb.toString());
            if (this.weightunit.equalsIgnoreCase(getString(R.string.lbs))) {
                this.weight = this.weight;
            } else {
                this.weight *= 2.2046f;
            }
            if (this.distanceunit.equalsIgnoreCase(getString(R.string.miles))) {
                this.total_distance = this.distance;
                if (this.runwalkunit.equalsIgnoreCase(getString(R.string.Walking))) {
                    this.calories_burn = this.weight * 0.53f;
                } else if (this.runwalkunit.equalsIgnoreCase(getString(R.string.Running))) {
                    this.calories_burn = this.weight * 0.75f;
                }
                this.total_calories_burn = this.total_distance * this.calories_burn;
            } else {
                this.total_distance = this.distance * 0.62137f;
                if (this.runwalkunit.equalsIgnoreCase(getString(R.string.Walking))) {
                    this.calories_burn = this.weight * 0.53f;
                } else if (this.runwalkunit.equalsIgnoreCase(getString(R.string.Running))) {
                    this.calories_burn = this.weight * 0.75f;
                }
                this.total_calories_burn = this.total_distance * this.calories_burn;
            }
            if (this.total_distance <= 3.0f) {
                this.tips = getString(R.string.You_better_start_working);
            } else if (this.total_distance >= 4.0f && this.total_distance <= 6.0f) {
                this.tips = getString(R.string.Nice_run_but_you_can_do_better);
            } else if (this.total_distance >= 7.0f && this.total_distance <= 10.0f) {
                this.tips = getString(R.string.Very_good_Push_above_next_time);
            } else if (this.total_distance >= 11.0f && this.total_distance <= 20.0f) {
                this.tips = getString(R.string.Great_Your_a_runner_keep_it_up);
            } else if (this.total_distance >= 21.0f && this.total_distance <= 25.0f) {
                this.tips = getString(R.string.Bill_Rogers_move_over);
            } else if (this.total_distance > 25.0f) {
                this.tips = getString(R.string.Your_my_hero_Have_a_jelly_doughnut);
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("tops");
            sb2.append(this.tips);
            Log.d("tops", sb2.toString());
            Intent intent = new Intent(this, Calories_Burn_Result.class);
            intent.putExtra("caloriesburn", this.total_calories_burn);
            intent.putExtra("tips", this.tips);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener showPopupWindow_Weight() {
        return new View.OnClickListener() {
            public void onClick(View view) {
                Calorie_Burn.this.popupWindowWeight().showAsDropDown(view, 0, 0);
            }
        };
    }

    private View.OnClickListener showPopupWindow_distance() {
        return new View.OnClickListener() {
            public void onClick(View view) {
                Calorie_Burn.this.popupWindowdistance().showAsDropDown(view, 0, 0);
            }
        };
    }

    private View.OnClickListener showPopupWindow_runwalk() {
        return new View.OnClickListener() {
            public void onClick(View view) {
                Calorie_Burn.this.popupWindowrunwalk().showAsDropDown(view, 0, 0);
            }
        };
    }


    public PopupWindow popupWindowWeight() {
        this.popupWindowWeight = new PopupWindow(this);
        this.listViewWeight = new ListView(this);
        this.listViewWeight.setDividerHeight(0);
        this.listViewWeight.setAdapter(this.adapter_weight);
        this.listViewWeight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                StringBuilder sb = new StringBuilder();
                sb.append("position->");
                sb.append(i);
                Log.d("position", sb.toString());
                StringBuilder sb2 = new StringBuilder();
                sb2.append("arraylist_weigth->");
                sb2.append((String) Calorie_Burn.this.arraylist_weigth.get(i));
                Log.d("arraylist_weigth", sb2.toString());
                Calorie_Burn.this.tv_weightunit.setText((CharSequence) Calorie_Burn.this.arraylist_weigth.get(i));
                Calorie_Burn.this.dismissPopupTopics();
            }
        });
        this.popupWindowWeight.setFocusable(true);
        this.popupWindowWeight.setWidth(this.tv_weightunit.getMeasuredWidth());
        this.popupWindowWeight.setHeight(-2);
        this.popupWindowWeight.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), 17170443));
        this.popupWindowWeight.setContentView(this.listViewWeight);
        return this.popupWindowWeight;
    }


    public PopupWindow popupWindowdistance() {
        this.popupWindowdistance = new PopupWindow(this);
        this.listViewdistance = new ListView(this);
        this.listViewdistance.setDividerHeight(0);
        this.listViewdistance.setAdapter(this.adapter_distance);
        this.listViewdistance.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                StringBuilder sb = new StringBuilder();
                sb.append("position->");
                sb.append(i);
                Log.d("position", sb.toString());
                StringBuilder sb2 = new StringBuilder();
                sb2.append("arraylist_distance->");
                sb2.append((String) Calorie_Burn.this.arraylist_distance.get(i));
                Log.d("arraylist_distance", sb2.toString());
                Calorie_Burn.this.tv_distance_unit.setText((CharSequence) Calorie_Burn.this.arraylist_distance.get(i));
                Calorie_Burn.this.dismissPopupTopics1();
            }
        });
        this.popupWindowdistance.setFocusable(true);
        this.popupWindowdistance.setWidth(this.tv_distance_unit.getMeasuredWidth());
        this.popupWindowdistance.setHeight(-2);
        this.popupWindowdistance.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), 17170443));
        this.popupWindowdistance.setContentView(this.listViewdistance);
        return this.popupWindowdistance;
    }


    public PopupWindow popupWindowrunwalk() {
        this.popupWindowrunwalk = new PopupWindow(this);
        this.listViewrunwalk = new ListView(this);
        this.listViewrunwalk.setDividerHeight(0);
        this.listViewrunwalk.setAdapter(this.adapter_runwalk);
        this.listViewrunwalk.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                StringBuilder sb = new StringBuilder();
                sb.append("position->");
                sb.append(i);
                Log.d("position", sb.toString());
                StringBuilder sb2 = new StringBuilder();
                sb2.append("arraylist_weigth->");
                sb2.append((String) Calorie_Burn.this.arraylist_runwalk.get(i));
                Log.d("arraylist_weigth", sb2.toString());
                Calorie_Burn.this.tv_runwalk_unit.setText((CharSequence) Calorie_Burn.this.arraylist_runwalk.get(i));
                Calorie_Burn.this.tv_runwalk.setText((CharSequence) Calorie_Burn.this.arraylist_runwalk.get(i));
                Calorie_Burn.this.dismissPopupTopics2();
            }
        });
        this.popupWindowrunwalk.setFocusable(true);
        this.popupWindowrunwalk.setWidth(this.tv_runwalk_unit.getMeasuredWidth());
        this.popupWindowrunwalk.setHeight(-2);
        this.popupWindowrunwalk.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), 17170443));
        this.popupWindowrunwalk.setContentView(this.listViewrunwalk);
        return this.popupWindowrunwalk;
    }


    public void dismissPopupTopics() {
        if (this.popupWindowWeight != null) {
            this.popupWindowWeight.dismiss();
        }
    }


    public void dismissPopupTopics1() {
        if (this.popupWindowdistance != null) {
            this.popupWindowdistance.dismiss();
        }
    }


    public void dismissPopupTopics2() {
        if (this.popupWindowrunwalk != null) {
            this.popupWindowrunwalk.dismiss();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }


//    public void onResume() {
//        super.onResume();
//        if (!this.sharedPreferenceManager.get_Remove_Ad().booleanValue() && MyApplication.interstitial != null && !MyApplication.interstitial.isLoaded() && !MyApplication.interstitial.isLoading()) {
//            ConnectionBuddy.getInstance().hasNetworkConnection(new NetworkRequestCheckListener() {
//                public void onNoResponse() {
//                }
//
//                public void onResponseObtained() {
//                    MyApplication.interstitial.loadAd(new Builder().build());
//                }
//            });
//        }
//        if (!this.sharedPreferenceManager.get_Remove_Ad().booleanValue()) {
//            MyApplication.interstitial.setAdListener(new AdListener() {
//                public void onAdClosed() {
//                    super.onAdClosed();
//                    MyApplication.interstitial.loadAd(new Builder().build());
//                    Calories_burn_Calculator.this.calculate();
//                }
//
//                public void onAdFailedToLoad(int i) {
//                    super.onAdFailedToLoad(i);
//                    if (MyApplication.interstitial != null && !MyApplication.interstitial.isLoading()) {
//                        ConnectionBuddy.getInstance().hasNetworkConnection(new NetworkRequestCheckListener() {
//                            public void onNoResponse() {
//                            }
//
//                            public void onResponseObtained() {
//                                MyApplication.interstitial.loadAd(new Builder().build());
//                            }
//                        });
//                    }
//                }
//            });
//        }
//    }
//
//    public void showIntertitial() {
//        if (this.sharedPreferenceManager.get_Remove_Ad().booleanValue()) {
//            calculate();
//        } else if (MyApplication.interstitial == null || !MyApplication.interstitial.isLoaded()) {
//            if (!MyApplication.interstitial.isLoading()) {
//                ConnectionBuddy.getInstance().hasNetworkConnection(new NetworkRequestCheckListener() {
//                    public void onNoResponse() {
//                    }
//
//                    public void onResponseObtained() {
//                        MyApplication.interstitial.loadAd(new Builder().build());
//                    }
//                });
//            }
//            calculate();
//        } else {
//            MyApplication.interstitial.show();
//        }
//    }

    public void onBackPressed() {
        //this.adView.setVisibility(8);
        ActivityCompat.finishAfterTransition(this);
    }
}
