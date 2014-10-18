package com.bryanpotts.mondayschild;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by bryan on 01/10/2014.
 */
public class mondaysChild {



    //***********************
    //Declare variables
    //***********************
//region
    private int iDOW;
    private int iMonth;
    private int iYear;
    private int iDayOfWeek;

    private String sDOW;
    private String sLineFromPoem;
    private String[] saLinesOfPoem;
    private String[] saDOW;
    private String sOutputMsg;
//endregion

    //***************************
    //Declare getters and setters
    //***************************
//region
    private void setiDOW(int isDOW)
    {
        this.iDOW = isDOW;
    }

    public int getiDOW()
    {
        return iDOW;
    }

    private void setiMonth(int isMonth)
    {
        this.iMonth = isMonth;
    }

    public int getiMonth()
    {
        return iMonth;
    }

    private void setiYear(int isYear)
    {
        this.iYear = isYear;
    }

    public  int getiYear()
    {
        return iYear;
    }

    private void setiDayOfWeek(int isDayOfWeek)
    {
        this.iDayOfWeek = isDayOfWeek;
    }

    public int getiDayOfWeek()
    {
        return iDayOfWeek;
    }

    private void setsDOW(int iDAY)
    {
        this.sDOW = saDOW[iDAY];
    }

    public String getsDOW()
    {
        return sDOW;
    }

    private void setsLineFromPoem(int iPoemLine)
    {
        this.sLineFromPoem = saLinesOfPoem[iPoemLine];
    }

    public String getsLineFromPoem()
    {
        return sLineFromPoem;
    }

    private void setSaLinesOfPoem()
    {
        saLinesOfPoem = new String[7];
        saLinesOfPoem[0] = "And the child born on the Sabbath day, is bonny and blithe, and good and gay";
        saLinesOfPoem[1] = "Mondays child is fair of face";
        saLinesOfPoem[2] = "Tuesdays child is full of grace";
        saLinesOfPoem[3] = "Wednesdays child is full of woe";
        saLinesOfPoem[4] = "Thursdays child has far to go";
        saLinesOfPoem[5] = "Fridays child is loving and giving";
        saLinesOfPoem[6] = "Saturdays child works hard for a living";

    }

    public String[] getSaLinesOfPoem()
    {
        return saLinesOfPoem;
    }

    private  void setSaDOW()
    {
        saDOW = new String[7];
        saDOW[0] = "Sunday";
        saDOW[1] = "Monday";
        saDOW[2] = "Tuesday";
        saDOW[3] = "Wednesday";
        saDOW[4] = "Thursday";
        saDOW[5] = "Friday";
        saDOW[6] = "Saturday";
    }
    public String[] getSaDOW()
    {
        return saDOW;
    }

    private void setsOutputMsg(String sOutputMsg)
    {
        this.sOutputMsg = sOutputMsg;
    }

    public String getsOutputMsg()
    {
        return sOutputMsg;
    }

// endregion

    //*************************
    // Declare constructor and
    // date manipulation methods
    //*************************
//region
    public mondaysChild()
    {
        //Fill the day and poem arrays
        setSaDOW();
        setSaLinesOfPoem();
        //use the Calendar to instantiate a calendar object for today's date
        Calendar cCal = Calendar.getInstance();
        //Store the date, month and year
        setiDOW(cCal.get(Calendar.DAY_OF_WEEK));
        setiMonth(cCal.get(Calendar.MONTH));
        setiYear(cCal.get(Calendar.YEAR));
    }

    public mondaysChild(int mcDOW, int mcMonth, int mcYear)
    {
        //Fill the day and poem array
        setSaDOW();
        setSaLinesOfPoem();
        //Store the date, month and year
        setiDOW(mcDOW);
        setiMonth(mcMonth);
        setiYear(mcYear);
        //Use the Gregorian calendar to instantiate a calendar object for the Birthday
        GregorianCalendar gcBDay = new GregorianCalendar(mcYear, mcMonth, mcDOW);
        //Set the day of the week integer using the Birthday
        setiDayOfWeek(gcBDay.get(Calendar.DAY_OF_WEEK)-1);
        //Set the string day of the week
        setsDOW(getiDayOfWeek());
        //Set the line from the poem based on the integer day of the week
        setsLineFromPoem(getiDayOfWeek());
        //Create temporary string for output message
        String sTempStr = "You were born on a " + getsDOW() + "\n" +
                getsLineFromPoem();
        setsOutputMsg(sTempStr);
    }
//endregion

}
