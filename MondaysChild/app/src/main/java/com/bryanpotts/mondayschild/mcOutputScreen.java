package com.bryanpotts.mondayschild;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by bryan on 01/10/2014.
 */
public class mcOutputScreen extends Activity implements View.OnClickListener{

    Button btnDatePick;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mc_output_screen);// app main ui screen
        //setup access and listen for the pick date button
        btnDatePick = (Button) findViewById(R.id.btnPickDate);
        btnDatePick.setOnClickListener(this);

        //Create textview for output
        TextView mcOutput = (TextView) findViewById(R.id.tvOutputMsg);
        //Get the intent and data
        Intent iMainAct = getIntent();
        mcOutput.setText(iMainAct.getStringExtra("mcOutputMsg"));
        //mcOutput.setText(iMainAct.getStringExtra());

    }

    public void onClick(View view) {
        setResult(Activity.RESULT_OK);
        finish();
    }
}
