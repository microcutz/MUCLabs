package com.bryanpotts.mondayschild;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;

/**
 * Created by bryan on 30/10/14.
 */
public class mcBioActivity extends Activity {

    // *****************************************************************
    // OnCreate.
    // An event handler called when the app is first created.
    // Usually contains all initializations and settings up for the app.
    // Generally the place to show the app main UI screen
    // *****************************************************************

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mc_bio_draw_screen); // App main UI screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(new mcBiorhythmsSurfaceView(this));
    }


}
