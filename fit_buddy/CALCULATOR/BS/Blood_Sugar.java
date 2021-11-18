package com.example.fit_buddy.CALCULATOR.BS;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.fit_buddy.R;
import com.example.fit_buddy.TypefaceManager;

import java.io.PrintStream;
import java.util.ArrayList;

public class Blood_Sugar extends Activity {
    String TAG = getClass().getSimpleName();
    //AdView adView;
    ArrayAdapter<String> adapter_sugar;
    ArrayList<String> arraylist_sugar = new ArrayList<>();
    Double blood_sugarval;
    EditText et_sugar_value;
    Double final_bloodsugar_val;
    //GlobalFunction globalFunction;
    ImageView iv_back;
    ListView listViewsugar;
    private PopupWindow popupWindowsugar;
    //SharedPreferenceManager sharedPreferenceManager;
    TextView tv_caluculate_blood_sugar;
    TextView tv_sugar;
    TextView tv_sugar_unit;
    TypefaceManager typefaceManager;


//    public void attachBaseContext(Context context) {
//        super.attachBaseContext(uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper.wrap(context));
//    }


    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_blood_sugar);
//        this.sharedPreferenceManager = new SharedPreferenceManager(this);
//        this.globalFunction = new GlobalFunction(this);
//        this.typefaceManager = new TypefaceManager(getAssets(), this);
//        this.globalFunction.set_locale_language();
        this.iv_back = (ImageView) findViewById(R.id.iv_back);
        this.tv_sugar = (TextView) findViewById(R.id.tv_sugar);
        this.tv_sugar_unit = (TextView) findViewById(R.id.tv_sugar_unit);
        this.et_sugar_value = (EditText) findViewById(R.id.et_sugar_value);
        this.tv_caluculate_blood_sugar = (TextView) findViewById(R.id.tv_caluculate_blood_sugar);
//        this.adView = (AdView) findViewById(R.id.adView);
//        AdView mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
        this.tv_sugar_unit.setOnClickListener(showPopupWindowTime());
        this.arraylist_sugar.clear();
        this.arraylist_sugar.add(getString(R.string.mmol));
        this.arraylist_sugar.add(getString(R.string.mg));
        this.adapter_sugar = new ArrayAdapter<>(this, R.layout.spinner_item, R.id.text1, this.arraylist_sugar);
//        this.globalFunction.sendAnalyticsData(this.TAG, this.TAG);
//        this.tv_sugar.setTypeface(this.typefaceManager.getBold());
//        this.tv_caluculate_blood_sugar.setTypeface(this.typefaceManager.getBold());
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().addFlags(67108864);
        }
        this.iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Blood_Sugar.this.onBackPressed();
            }
        });
        this.tv_caluculate_blood_sugar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Blood_Sugar.this.et_sugar_value.getText().toString().trim().equals("") || Blood_Sugar.this.et_sugar_value.getText().toString().trim().equals(".")) {
                    Toast.makeText(Blood_Sugar.this.getApplicationContext(), Blood_Sugar.this.getString(R.string.Enter_Blood_sugar_value), 0).show();
                    return;
                }
                Blood_Sugar.this.blood_sugarval = Double.valueOf(Double.parseDouble(Blood_Sugar.this.et_sugar_value.getText().toString().toString()));
                if (Blood_Sugar.this.tv_sugar_unit.getText().toString().trim().equals(Blood_Sugar.this.getString(R.string.mmol))) {
                    Blood_Sugar.this.calculate_m1();
                } else {
                    Blood_Sugar.this.calculate_m2();
                }
                int random = ((int) (Math.random() * 2.0d)) + 1;
                PrintStream printStream = System.out;
                StringBuilder sb = new StringBuilder();
                sb.append("random_number==>");
                sb.append(random);
                printStream.println(sb.toString());
//                if (random == 2) {
//                    Blood_Sugar.this.showIntertitial();
//                    return;
//                }
                Intent intent = new Intent(Blood_Sugar.this, Sugar_Result.class);
                intent.putExtra("final_bloodsugar_val", Blood_Sugar.this.final_bloodsugar_val);
                Blood_Sugar.this.startActivity(intent);
            }
        });
    }

    public void calculate_m1() {
        this.final_bloodsugar_val = Double.valueOf(this.blood_sugarval.doubleValue() * 18.0d);
    }

    public void calculate_m2() {
        this.final_bloodsugar_val = Double.valueOf(this.blood_sugarval.doubleValue() * 0.05556d);
    }

    private View.OnClickListener showPopupWindowTime() {
        return new View.OnClickListener() {
            public void onClick(View view) {
                Blood_Sugar.this.popupWindowTime().showAsDropDown(view, 0, 0);
            }
        };
    }


    public PopupWindow popupWindowTime() {
        this.popupWindowsugar = new PopupWindow(this);
        this.listViewsugar = new ListView(this);
        this.listViewsugar.setDividerHeight(0);
        this.listViewsugar.setAdapter(this.adapter_sugar);
        this.listViewsugar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Blood_Sugar.this.tv_sugar_unit.setText((CharSequence) Blood_Sugar.this.arraylist_sugar.get(i));
                Blood_Sugar.this.dismissPopupTime();
            }
        });
        this.popupWindowsugar.setFocusable(true);
        this.popupWindowsugar.setWidth(this.tv_sugar_unit.getMeasuredWidth());
        this.popupWindowsugar.setHeight(-2);
        this.popupWindowsugar.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), 17170443));
        this.popupWindowsugar.setContentView(this.listViewsugar);
        return this.popupWindowsugar;
    }


    public void dismissPopupTime() {
        if (this.popupWindowsugar != null) {
            this.popupWindowsugar.dismiss();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    public void onBackPressed() {
        //this.adView.setVisibility(8);
        ActivityCompat.finishAfterTransition(this);
    }

//    public void showIntertitial() {
//        if (this.sharedPreferenceManager.get_Remove_Ad().booleanValue()) {
//            Intent intent = new Intent(this, Sugar_Result.class);
//            intent.putExtra("final_bloodsugar_val", this.final_bloodsugar_val);
//            startActivity(intent);
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
//            Intent intent2 = new Intent(this, Sugar_Result.class);
//            intent2.putExtra("final_bloodsugar_val", this.final_bloodsugar_val);
//            startActivity(intent2);
//        } else {
//            MyApplication.interstitial.show();
//        }
//    }


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
//                    Intent intent = new Intent(Sugar_calculator.this, Sugar_Result.class);
//                    intent.putExtra("final_bloodsugar_val", Sugar_calculator.this.final_bloodsugar_val);
//                    Sugar_calculator.this.startActivity(intent);
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
}