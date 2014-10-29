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

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;

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

    TextView tvDayBorn; // Lab 5
    TextView tvHoroscope; // Lab 5

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
        tvHoroscope = (TextView) findViewById(R.id.tvStarSignHoroScope); // Lab 5
        tvDayBorn = (TextView) findViewById(R.id.tvDayBorn); // Lab 5
        // Display starsign img
        ivStarSign = (ImageView) findViewById(R.id.imgViewStarSign); // Lab 3

        //Create textview for output
        //TextView mcOutput = (TextView) findViewById(R.id.tvOutputMsg);

        //Get the intent and data
        Intent iMainAct = getIntent();

        mcStarSignsInfo starSignInfo = (mcStarSignsInfo) iMainAct.getSerializableExtra("starSignInfoOne"); // Lab 4

        tvStarSign.setText(starSignInfo.getStarSign()); // Lab 4
        tvStarSignDates.setText(starSignInfo.getStarSignDates()); // Lab 4
        tvStarSignChars.setText(starSignInfo.getStarSignCharacteristics()); // Lab 4
        tvDayBorn.setText(iMainAct.getStringExtra("mcOutputMsg")); // Lab 5
        String sImagePath = "drawable/" + starSignInfo.getStarSignImg(); // Lab 3 Amended Lab 4
        Context appContext = getApplicationContext(); // lab 3
        int imgResID = appContext.getResources().getIdentifier(sImagePath, "drawable", "com.bryanpotts.mondayschild"); // Lab 3
        ivStarSign.setImageResource(imgResID); // Lab 3

        // Get horoscope RSS Feed
        mcRSSDataItem userHoroscope = new mcRSSDataItem(); // Lab5
            String RSSFeedURL = "http://www.findyourfate.com/rss/dailyhoroscope-feed.asp?sign=" + starSignInfo.getStarSign(); // Lab 5
            mcAsyncRSSParser rssAsyncParse = new mcAsyncRSSParser(this, RSSFeedURL); // Lab 5

        try {
            userHoroscope = rssAsyncParse.execute("").get(); // Lab 5
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        tvHoroscope.setText(userHoroscope.getItemDesc()); // Lab 5

    }

    public void onClick(View view) {
        setResult(Activity.RESULT_OK);
        finish();
    }
}
