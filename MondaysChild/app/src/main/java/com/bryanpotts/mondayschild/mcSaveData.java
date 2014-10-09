package com.bryanpotts.mondayschild;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by bryan on 02/10/2014.
 */
public class mcSaveData extends Activity {

    // *********
    // Variables
    // *********
//region
    SharedPreferences mcSharedprefs;

    private int mcSDDOW;
    private int mcSDMonth;
    private String mcSDDayBorn;
    private String mcSDStarSign; // Lab 3
//endregion

    // ***************************
    // Declare getters and setters
    // ***************************
//region
    private void setmcSDDOW(int isDOW) {
        this.mcSDDOW = isDOW;
    }

    public int getmcSDDOW() {
        return mcSDDOW;
    }

    private void setmcSDMonth(int isMonth) {
        this.mcSDMonth = isMonth;
    }

    public int getmcSDMonth() {
        return mcSDMonth;
    }

    private void setmcSDDayBorn (String isDayBorn) {
        this.mcSDDayBorn = isDayBorn;
    }

    public String getmcSDDayBorn() {
        return mcSDDayBorn;
    }

    private void setmcSDStarSign (String mcSDStarSign) {
        this.mcSDStarSign = mcSDStarSign; // Lab 3
    }

    public String getmcSDStarSign() {
        return mcSDStarSign; // Lab 3
    }
//endregion

    // *******************************
    // Declare constructor and date
    // manipulation method.
    // ******************************
//region
    public mcSaveData(SharedPreferences mcSDPrefs) {

        setmcSDDOW(1);
        setmcSDDOW(1);
        setmcSDDayBorn("Sunday");
        setmcSDStarSign("January"); // Lab 3

        try {
            this.mcSharedprefs = mcSDPrefs;
        }
        catch (Exception e)
        {
            Log.e("n", "Pref manager is Null");
        }


        setDefaultPrefs();
    }

    public void savePreferences (String key, String value) {
        SharedPreferences.Editor editor = mcSharedprefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void savePreferences (String key, int value) {
        SharedPreferences.Editor editor = mcSharedprefs.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void setDefaultPrefs() {
        savePreferences("mc_DOW", 1);
        savePreferences("mc_Month", 1);
        savePreferences("mc_DayBorn", "Empty");
        savePreferences("mc_StarSign", "Empty"); // Lab 3
    }
// endregion
}
