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


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    DatePicker dpBDay;
    Button submitBtn;
    mcSaveData mcSDPrefs;
    SharedPreferences mySharedPrefs;
    FragmentManager fmAboutDialogue;

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

    }

    @Override
    public void onClick(View view) {
        //Create an instance of the MondaysChild Class
        mondaysChild mcYourDay = new mondaysChild(dpBDay.getDayOfMonth(), dpBDay.getMonth(), dpBDay.getYear());

        //Starting new Intent
        Intent mcOutput_Screen = new Intent(getApplicationContext(), mcOutputScreen.class);
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
