package com.bryanpotts.mondayschild;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by bryan on 02/10/2014.
 */
public class mcSavingDataOutput extends Activity implements View.OnClickListener {

    SharedPreferences mcSharedPrefs;
    Button btnBack;
    TextView mcSDODOW;
    TextView mcSDOMonth;
    TextView mcSODayBorn;

    //*********************************************************************
    // onCreate.
    // An event handler called when the app is first created
    // Usually contains al the initializations and settings up for the app.
    // Generally the place to show the app main UI screen
    //*********************************************************************

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mc_display_shared_prefs);
        // Setup, access and listen fot eh pick date button
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        // Create text view for output
        mcSDODOW = (TextView) findViewById(R.id.tvDow);
        mcSDOMonth = (TextView) findViewById(R.id.tvMonth);
        mcSODayBorn = (TextView) findViewById(R.id.tvDayBorn);
        // Load any saved preferences
        mcSharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        loadSavedPreferences();
        Log.e("n", "SDOutput msg");
    }

    private void loadSavedPreferences() {
        mcSDODOW.setText(mcSDODOW.getText() + String.valueOf(mcSharedPrefs.getInt("mc_DOW", 1)));
        mcSDOMonth.setText(mcSDOMonth.getText() + String.valueOf(mcSharedPrefs.getInt("mc_Month", 1)));
        mcSODayBorn.setText(mcSODayBorn.getText() + mcSharedPrefs.getString("mc_DayBorn", "Sunday"));
    }

    @Override
    public void onClick(View view) {
        setResult(Activity.RESULT_OK);
        finish();
    }
}
