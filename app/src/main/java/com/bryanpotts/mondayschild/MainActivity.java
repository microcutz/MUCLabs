package com.bryanpotts.mondayschild;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.io.IOException;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    DatePicker dpBDay; // Lab 1
    Button submitBtn; // Lab 1
    mcSaveData mcSDPrefs; // Lab 2
    SharedPreferences mySharedPrefs; // Lab 2
    FragmentManager fmAboutDialogue; // Lab 2
    String sOutputMsg; // Lab 3

    mcStarSignInfo userStarSigninfo; // Lab 4

    //****************************************************************
    //onCreate
    //An event handler called when the app is first created
    //Usually contains all initializations and setting up for the app
    //Generally the place to show the app main UI screen
    //****************************************************************
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);// app main UI screen
        //Create an instance of the DatePicker Object for the Birthday
        dpBDay = (DatePicker) findViewById(R.id.datePickerBDay);
        dpBDay.setCalendarViewShown(false);
        //Setup, access and listen for the submit button
        submitBtn = (Button) findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(this);
        //create Default preferences for the app.
        mySharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        mcSDPrefs = new mcSaveData(mySharedPrefs);
        mcSDPrefs.setDefaultPrefs();

        fmAboutDialogue = this.getFragmentManager();

        Log.e("n", "message");

        userStarSigninfo = new mcStarSignInfo(); // Lab 4

    }

    @Override
    public void onClick(View view) {
        //Create an instance of the MondaysChild Class
        mondaysChild mcYourDay = new mondaysChild(dpBDay.getDayOfMonth(), dpBDay.getMonth(), dpBDay.getYear());
        // Create an instance of the astrology Class
        astrology usersStarSign = new astrology(dpBDay.getDayOfMonth(), dpBDay.getMonth()+1); // Lab 3

        // ======
        // LAB 4
        // ======
        // Create database handler instance
        mcStarSignsInfoDBMgr dbStarSignMgr = new mcStarSignsInfoDBMgr(this, "starsigns.s3db", null, 1); // lab 4
        try {
            dbStarSignMgr.dbCreate();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Retrieve Star Sign Info
        userStarSigninfo = dbStarSignMgr.findStarSign(usersStarSign.getsStarSign()); // Lab 4

        // Save preferences
        mcSDPrefs.savePreferences("mc_DOW", mcYourDay.getiDOW());
        mcSDPrefs.savePreferences("mc_Month", mcYourDay.getiMonth());
        mcSDPrefs.savePreferences("mc_DayBorn", mcYourDay.getsDOW());
        mcSDPrefs.savePreferences("mc_StarSign", usersStarSign.getsStarSign()); // Lab 3

        //Starting new Intent
        Intent mcOutput_Screen = new Intent(getApplicationContext(), mcOutputScreen.class);

        //Send data to the new Activity
        /*
        sOutputMsg = mcYourDay.getsOutputMsg() + "\nYour star sign is: " + usersStarSign.getsStarSign(); // Lab 3
        mcOutput_Screen.putExtra("mcOutputMsg",sOutputMsg); // Lab 3
        mcOutput_Screen.putExtra("mcStarSign", usersStarSign.getsStarSign()); // Lab 3
        */

        // Send serialised Object to t new activity for display - Lab 4
        mcOutput_Screen.putExtra("starSignInfo", userStarSigninfo); // lab 4
        //Log the output data
        Log.e("n", mcYourDay.getsOutputMsg());
        //Start the new activity
        startActivity(mcOutput_Screen);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mc_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.mQuit:
                finish();
                return true;
            case R.id.mAbout:
                // About dialogue
                DialogFragment mcAboutDlg = new mcAboutDialogue();
                mcAboutDlg.show(fmAboutDialogue, "mc_About_Dlg");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
