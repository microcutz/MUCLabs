package com.bryanpotts.mondayschild;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mc_output_screen);// app main ui screen
        //setup access and listen for the pick date button
        btnDatePick = (Button) findViewById(R.id.btnPickDate);
        btnDatePick.setOnClickListener(this);
        ivStarSign = (ImageView) findViewById(R.id.imgViewStarSign); // Lab 3

        //Create textview for output
        TextView mcOutput = (TextView) findViewById(R.id.tvOutputMsg);


        //Get the intent and data
        Intent iMainAct = getIntent();

        String sImagePath = "drawable/" + iMainAct.getStringExtra("mcStarSign").toLowerCase() + "96x96"; // Lab 3
        Context appContext = getApplicationContext(); // lab 3
        int imgResID = appContext.getResources().getIdentifier(sImagePath, "drawable", "com.bryanpotts.mondayschild.app"); // Lab 3
        ivStarSign.setImageResource(imgResID); // Lab 3
        mcOutput.setText(iMainAct.getStringExtra("mcOutputMsg"));


    }

    public void onClick(View view) {
        setResult(Activity.RESULT_OK);
        finish();
    }
}
