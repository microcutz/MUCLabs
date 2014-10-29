package com.bryanpotts.mondayschild;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by bryan on 01/10/2014.
 */
public class mcOutputScreen extends Activity implements View.OnClickListener{

    Button btnDatePick;
    ImageView ivStarSign; // Lab 3

    Button btnShowSavedData;

    TextView tvStarSign; // Lab 4
    TextView tvStarSignDates; // Lab 4
    TextView tvStarSignChars; // Lab 4

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mc_output_screen);// app main ui screen
        //setup access and listen for the pick date button
        btnDatePick = (Button) findViewById(R.id.btnPickDate);
        btnDatePick.setOnClickListener(this);

        // Setup, access and listen for the view saved data button
        btnShowSavedData = (Button) findViewById(R.id.btnSavedData);
        btnShowSavedData.setOnClickListener(this);

        // Display text
        tvStarSign = (TextView) findViewById(R.id.tvStarSign); // Lab 4
        tvStarSignDates = (TextView) findViewById(R.id.tvStarSignDates); // Lab 4
        tvStarSignChars = (TextView) findViewById((R.id.tvStarSignChars)); // Lab 4
        // Display starsign img
        ivStarSign = (ImageView) findViewById(R.id.imgViewStarSign); // Lab 3

        //Create textview for output
        //TextView mcOutput = (TextView) findViewById(R.id.tvOutputMsg);



        //Get the intent and data
        Intent iMainAct = getIntent();
        String test = (String) iMainAct.getSerializableExtra("starSignInfo");
        //Log.e(test, "test");
        mcStarSignsInfo starSignInfo = (mcStarSignsInfo) iMainAct.getSerializableExtra("starSignInfoOne"); // Lab 4
        //Log.e(starSignInfo.toString(), "starSingInfo contents");
        tvStarSign.setText(starSignInfo.getStarSign()); // Lab 4
        tvStarSignDates.setText(starSignInfo.getStarSignDates()); // Lab 4
        tvStarSignChars.setText(starSignInfo.getStarSignCharacteristics()); // Lab 4
        String sImagePath = "drawable/" + starSignInfo.getStarSignImg(); // Lab 3
        Log.d(sImagePath, "sImagePath");
        Context appContext = getApplicationContext(); // lab 3
        int imgResID = appContext.getResources().getIdentifier(sImagePath, "drawable", "com.bryanpotts.mondayschild"); // Lab 3

        ivStarSign.setImageResource(imgResID); // Lab 3
        Log.d(Integer.toString(imgResID), "imgResID");
        //mcOutput.setText(iMainAct.getStringExtra("mcOutputMsg"));


    }

    public void onClick(View view) {
        setResult(Activity.RESULT_OK);
        finish();
    }
}
